package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Base test that contains the common setup and teardown
 * used by the test classes.
 */
public class BaseTest {

    protected WebDriver driver;

    /**
     * Initializes the WebDriver, maximizes the browser window
     * and opens the OrangeHRM login page before each test.
     */
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    /**
     * Closes the browser after each test.
     */
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}