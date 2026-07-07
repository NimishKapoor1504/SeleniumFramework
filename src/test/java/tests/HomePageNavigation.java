package tests;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomePageNavigation extends BaseTest {

    @Test
    public void testHomePageNavigation() {
        HomePage homePage = new HomePage(driver);

        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Sign in to Westpac Online Banking");

        homePage.getBusinessLink().click();
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Business Banking | Westpac");
        driver.navigate().back();
        homePage.getCorporate().click();
        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Corporate Banking | Westpac");
        driver.navigate().back();
        homePage.getSignInButton().click();

    }


}
