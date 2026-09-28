package com.orangehrm.base;

import com.aventstack.extentreports.Status;
import com.orangehrm.core.config.ConfigReader;
import com.orangehrm.core.driver.DriverManager;
import com.orangehrm.core.reporting.ReportManager;
import com.orangehrm.core.utils.ScreenshotUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * Lifecycle shared by every test of the suite.
 */
public abstract class BaseTest {

    private static final Logger LOGGER = LogManager.getLogger(BaseTest.class);

    /**
     * Driver of the current test, managed through the thread-safe holder.
     */
    protected WebDriver driver;

    /**
     * Administrator username loaded from the config file before each test.
     */
    protected String adminUsername;

    /**
     * Administrator password loaded from the config file before each test.
     */
    protected String adminPassword;

    /**
     * Initializes the HTML report once for the whole suite.
     *
     * @throws Exception when the reporter cannot be initialized
     */
    @BeforeSuite
    public void setUpSuite() throws Exception {
        ReportManager.init(ConfigReader.getReportPath(), ConfigReader.getReportName());
    }

    /**
     * Opens a fresh browser on the base URL before each test.
     *
     * @param result TestNG result carrying the test name for the report
     * @param browser browser name from the suite XML, or the browser system property
     */
    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(ITestResult result, @Optional("chrome") String browser) {
        String effectiveBrowser = System.getProperty("browser", browser);
        String testName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();
        ReportManager.getInstance().startTest(description == null ? testName : description);

        driver = DriverManager.startSession(effectiveBrowser);
        adminUsername = ConfigReader.getAdminUsername();
        adminPassword = ConfigReader.getAdminPassword();
        driver.manage().window().maximize();
        driver.get(ConfigReader.getBaseUrl());
        LOGGER.info("Browser {} opened on {}", effectiveBrowser, ConfigReader.getBaseUrl());
    }

    /**
     * Attaches evidence on failure and always closes the browser.
     *
     * @param result TestNG result telling whether the test failed
     */
    @AfterMethod
    public void tearDown(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                ScreenshotUtils.attachToCurrentTest(driver, Status.FAIL, "Failure evidence");
            }
        } finally {
            DriverManager.quitDriver();
            driver = null;
            LOGGER.info("Browser closed");
        }
    }

    /**
     * Writes the HTML report after the whole suite finishes.
     */
    @AfterSuite
    public void tearDownSuite() {
        ReportManager.getInstance().flush();
    }
}
