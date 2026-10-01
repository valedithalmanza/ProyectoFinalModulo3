package com.orangehrm.core.utils;

/**
 * Generates the runtime-unique part of test data.
 */
public final class DataGenerator {

    private DataGenerator() {
    }

    /**
     * Builds an identifier unique to a single data provider invocation.
     *
     * @return a new run identifier on every call
     */
    public static String newRunId() {
        return "T" + Long.toString(System.currentTimeMillis(), 36).toUpperCase()
                + Long.toString(Math.abs(System.nanoTime() % 1296), 36).toUpperCase();
    }

    /**
     * Builds a numeric employee number unique to a single data provider invocation.
     *
     * @return a new numeric identifier on every call
     */
    public static String newEmployeeNumber() {
        return String.format("%06d", Math.abs(System.nanoTime() % 1000000L));
    }
}
