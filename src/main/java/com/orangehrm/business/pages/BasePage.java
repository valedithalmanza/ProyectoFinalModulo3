package com.orangehrm.business.pages;

import com.orangehrm.core.config.ConfigReader;
import com.orangehrm.core.waits.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;

import java.util.List;

/**
 * Foundation inherited by every page object.
 */
public abstract class BasePage {

    private final WebDriver driver;
    private final WaitUtils waits;

    private final By successToast = By.cssSelector(".oxd-toast--success");

    /**
     * Creates a page bound to the given driver.
     *
     * @param driver driver currently showing this page
     */
    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.waits = new WaitUtils(driver, ConfigReader.getTimeoutSeconds());
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
     * Waits until the success confirmation appears.
     */
    protected void waitForSuccessToast() {
        waits.visibilityOf(successToast);
    }

    /**
     * Waits until the element is present and visible.
     *
     * @param locator locator of the expected element
     */
    protected void waitForVisible(By locator) {
        waits.visibilityOf(locator);
    }

    /**
     * Waits until the element is gone or hidden.
     *
     * @param locator locator of the blocking element
     */
    protected void waitForInvisible(By locator) {
        waits.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Reads the selection state of an input without requiring visibility.
     *
     * @param locator locator of the input to check
     * @return true when the input is selected
     */
    protected boolean isSelected(By locator) {
        return driver.findElement(locator).isSelected();
    }

    /**
     * Returns all elements matching the locator without waiting.
     *
     * @param locator locator of the elements to read
     * @return the matching elements, possibly empty
     */
    protected List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    /**
     * Waits until any of the given elements is visible.
     *
     * @param locators candidates to wait for, the first visible one wins
     */
    protected void waitForAnyVisible(By... locators) {
        waits.until(ExpectedConditions.or(Arrays.stream(locators)
                .map(ExpectedConditions::visibilityOfElementLocated)
                .toArray(ExpectedCondition<?>[]::new)));
    }

    /**
     * Clicks an element after waiting until it is visible and accepts clicks.
     *
     * @param locator locator of the element to click
     */
    protected void clickElement(By locator) {
        waits.visibilityOf(locator);
        waits.clickableOf(locator).click();
    }

    /**
     * Clears a field and types the given value into it.
     *
     * @param locator locator of the input field
     * @param value text to type into the field
     */
    protected void typeText(By locator, String value) {
        WebElement field = waits.visibilityOf(locator);
        field.clear();
        field.sendKeys(Keys.chord(selectAllModifier(), "a"));
        field.sendKeys(Keys.DELETE);
        field.sendKeys(value);
    }

    /**
     * Returns the select-all modifier for the current operating system.
     *
     * @return command on macOS, control otherwise
     */
    private Keys selectAllModifier() {
        String os = System.getProperty("os.name", "").toLowerCase();
        return os.contains("mac") ? Keys.COMMAND : Keys.CONTROL;
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
