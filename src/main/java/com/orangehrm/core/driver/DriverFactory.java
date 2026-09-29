package com.orangehrm.core.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

/**
 * Creates configured browser drivers.
 */
public final class DriverFactory {

    private DriverFactory() {
    }

    /**
     * Creates a new driver for the requested browser.
     *
     * @param browser browser name, case-insensitive (for example {@code chrome})
     * @return a ready-to-use driver instance
     * @throws IllegalArgumentException when the browser name is not supported
     */
    public static WebDriver create(String browser) {
        String normalized = browser == null ? "" : browser.trim().toLowerCase();
        switch (normalized) {
            case "chrome":
                return new ChromeDriver(chromeOptionsWithoutPasswordManager());
            case "firefox":
                return new FirefoxDriver();
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    /**
     * Builds Chrome options with the built-in password manager disabled.
     *
     * @return Chrome options safe for automation with demo credentials
     */
    private static ChromeOptions chromeOptionsWithoutPasswordManager() {
        Map<String, Object> preferences = new HashMap<>();
        preferences.put("credentials_enable_service", false);
        preferences.put("profile.password_manager_enabled", false);
        preferences.put("profile.password_manager_leak_detection", false);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", preferences);
        options.addArguments("--disable-features=PasswordLeakDetection,AutofillServerCommunication");
        return options;
    }
}
