package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setDriver() {
        driver = new EdgeDriver();
        //System.setProperty("webdriver.edge.driver","drivers/msedgedriver.exe");
        driver.manage().window().maximize();
        driver.get("https://banking.westpac.com.au/");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
