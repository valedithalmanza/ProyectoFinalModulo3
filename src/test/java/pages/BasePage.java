package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Base page that contains common methods used by the Page Objects.
 */
public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    /**
     * Creates a BasePage using the current WebDriver
     * and initializes the explicit wait.
     *
     * @param driver WebDriver used to interact with the browser
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Waits until an element is visible on the page.
     *
     * @param locator locator of the element
     * @return the visible WebElement
     */
    protected WebElement waitForElement(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    /**
     * Waits until an element is clickable and clicks it.
     *
     * @param locator locator of the element to click
     */
    protected void click(By locator) {
        wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        ).click();
    }
}