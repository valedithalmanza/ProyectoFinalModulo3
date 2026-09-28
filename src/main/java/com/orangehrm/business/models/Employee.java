package com.orangehrm.business.models;

/**
 * One row of the employee test data.
 */
public class Employee {

    private String firstName;
    private String middleName;
    private String lastName;
    private String employeeId;
    private String username;
    private String password;
    private String status;

    /**
     * Creates an empty employee for JSON deserialization.
     */
    public Employee() {
    }

    /**
     * Creates a fully defined employee.
     *
     * @param firstName employee first name
     * @param middleName employee middle name
     * @param lastName employee last name
     * @param employeeId employee identifier
     * @param username login username
     * @param password login password
     * @param status account status
     */
    public Employee(String firstName, String middleName, String lastName, String employeeId,
                    String username, String password, String status) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.employeeId = employeeId;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    /**
     * Returns the employee first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the employee middle name.
     *
     * @return the middle name
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Returns the employee last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the employee identifier.
     *
     * @return the employee id
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * Returns the login username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the login password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the account status.
     *
     * @return the status, for example {@code Enabled}
     */
    public String getStatus() {
        return status;
    }

    /**
     * Copies this employee with the run identifier applied to its unique fields.
     *
     * @param runId identifier unique to the current execution
     * @return a new employee ready to be created in this run
     */
    public Employee withRunId(String runId) {
        return new Employee(firstName + runId, middleName, lastName, employeeId,
                username + "." + runId.toLowerCase(), password, status);
    }

    /**
     * Returns a log-safe summary without the password.
     *
     * @return the employee full name and username
     */
    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + username + ")";
    }
}
