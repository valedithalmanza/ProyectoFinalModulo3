package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Represents the OrangeHRM login page.
 * Contains the locators and actions required to perform the login.
 */
public class LoginPage extends BasePage {

    // Locators
    private By usernameInput = By.name("username");
    private By passwordInput = By.name("password");
    private By loginButton = By.cssSelector("button[type='submit']");

    /**
     * Creates a LoginPage using the current WebDriver.
     *
     * @param driver WebDriver used to interact with the browser
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Enters the username in the username field.
     *
     * @param username username used to log in
     */
    public void enterUsername(String username) {
        waitForElement(usernameInput).sendKeys(username);
    }

    /**
     * Enters the password in the password field.
     *
     * @param password password used to log in
     */
    public void enterPassword(String password) {
        waitForElement(passwordInput).sendKeys(password);
    }

    /**
     * Clicks the login button.
     */
    public void clickLogin() {
        click(loginButton);
    }

    /**
     * Performs the login using the provided credentials.
     *
     * @param username username used to log in
     * @param password password used to log in
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}