package com.orangehrm.core.reporting;

import com.aventstack.extentreports.Status;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * Mirrors TestNG outcomes into the HTML report.
 */
public class TestListener extends TestListenerAdapter {

    /**
     * Logs a passing test in the report.
     *
     * @param result TestNG result of the finished test
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        logSafely(Status.PASS, "Test passed");
    }

    /**
     * Logs a failing test together with its error message.
     *
     * @param result TestNG result holding the thrown assertion or error
     */
    @Override
    public void onTestFailure(ITestResult result) {
        logSafely(Status.FAIL, "Test failed");
        if (result.getThrowable() != null) {
            logSafely(Status.FAIL, result.getThrowable().getMessage());
        }
    }

    /**
     * Logs a skipped test in the report.
     *
     * @param result TestNG result of the skipped test
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        logSafely(Status.SKIP, "Test skipped");
    }

    /**
     * Writes a line only when a report node exists for the current thread.
     *
     * @param status status of the line
     * @param details message shown in the report
     */
    private void logSafely(Status status, String details) {
        if (ReportManager.getInstance().getTest() != null) {
            ReportManager.getInstance().getTest().log(status, details);
        }
    }
}
