package com.orangehrm.tests;

import com.orangehrm.base.BaseTest;
import com.orangehrm.core.waits.WaitUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Temporary example test, deleted when the real EmployeeCreationTest arrives.
 *
 * <p>Section 1 holds commented examples: uncommenting those lines shows where
 * each piece of the final test goes (import errors can be ignored, those pages
 * do not exist yet). Section 2 runs a small login-page check proving the
 * framework works end to end.</p>
 *
 * <p>This is the only class allowed to use line comments and a long Javadoc.
 * The comments below are the guideline itself, not leftover notes.</p>
 */
public class ExampleImplementationTest extends BaseTest {

    // SECTION 1: commented examples (reference only, uncomment to explore).
    //
    // EXAMPLE A: login test in its current shape.
    // It works, but the username and password are written inside the code.
    // Those values belong to the config file and are already loaded in setUp,
    // so the test uses the adminUsername and adminPassword fields instead.
    // @Test(description = "Valid login reaches the dashboard")
    // public void loginWithValidCredentials() {
    //     LoginPage loginPage = new LoginPage(driver);
    //     loginPage.login(adminUsername, adminPassword);
    //     Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
    // }
    //
    // EXAMPLE B: shape of a proposed final PIM test. Each step returns the next page,
    // so the test reads as: login, PIM, create, search, assert. Locators stay
    // inside pages, assertions stay here.
    // @Test(description = "Created employee appears in the results grid",
    //         dataProvider = "employees", dataProviderClass = EmployeeDataProvider.class)
    // public void createdEmployeeAppearsInGrid(Employee employee) {
    //     DashboardPage dashboard = new LoginPage(driver)
    //             .loginAs(adminUsername, adminPassword);
    //     AddEmployeePage addEmployee = dashboard.openPim().openAddEmployee();
    //     EmployeeListPage list = addEmployee.create(employee);
    //     list.searchByName(employee.getFirstName());
    //     Assert.assertTrue(list.contains(employee.getFirstName()));
    // }

    // SECTION 2: executable Core verification (removed together with Section 1).
    /**
     * Verifies the login page loads with the expected title.
     */
    @Test(description = "Login page loads with the expected title")
    public void baseUrlLoadsLoginPage() {
        WaitUtils waits = new WaitUtils(driver);
        Assert.assertTrue(waits.urlContains("auth/login"),
                "Current URL does not show the login page: " + driver.getCurrentUrl());
        Assert.assertTrue(waits.titleContains("OrangeHRM"),
                "Page title does not mention OrangeHRM: " + driver.getTitle());
    }
}
