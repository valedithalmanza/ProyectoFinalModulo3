package com.orangehrm.tests;

import com.orangehrm.base.BaseTest;
import com.orangehrm.business.models.Employee;
import com.orangehrm.core.utils.DataGenerator;
import com.orangehrm.dataproviders.EmployeeDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

/**
 * Guards the contract between the JSON data file and the test layer.
 */
public class EmployeeDataContractTest extends BaseTest {

    /**
     * Verifies the data file provides exactly the two employees of the spec.
     *
     * @throws FileNotFoundException when the data file is missing
     */
    @Test(description = "Employee data file provides two rows")
    public void employeesFileProvidesTwoRows() throws FileNotFoundException {
        Assert.assertEquals(new EmployeeDataProvider().employees().length, 2,
                "The business case requires exactly two employees");
    }

    /**
     * Verifies every employee row is complete and unique for this run.
     *
     * @param employee one employee row stamped with the run identifier
     */
    @Test(description = "Employee row is complete and unique for this run",
            dataProvider = "employees", dataProviderClass = EmployeeDataProvider.class)
    public void employeeRowIsCompleteAndUnique(Employee employee) {
        Assert.assertNotNull(employee.getFirstName(), "First name is missing");
        Assert.assertFalse(employee.getFirstName().isBlank(), "First name is blank");
        Assert.assertNotNull(employee.getLastName(), "Last name is missing");
        Assert.assertFalse(employee.getLastName().isBlank(), "Last name is blank");
        Assert.assertNotNull(employee.getUsername(), "Username is missing");
        Assert.assertFalse(employee.getUsername().isBlank(), "Username is blank");
        Assert.assertNotNull(employee.getPassword(), "Password is missing");
        Assert.assertFalse(employee.getPassword().isBlank(), "Password is blank");
        Assert.assertTrue(employee.getStatus().equals("Enabled")
                        || employee.getStatus().equals("Disabled"),
                "Status must be Enabled or Disabled but was: " + employee.getStatus());
        Assert.assertTrue(employee.getFirstName().contains(DataGenerator.getRunId()),
                "First name lacks the run identifier and would collide: " + employee);
        Assert.assertTrue(employee.getUsername().contains(DataGenerator.getRunId().toLowerCase()),
                "Username lacks the run identifier and would collide: " + employee);
    }
}
