package tests;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.ExtentReportManager;
import utils.Log;

import java.time.Duration;

public class HomePageNavigation extends BaseTest {

    @Test
    public void testHomePageNavigation() {
        HomePage homePage = new HomePage(driver);

        Log.info("HomePage navigation test");
        test = ExtentReportManager.createTest("HomePage");

        test.info("HomePage navigation to home page");
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Sign in to Westpac Online Banking");

        Log.info("Home Page Title: " + driver.getTitle());
        test.info("Navigation to business page");

        if(homePage.getBusinessLink().isEnabled()){
            homePage.getBusinessLink().click();
        }
        else{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(homePage.getBusinessLink()));
            homePage.getBusinessLink().click();
        }

        System.out.println(driver.getTitle());
        Log.info("Business Page Title: " + driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Business Banking | Westpac");
        driver.navigate().back();

        test.info("Navigation to corporate  page");

        homePage.getCorporate().click();
        System.out.println(driver.getTitle());

        Log.info("Corporate Banking Page Title: " + driver.getTitle());
        test.info("Navigation to business page");

        Assert.assertEquals(driver.getTitle(), "Corporate Banking | Westpac");
        driver.navigate().back();
        homePage.getSignInButton().click();
        test.pass("HomePage");
    }

}