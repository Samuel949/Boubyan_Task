package Pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class LoginPage {

    private AppiumDriver driver;

    public LoginPage(AppiumDriver driver){
        this.driver = driver;
    }

    private By usernameField = By.id("required_username");
    private By passwordField = By.id("required_password");
    private By loginButton = By.id("required_login");
    private By welcomeMessage = By.id("required_welcome");
    private By errorMessage = By.id("required_error");

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public boolean isWelcomeMessageDisplayed() {
        return driver.findElement(welcomeMessage).isDisplayed();
    }

    public boolean isErrorMessageDisplayed() {
        return driver.findElement(errorMessage).isDisplayed();
    }
}