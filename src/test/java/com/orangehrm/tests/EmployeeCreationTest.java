package com.orangehrm.tests;

import com.orangehrm.base.BaseTest;
import com.orangehrm.business.models.Employee;
import com.orangehrm.business.pages.EmployeeListPage;
import com.orangehrm.business.pages.LoginPage;
import com.orangehrm.dataproviders.EmployeeDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Tests the employee creation flow.
 */
public class EmployeeCreationTest extends BaseTest {

    /**
     * Verifies a created employee appears in the results grid.
     *
     * @param employee employee data
     */
    @Test(
            description = "Created employee appears in the results grid",
            dataProvider = "employees",
            dataProviderClass = EmployeeDataProvider.class
    )
    public void createdEmployeeAppearsInGrid(Employee employee) {
        EmployeeListPage employeeList = new LoginPage(driver)
                .loginAs(adminUsername, adminPassword)
                .openPim()
                .openAddEmployee()
                .createEmployee(employee);

        employeeList.searchByName(employee.getFirstName());

        Assert.assertTrue(employeeList.containsEmployee(employee.getFirstName()),
                "Created employee is missing from the results grid: " + employee);
    }
}
