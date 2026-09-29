package com.orangehrm.core.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.orangehrm.core.config.AppConstants;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Owns the single ExtentReports instance of the suite.
 */
public final class ReportManager {

    private static ExtentReports extentReport;
    private static String reportFilePath = "";
    private static String reportName;
    private static final ConcurrentHashMap<Long, ExtentTest> TEST_NODES = new ConcurrentHashMap<>();
    private static ReportManager instance;

    private ReportManager() throws Exception {
        createExtentReportInstance();
    }

    /**
     * Returns the shared manager, creating it on first use.
     *
     * @return the singleton manager
     */
    public static ReportManager getInstance() {
        if (instance == null) {
            synchronized (ReportManager.class) {
                if (instance == null) {
                    try {
                        instance = new ReportManager();
                    } catch (Exception e) {
                        throw new IllegalStateException("Unable to create ExtentReports instance", e);
                    }
                }
            }
        }
        return instance;
    }

    /**
     * Configures the report output before the suite starts.
     *
     * @param reportDir directory receiving the HTML document
     * @param name display name shown inside the report
     * @throws Exception when the reporter is already initialized
     */
    public static void init(String reportDir, String name) throws Exception {
        if (extentReport == null) {
            reportFilePath = reportDir + File.separator + AppConstants.REPORT_FILE_NAME;
            reportName = name;
        } else {
            throw new Exception("ExtentReports is already initialized");
        }
    }

    /**
     * Opens a report node for the current thread test.
     *
     * @param testName name shown for the test in the report
     * @return the created test node
     */
    public ExtentTest startTest(String testName) {
        ExtentTest test = extentReport.createTest(testName);
        TEST_NODES.put(Thread.currentThread().getId(), test);
        return test;
    }

    /**
     * Returns the report node of the current thread test.
     *
     * @return the current test node, or {@code null} when none was started
     */
    public ExtentTest getTest() {
        return TEST_NODES.get(Thread.currentThread().getId());
    }

    /**
     * Writes the accumulated report to disk.
     */
    public void flush() {
        extentReport.flush();
    }

    /**
     * Builds the reporter instance from the previously configured path.
     *
     * @throws Exception when {@code init} was never called
     */
    private void createExtentReportInstance() throws Exception {
        if (reportFilePath.isEmpty()) {
            throw new Exception("Call init with the report directory before using ReportManager");
        }
        new File(reportFilePath).getParentFile().mkdirs();
        extentReport = new ExtentReports();
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportFilePath);
        htmlReporter.config().setDocumentTitle("Automation Report " + reportName);
        htmlReporter.config().setReportName(reportName);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setEncoding("utf-8");
        extentReport.attachReporter(htmlReporter);
    }
}
