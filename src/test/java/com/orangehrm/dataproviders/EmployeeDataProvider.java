package com.orangehrm.dataproviders;

import com.orangehrm.business.models.Employee;
import com.orangehrm.core.utils.DataGenerator;
import com.orangehrm.core.utils.JsonTestDataHelper;
import org.testng.annotations.DataProvider;

import java.io.FileNotFoundException;

/**
 * Supplies employee rows to the business-case tests.
 */
public class EmployeeDataProvider {

    /**
     * Path of the employee data file relative to the repository root.
     */
    public static final String EMPLOYEES_JSON = "src/test/resources/testdata/employees.json";

    /**
     * Returns one {@code Employee} per JSON row with run uniqueness applied.
     *
     * @return the employee rows ready to inject into tests
     * @throws FileNotFoundException when the data file is missing
     */
    @DataProvider(name = "employees")
    public Object[] employees() throws FileNotFoundException {
        String runId = DataGenerator.newRunId();
        Object[] rows = JsonTestDataHelper.getInstance().getTestData(EMPLOYEES_JSON, Employee.class);
        Object[] stamped = new Object[rows.length];
        for (int i = 0; i < rows.length; i++) {
            stamped[i] = ((Employee) rows[i])
                    .withUniqueValues(runId, DataGenerator.newEmployeeNumber());
        }
        return stamped;
    }
}
