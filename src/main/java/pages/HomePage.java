package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    private WebDriver driver;

    private final By businessLink = By.linkText("Business");
    private final By corporate = By.linkText("Corporate");
    private final By signInButton = By.id("signin");

    private final By homePageSection = By.linkText("Home");

    private final By dropdown = By.id("signIn");

    private final By whatsNew = By.linkText("What's new");

    public WebElement whatsNewLink() {
        return driver.findElement(whatsNew);
    }

    public WebElement dropdownselect() {
        return driver.findElement(dropdown);
    }

    public WebElement homePageSection(){
        return driver.findElement(homePageSection);
    }

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
