package com.orangehrm.core.config;

/**
 * Central constants of the framework.
 */
public final class AppConstants {

    /**
     * Name of the properties file loaded from the test classpath.
     */
    public static final String CONFIG_FILE = "config.properties";

    /**
     * Property key for the application base URL.
     */
    public static final String BASE_URL_KEY = "baseUrl";

    /**
     * Property key for the default explicit-wait timeout in seconds.
     */
    public static final String TIMEOUT_SECONDS_KEY = "timeoutSeconds";

    /**
     * Property key for the ExtentReports output directory.
     */
    public static final String REPORT_PATH_KEY = "reportPath";

    /**
     * Property key for the ExtentReports report name.
     */
    public static final String REPORT_NAME_KEY = "reportName";

    /**
     * Property key for the administrator username used to log in.
     */
    public static final String ADMIN_USERNAME_KEY = "adminUsername";

    /**
     * Property key for the administrator password used to log in.
     */
    public static final String ADMIN_PASSWORD_KEY = "adminPassword";

    /**
     * Fallback explicit-wait timeout used when the properties file is absent.
     */
    public static final long DEFAULT_TIMEOUT_SECONDS = 10L;

    /**
     * Fallback report directory used when the properties file is absent.
     */
    public static final String DEFAULT_REPORT_PATH = "target/reports";

    /**
     * Fallback report name used when the properties file is absent.
     */
    public static final String DEFAULT_REPORT_NAME = "OrangeHRM";

    /**
     * File name of the generated ExtentReports HTML document.
     */
    public static final String REPORT_FILE_NAME = "ExtentReport.html";

    private AppConstants() {
    }
}
