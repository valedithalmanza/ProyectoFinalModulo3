package com.orangehrm.business.pages;
import com.orangehrm.business.models.Employee;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Represents the OrangeHRM Add Employee page.
 */
public class AddEmployeePage extends BasePage {

    private final By firstNameInput =
            By.name("firstName");

    private final By middleNameInput =
            By.name("middleName");

    private final By lastNameInput =
            By.name("lastName");

    private final By employeeIdInput = By.xpath(
            "//label[normalize-space()='Employee Id']" +
                    "/parent::div/following-sibling::div//input"
    );

    private final By createLoginDetailsSwitch = By.xpath(
            "//p[normalize-space()='Create Login Details']" +
                    "/following-sibling::div//span[contains(@class,'oxd-switch-input')]"
    );

    private final By usernameInput = By.xpath(
            "//label[contains(normalize-space(),'Username')]" +
                    "/parent::div/following-sibling::div//input"
    );

    private final By enabledStatusRadio = By.xpath(
            "//input[@type='radio' and @value='1']" +
                    "/following-sibling::span[contains(@class,'oxd-radio-input')]"
    );

    private final By disabledStatusRadio = By.xpath(
            "//input[@type='radio' and @value='2']" +
                    "/following-sibling::span[contains(@class,'oxd-radio-input')]"
    );

    private final By passwordInput = By.xpath(
            "//label[contains(normalize-space(),'Password') " +
                    "and not(contains(normalize-space(),'Confirm'))]" +
                    "/parent::div/following-sibling::div//input[@type='password']"
    );

    private final By confirmPasswordInput = By.xpath(
            "//label[contains(normalize-space(),'Confirm Password')]" +
                    "/parent::div/following-sibling::div//input[@type='password']"
    );

    private final By saveButton =
            By.xpath("//button[@type='submit' and normalize-space()='Save']");

    /**
     * Creates an AddEmployeePage using the current WebDriver.
     *
     * @param driver WebDriver used to interact with the browser
     */
    public AddEmployeePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Enters the employee first name.
     *
     * @param firstName employee first name
     */
    public void enterFirstName(String firstName) {
        typeText(firstNameInput, firstName);
    }

    /**
     * Enters the employee middle name.
     *
     * @param middleName employee middle name
     */
    public void enterMiddleName(String middleName) {
        typeText(middleNameInput, middleName);
    }

    /**
     * Enters the employee last name.
     *
     * @param lastName employee last name
     */
    public void enterLastName(String lastName) {
        typeText(lastNameInput, lastName);
    }

    /**
     * Enters the employee ID.
     *
     * @param employeeId employee identifier
     */
    public void enterEmployeeId(String employeeId) {
        typeText(employeeIdInput, employeeId);
    }

    /**
     * Enables the Create Login Details option.
     */
    public void enableCreateLoginDetails() {
        clickElement(createLoginDetailsSwitch);
    }

    /**
     * Enters the username.
     *
     * @param username employee username
     */
    public void enterUsername(String username) {
        typeText(usernameInput, username);
    }

    /**
     * Selects the employee user status.
     *
     * @param status user status
     */
    public void selectStatus(String status) {
        if ("Disabled".equalsIgnoreCase(status)) {
            clickElement(disabledStatusRadio);
        } else {
            clickElement(enabledStatusRadio);
        }
    }

    /**
     * Enters the password.
     *
     * @param password employee password
     */
    public void enterPassword(String password) {
        typeText(passwordInput, password);
    }

    /**
     * Confirms the password.
     *
     * @param password employee password
     */
    public void enterConfirmPassword(String password) {
        typeText(confirmPasswordInput, password);
    }

    /**
     * Saves the employee.
     */
    public void clickSave() {
        clickElement(saveButton);
    }

    /**
     * Creates a new employee with login details.
     *
     * @param employee employee data
     */
    public void createEmployee(Employee employee) {
        enterFirstName(employee.getFirstName());
        enterMiddleName(employee.getMiddleName());
        enterLastName(employee.getLastName());
        enterEmployeeId(employee.getEmployeeId());

        enableCreateLoginDetails();

        enterUsername(employee.getUsername());
        selectStatus(employee.getStatus());
        enterPassword(employee.getPassword());
        enterConfirmPassword(employee.getPassword());

        clickSave();
    }
}