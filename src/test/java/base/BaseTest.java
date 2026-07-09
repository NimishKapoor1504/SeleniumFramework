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

public class BaseTest {

    protected WebDriver driver;
    protected static ExtentReports extentReports;
    protected ExtentTest test;

    @BeforeMethod
    public void setDriver() {

        Log.info("Setting Up WebDriver");
        driver = new EdgeDriver();
        //System.setProperty("webdriver.edge.driver","drivers/msedgedriver.exe");
        driver.manage().window().maximize();
        Log.info("Getting the Westpac Url");
        driver.get("https://banking.westpac.com.au/");
    }

    @BeforeSuite
    public void setupReport(){
        extentReports = ExtentReportManager.getInstance();
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
