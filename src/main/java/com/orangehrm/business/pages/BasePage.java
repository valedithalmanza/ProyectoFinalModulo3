package com.orangehrm.business.pages;

import com.orangehrm.core.waits.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Foundation inherited by every page object.
 */
public abstract class BasePage {

    private final WebDriver driver;
    private final WaitUtils waits;

    /**
     * Creates a page bound to the given driver.
     *
     * @param driver driver currently showing this page
     */
    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.waits = new WaitUtils(driver);
    }

    /**
     * Returns the driver bound to this page.
     *
     * @return the page driver
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Returns the waits helper bound to this page.
     *
     * @return the shared waits of this page
     */
    protected WaitUtils getWaits() {
        return waits;
    }

    /**
     * Clicks an element after waiting until it accepts clicks.
     *
     * @param locator locator of the element to click
     */
    protected void clickElement(By locator) {
        waits.clickableOf(locator).click();
    }

    /**
     * Clears a field and types the given value into it.
     *
     * @param locator locator of the input field
     * @param value text to type into the field
     */
    protected void typeText(By locator, String value) {
        waits.visibilityOf(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }

    /**
     * Reads the visible text of an element.
     *
     * @param locator locator of the element to read
     * @return the element visible text
     */
    protected String getText(By locator) {
        return waits.visibilityOf(locator).getText();
    }

    /**
     * Checks whether an element becomes visible within the wait timeout.
     *
     * @param locator locator of the element to check
     * @return {@code true} when the element is visible, {@code false} otherwise
     */
    protected boolean isDisplayed(By locator) {
        try {
            waits.visibilityOf(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
