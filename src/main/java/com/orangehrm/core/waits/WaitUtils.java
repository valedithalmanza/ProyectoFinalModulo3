package com.orangehrm.core.waits;

import com.orangehrm.core.config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Centralizes every explicit wait of the framework.
 */
public class WaitUtils {

    private final WebDriver driver;
    private final long timeoutSeconds;

    /**
     * Creates waits with the timeout from the framework configuration.
     *
     * @param driver driver used to poll the page
     */
    public WaitUtils(WebDriver driver) {
        this(driver, ConfigReader.getTimeoutSeconds());
    }

    /**
     * Creates waits with an explicit timeout.
     *
     * @param driver driver used to poll the page
     * @param timeoutSeconds maximum wait per condition, in seconds
     */
    public WaitUtils(WebDriver driver, long timeoutSeconds) {
        this.driver = driver;
        this.timeoutSeconds = timeoutSeconds;
    }

    /**
     * Waits until the element is present and visible.
     *
     * @param locator locator of the expected element
     * @return the visible element
     */
    public WebElement visibilityOf(By locator) {
        return newWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits until the element can be clicked.
     *
     * @param locator locator of the element to click
     * @return the clickable element
     */
    public WebElement clickableOf(By locator) {
        return newWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits until the page title contains the expected text.
     *
     * @param text fragment expected inside the title
     * @return {@code true} when the title matched before the timeout
     */
    public boolean titleContains(String text) {
        return newWait().until(ExpectedConditions.titleContains(text));
    }

    /**
     * Waits until the current URL contains the expected fragment.
     *
     * @param fragment fragment expected inside the URL
     * @return {@code true} when the URL matched before the timeout
     */
    public boolean urlContains(String fragment) {
        return newWait().until(ExpectedConditions.urlContains(fragment));
    }

    /**
     * Builds a fresh wait for a single condition.
     *
     * @return a wait bound to this helper driver and timeout
     */
    private WebDriverWait newWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
    }
}
