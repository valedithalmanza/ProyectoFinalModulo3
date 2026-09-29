package com.orangehrm.business.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Represents the OrangeHRM Employee List page.
 */
public class EmployeeListPage extends BasePage {

    private final By addEmployeeTab =
            By.xpath("//a[normalize-space()='Add Employee']");

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
        return new AddEmployeePage(getDriver());
    }
}