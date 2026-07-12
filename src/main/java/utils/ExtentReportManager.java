package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.ExtentTest.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {

    private static ExtentReports instance;
    private static ExtentTest extentReports;

    public static ExtentReports getInstance() {
        if (instance == null) {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String reportPath = "reports/ExtentReport_"+timeStamp+".html";
            ExtentSparkReporter reporter= new ExtentSparkReporter(reportPath);

            reporter.config().setDocumentTitle("Automation Test Report");
            reporter.config().setReportName("Test Execution Report");

            instance = new ExtentReports();
            instance.attachReporter(reporter);
        }
        return instance;

    }

    public static ExtentTest createTest(String testName){

        extentReports =getInstance().createTest(testName);
        return extentReports;
    }

    public static String captureScreenshot(WebDriver driver, String screenshotName){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");

        try{
            File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String path = System.getProperty("user.dir")+"/screenshots/"+screenshotName+sdf.format(new Date())+".png";
            FileUtils.copyFile(src, new File(path));
            return path;
        } catch (WebDriverException | IOException e) {
            throw new RuntimeException(e);
        }

    }

}
