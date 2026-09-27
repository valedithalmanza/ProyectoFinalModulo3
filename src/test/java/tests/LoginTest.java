package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

/**
 * Contains the tests for the OrangeHRM login functionality.
 */
public class LoginTest extends BaseTest {

    /**
     * Verifies that a user can log in with valid credentials
     * and is redirected to the Dashboard.
     */
    @Test
    public void loginWithValidCredentials()  {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("Admin", "admin123");


        Assert.assertTrue(
                driver.getCurrentUrl().contains("dashboard"),
                "The user was not redirected to the Dashboard"
        );
    }
}