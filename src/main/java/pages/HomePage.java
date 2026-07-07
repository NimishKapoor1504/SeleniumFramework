package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    private WebDriver driver;

    private By businessLink = By.linkText("Business");
    private By corporate = By.linkText("Corporate");
    private By signInButton = By.id("signin");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getBusinessLink() {
        return driver.findElement(businessLink);
    }

    public WebElement getCorporate() {
        return driver.findElement(corporate);
    }

    public WebElement getSignInButton() {
        return driver.findElement(signInButton);
    }

}
