package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.ExtentReportManager;
import utils.Log;

public class CorporatePageNavigation extends BaseTest {

    @Test
    public void testHomePageNavigation() {
        HomePage homePage = new HomePage(driver);

        Log.info("HomePage navigation test");
        test = ExtentReportManager.createTest("CorporatePage");

        test.info("HomePage navigation to home page");
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Sign in to Westpac Online Banking");



        Log.info("Home Page Title: " + driver.getTitle());
        test.info("Navigation to business page");
        homePage.getBusinessLink().click();
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
        test.pass("CorporatePage");


    }
}
