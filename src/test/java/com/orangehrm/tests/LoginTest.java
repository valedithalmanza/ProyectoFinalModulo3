package com.orangehrm.tests;

import com.orangehrm.base.BaseTest;
import com.orangehrm.business.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/** Contains the tests for the OrangeHRM login functionality. */
public class LoginTest extends BaseTest {

    /** Verifies that a user can log in with valid credentials and is redirected to the Dashboard. */
    @Test(description = "Login with valid credentials")
    public void loginWithValidCredentials() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginAs(adminUsername, adminPassword);

        Assert.assertTrue(
                driver.getCurrentUrl().contains("dashboard"),
                "The user was not redirected to the Dashboard"
        );
    }
}