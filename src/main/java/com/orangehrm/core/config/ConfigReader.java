package com.orangehrm.core.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Reads framework settings from the test classpath.
 */
public final class ConfigReader {

    private static final Properties PROPERTIES = loadProperties();

    private ConfigReader() {
    }

    /**
     * Returns the raw value of a property key.
     *
     * @param key property key to look up
     * @return the configured value, or {@code null} when the key is absent
     */
    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    /**
     * Returns the application base URL under test.
     *
     * @return the configured base URL
     * @throws IllegalStateException when no base URL is configured
     */
    public static String getBaseUrl() {
        String baseUrl = get(AppConstants.BASE_URL_KEY);
        if (baseUrl == null || baseUrl.isBlank()) {
            throw new IllegalStateException(
                    "Missing required property '" + AppConstants.BASE_URL_KEY + "' in "
                            + AppConstants.CONFIG_FILE);
        }
        return baseUrl;
    }

    /**
     * Returns the administrator username used to log in.
     *
     * @return the configured admin username
     * @throws IllegalStateException when no admin username is configured
     */
    public static String getAdminUsername() {
        String username = get(AppConstants.ADMIN_USERNAME_KEY);
        if (username == null || username.isBlank()) {
            throw new IllegalStateException(
                    "Missing required property '" + AppConstants.ADMIN_USERNAME_KEY + "' in "
                            + AppConstants.CONFIG_FILE);
        }
        return username;
    }

    /**
     * Returns the administrator password used to log in.
     *
     * @return the configured admin password
     * @throws IllegalStateException when no admin password is configured
     */
    public static String getAdminPassword() {
        String password = get(AppConstants.ADMIN_PASSWORD_KEY);
        if (password == null || password.isBlank()) {
            throw new IllegalStateException(
                    "Missing required property '" + AppConstants.ADMIN_PASSWORD_KEY + "' in "
                            + AppConstants.CONFIG_FILE);
        }
        return password;
    }

    /**
     * Returns the default explicit-wait timeout in seconds.
     *
     * @return the configured timeout, or the framework default when absent
     */
    public static long getTimeoutSeconds() {
        String raw = get(AppConstants.TIMEOUT_SECONDS_KEY);
        if (raw == null || raw.isBlank()) {
            return AppConstants.DEFAULT_TIMEOUT_SECONDS;
        }
        return Long.parseLong(raw.trim());
    }

    /**
     * Returns the directory where the HTML report is written.
     *
     * @return the configured report directory, or the framework default
     */
    public static String getReportPath() {
        String path = get(AppConstants.REPORT_PATH_KEY);
        return (path == null || path.isBlank()) ? AppConstants.DEFAULT_REPORT_PATH : path;
    }

    /**
     * Returns the display name used inside the HTML report.
     *
     * @return the configured report name, or the framework default
     */
    public static String getReportName() {
        String name = get(AppConstants.REPORT_NAME_KEY);
        return (name == null || name.isBlank()) ? AppConstants.DEFAULT_REPORT_NAME : name;
    }

    /**
     * Loads the properties file from the classpath exactly once.
     *
     * @return the loaded properties, possibly empty when the file is missing
     */
    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream stream =
                     ConfigReader.class.getClassLoader().getResourceAsStream(AppConstants.CONFIG_FILE)) {
            if (stream != null) {
                properties.load(stream);
            }
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Unable to read " + AppConstants.CONFIG_FILE + " from the classpath", e);
        }
        return properties;
    }
}
