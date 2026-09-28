package com.orangehrm.core.utils;

/**
 * Generates the runtime-unique part of test data.
 */
public final class DataGenerator {

    /**
     * Identifier shared by every value generated during the current run.
     */
    private static final String RUN_ID = "T" + Long.toString(System.currentTimeMillis(), 36).toUpperCase();

    private DataGenerator() {
    }

    /**
     * Returns the identifier shared by the current run.
     *
     * @return the run identifier, unique per execution
     */
    public static String getRunId() {
        return RUN_ID;
    }

    /**
     * Builds a first name that is unique for the current run.
     *
     * @param baseName stable first name read from the data file
     * @return the base name suffixed with the run identifier
     */
    public static String uniqueFirstName(String baseName) {
        return baseName + RUN_ID;
    }

    /**
     * Builds a username that is unique for the current run.
     *
     * @param baseUsername stable username read from the data file
     * @return the base username suffixed with the run identifier
     */
    public static String uniqueUsername(String baseUsername) {
        return baseUsername + "." + RUN_ID.toLowerCase();
    }
}
