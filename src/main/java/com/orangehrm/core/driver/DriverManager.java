package com.orangehrm.core.driver;

import org.openqa.selenium.WebDriver;

/**
 * Single entry point for driver lifecycle.
 */
public final class DriverManager {

    private static final ThreadLocal<WebDriver> DRIVER_HOLDER = new ThreadLocal<>();

    private DriverManager() {
    }

    /**
     * Creates a driver for the requested browser and binds it to the current thread.
     *
     * @param browser browser name, case-insensitive (for example {@code chrome})
     * @return the driver bound to the current thread
     */
    public static WebDriver startSession(String browser) {
        WebDriver driver = DriverFactory.create(browser);
        DRIVER_HOLDER.set(driver);
        return driver;
    }

    /**
     * Returns the driver bound to the current thread.
     *
     * @return the current driver, or {@code null} when none was bound yet
     */
    public static WebDriver getDriver() {
        return DRIVER_HOLDER.get();
    }

    /**
     * Quits the current thread driver and releases the binding.
     *
     * <p>Null-safe so teardown stays green even when setup never completed.</p>
     */
    public static void quitDriver() {
        WebDriver driver = DRIVER_HOLDER.get();
        if (driver != null) {
            driver.quit();
        }
        DRIVER_HOLDER.remove();
    }
}
