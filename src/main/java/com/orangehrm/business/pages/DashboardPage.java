package com.orangehrm.business.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Represents the OrangeHRM Dashboard page.
 */
public class DashboardPage extends BasePage {

    private final By pimMenu = By.xpath("//a[contains(@href,'/pim/viewPimModule')]");

    /**
     * Creates a DashboardPage using the current WebDriver.
     *
     * @param driver WebDriver used to interact with the browser
     */
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens the PIM module.
     *
     * @return EmployeeListPage
     */
    public EmployeeListPage openPim() {
        clickElement(pimMenu);
        return new EmployeeListPage(getDriver());
    }
}