package com.orangehrm.core.waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

/**
 * Centralizes every explicit wait of the framework.
 */
public class WaitUtils {

    private final WebDriverWait wait;

    /**
     * Creates waits with an explicit timeout.
     *
     * @param driver driver used to poll the page
     * @param timeoutSeconds maximum wait per condition, in seconds
     */
    public WaitUtils(WebDriver driver, long timeoutSeconds) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
    }

    /**
     * Waits until the element is present and visible.
     *
     * @param locator locator of the expected element
     * @return the visible element
     */
    public WebElement visibilityOf(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits until the element can be clicked.
     *
     * @param locator locator of the element to click
     * @return the clickable element
     */
    public WebElement clickableOf(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits until the page title contains the expected text.
     *
     * @param text fragment expected inside the title
     * @return {@code true} when the title matched before the timeout
     */
    public boolean titleContains(String text) {
        return wait.until(ExpectedConditions.titleContains(text));
    }

    /**
     * Waits until the current URL contains the expected fragment.
     *
     * @param fragment fragment expected inside the URL
     * @return {@code true} when the URL matched before the timeout
     */
    public boolean urlContains(String fragment) {
        return wait.until(ExpectedConditions.urlContains(fragment));
    }

    /**
     * Waits until the given condition holds.
     *
     * @param condition condition evaluated against the driver
     * @param <T> result type of the condition
     * @return the condition result
     */
    public <T> T until(Function<WebDriver, T> condition) {
        return wait.until(condition);
    }
}
