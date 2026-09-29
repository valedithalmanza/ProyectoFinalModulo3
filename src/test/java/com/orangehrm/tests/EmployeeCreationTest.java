package com.orangehrm.tests;

import com.orangehrm.base.BaseTest;
import com.orangehrm.business.models.Employee;
import com.orangehrm.business.pages.AddEmployeePage;
import com.orangehrm.business.pages.DashboardPage;
import com.orangehrm.business.pages.EmployeeListPage;
import com.orangehrm.business.pages.LoginPage;
import com.orangehrm.dataproviders.EmployeeDataProvider;
import org.testng.annotations.Test;

/**
 * Tests the employee creation flow.
 */
public class EmployeeCreationTest extends BaseTest {

    /**
     * Creates an employee using test data.
     *
     * @param employee employee data
     */
    @Test(
            description = "Create employee with login details",
            dataProvider = "employees",
            dataProviderClass = EmployeeDataProvider.class
    )
    public void createEmployee(Employee employee) {

        DashboardPage dashboard = new LoginPage(driver)
                .loginAs(adminUsername, adminPassword);

        EmployeeListPage employeeListPage =
                dashboard.openPim();

        AddEmployeePage addEmployeePage =
                employeeListPage.openAddEmployee();

        addEmployeePage.createEmployee(employee);
    }
}