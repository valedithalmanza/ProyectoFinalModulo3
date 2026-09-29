package com.orangehrm.core.utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.orangehrm.core.reporting.ReportManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * Captures screenshots and attaches them to the HTML report.
 */
public final class ScreenshotUtils {

    private static final Logger LOGGER = LogManager.getLogger(ScreenshotUtils.class);

    private ScreenshotUtils() {
    }

    /**
     * Captures the current browser viewport as Base64.
     *
     * @param driver driver holding the page to capture
     * @return the screenshot encoded as Base64, or {@code null} when unavailable
     */
    public static String captureBase64(WebDriver driver) {
        if (driver == null) {
            return null;
        }
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            LOGGER.warn("Screenshot capture failed: {}", e.getMessage());
            return null;
        }
    }

    /**
     * Captures the current page and attaches it to the running report test.
     *
     * @param driver driver holding the page to capture
     * @param status status logged together with the image
     * @param details message shown next to the image in the report
     */
    public static void attachToCurrentTest(WebDriver driver, Status status, String details) {
        if (ReportManager.getInstance().getTest() == null) {
            return;
        }
        String image = captureBase64(driver);
        if (image == null) {
            return;
        }
        ReportManager.getInstance().getTest().log(status, details,
                MediaEntityBuilder.createScreenCaptureFromBase64String(image).build());
    }
}
