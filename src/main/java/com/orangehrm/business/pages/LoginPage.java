package com.orangehrm.business.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** Represents the OrangeHRM login page. */
public class LoginPage extends BasePage {
    private final By usernameInput = By.name("username");
    private final By passwordInput = By.name("password");
    private final By loginButton = By.cssSelector("button[type='submit']");

    /** Creates a LoginPage using the current WebDriver. */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /** Enters the username in the username field. */
    public void enterUsername(String username) {
        typeText(usernameInput, username);
    }

    /** Enters the password in the password field. */
    public void enterPassword(String password) {
        typeText(passwordInput, password);
    }

    /** Clicks the login button. */
    public void clickLogin() {
        clickElement(loginButton);
    }

    /** Performs the login and returns the Dashboard page. */
    public DashboardPage loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        return new DashboardPage(getDriver());
    }
}