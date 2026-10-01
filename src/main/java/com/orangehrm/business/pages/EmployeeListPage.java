package com.orangehrm.business.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

/**
 * Represents the OrangeHRM Employee List page.
 */
public class EmployeeListPage extends BasePage {

    private final By addEmployeeTab = By.xpath(
            "//a[normalize-space()='Add Employee' or normalize-space()='Agregar Empleado']"
    );

    private final By employeeNameInput = By.xpath(
            "//label[normalize-space()='Employee Name']" +
                    "/parent::div/following-sibling::div//input"
    );

    private final By nameHintOption = By.cssSelector(".oxd-autocomplete-option");

    private final By searchButton =
            By.xpath("//button[normalize-space()='Search']");

    private final By resultRows = By.cssSelector(".oxd-table-card");

    private final By noRecordsMessage =
            By.xpath("//span[normalize-space()='No Records Found']");

    /**
     * Creates an EmployeeListPage using the current WebDriver.
     *
     * @param driver WebDriver used to interact with the browser
     */
    public EmployeeListPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens the Add Employee page.
     *
     * @return AddEmployeePage
     */
    public AddEmployeePage openAddEmployee() {
        clickElement(addEmployeeTab);
        AddEmployeePage addEmployee = new AddEmployeePage(getDriver());
        addEmployee.waitForLoad();
        return addEmployee;
    }

    /**
     * Waits until the employee list is displayed.
     */
    public void waitForLoad() {
        waitForVisible(employeeNameInput);
    }

    /**
     * Searches the list by employee name.
     *
     * @param name employee name to search for
     */
    public void searchByName(String name) {
        typeText(employeeNameInput, name);
        clickElement(nameHintOption);
        clickElement(searchButton);
        waitForResults();
    }

    /**
     * Checks whether any result row mentions the given name.
     *
     * @param name employee name to look for
     * @return true when a result row contains the name
     */
    public boolean containsEmployee(String name) {
        StaleElementReferenceException stale = null;
        for (int attempt = 0; attempt < 3; attempt++) {
            try {
                return findElements(resultRows).stream()
                        .anyMatch(row -> row.getText().contains(name));
            } catch (StaleElementReferenceException e) {
                stale = e;
                waitForResults();
            }
        }
        throw stale;
    }

    /**
     * Waits until the grid shows rows or the empty message.
     */
    private void waitForResults() {
        waitForAnyVisible(resultRows, noRecordsMessage);
    }
}