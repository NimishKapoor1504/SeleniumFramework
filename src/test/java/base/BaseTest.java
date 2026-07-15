package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import email.SendEmail;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ExtentReportManager;
import utils.Log;
import utils.Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    protected WebDriver driver;
    protected static ExtentReports extentReports;
    protected ExtentTest test;

    protected static String url;

    @BeforeSuite
    public void loadConfig() {
        Properties prop = new Properties();

        // Adjust the path based on where your file sits (e.g., src/test/resources)
        String propFilePath = "src/test/resources/configProperties/Env.properties";

        try (FileInputStream fis = new FileInputStream(propFilePath)) {
            prop.load(fis);

            // Extract the URL and save it to a variable
            url = prop.getProperty("westpacURL"); // or "app.url"
            System.out.println("Westpac URL: " + url);
        } catch (IOException e) {
            System.err.println("Could not load properties file!");
            e.printStackTrace();
        }
    }

    @BeforeSuite
    public void setupReport(){
        extentReports = ExtentReportManager.getInstance();
    }

    @BeforeMethod
    public void setDriver() {

        Log.info("Setting Up WebDriver");
        driver = new EdgeDriver();
        //System.setProperty("webdriver.edge.driver","drivers/msedgedriver.exe");
        driver.manage().window().maximize();
        Log.info("Getting the Westpac Url");
        driver.get(url);
    }

    @AfterSuite
    public void tearDownReport(){
        extentReports.flush();
    }

    @AfterMethod
    public void tearDown(ITestResult testResult) {

        if(testResult.getStatus() == ITestResult.FAILURE){
            String screenShotPath=ExtentReportManager.captureScreenshot(driver,"HomePageFailure");
            test.fail("Failed Test Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());
        }
        Log.info("Close Browser...");
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void sendReports() {
        Log.info("Sending Reports");
        SendEmail sendEmail = new SendEmail();
    }
}
