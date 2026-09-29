# OrangeHRM Automation

Test automation for the OrangeHRM demo (`opensource-demo.orangehrmlive.com`),
built with Java + Selenium + TestNG following the Page Object Model.

Business case covered: E2E test to log in, open the PIM module, create a new employee with
login details, search that employee in the list, and verify it shows up in the
results grid.

## Requirements

- Java 17 or newer (the project targets Java 17).
- Maven 3.8+.
- Chrome and/or Firefox installed. Browser drivers are resolved automatically
  by Selenium Manager — never commit driver executables.

## How to run

From this folder:

```bash
mvn clean test
```

That runs the full suite in Chrome and Firefox (one suite block per browser,
see `testng.xml`). The HTML report is written to `target/reports/`.

Single browser only (no suite file, `-Dtest` selects the classes):

```bash
mvn clean test -Dtest='ExampleImplementationTest,EmployeeDataContractTest'
```

That uses Chrome by default. For Firefox only, add `-Dbrowser=firefox`:

```bash
mvn clean test -Dtest='ExampleImplementationTest,EmployeeDataContractTest' -Dbrowser=firefox
```

Note: `-Dbrowser` applies to these direct runs only. The full suite
(`mvn clean test`) always runs both browsers as defined in `testng.xml`.

## Architecture (3 layers)

| Layer | Package | What goes here | What never goes here |
|---|---|---|---|
| Core | `com.orangehrm.core.*` | Driver lifecycle (`driver/`), explicit waits (`waits/`), settings (`config/`), JSON loading, unique-data generation, screenshots (`utils/`), HTML reporting (`reporting/`). Shared by everything, knows nothing about pages or tests. | Page locators, test assertions |
| Business | `com.orangehrm.business.*` | Page objects (`pages/`, one class per screen, locators live inside their page) and the `Employee` data model (`models/`). Pages chain forward: each action returns the next page. | Assertions, driver creation |
| Test | `com.orangehrm.*` (test sources) | Suite setup (`base/BaseTest`), data providers (`dataproviders/`), the business-case tests (`tests/`) and resources (`config.properties`, `testdata/employees.json`). Tests read like the business case. | Locators, `findElement` calls |

Flow of a test: `BaseTest` opens the browser → data provider injects one
`Employee` per JSON row → pages perform the steps → the test asserts.

## Test data

Employee data lives in `src/test/resources/testdata/employees.json` (2 rows,
keys match the `Employee` fields). The demo site keeps every created employee,
so the provider stamps each row with a run identifier (`DataGenerator`) to keep
names and usernames unique on every run — no file edits needed.

The admin login (`adminUsername`/`adminPassword`) lives in
`src/test/resources/config.properties` and is loaded into every test by
`BaseTest`. Tests use those fields, never hardcoded credentials.

## Conventions

- Code and identifiers in English.
- One-line Javadoc on every class and public method, only special cases could be multiline Javadocs, no rationale paragraphs.
  `@param`/`@return`/`@throws` tags are kept.
- No `//` comments in Java code.
- Following POM patter, Actions and Locators live in pages, assertions live in tests.
- Waits go through `WaitUtils`/`BasePage`. No `Thread.sleep`, no hardcoded browser/URL/data.
- Screenshots attach to the report on failure only.
- Branch naming: `[<initialLetterOfFirstName><lastName>]/<scope>` for personal work (e.g.
  `jalvarez/core-implementation`)
- Never push to `main` directly, request a Pull Request review.
- Never commit `target/`, logs, reports, IDE files, or driver executables.

## Project layout

```text
├── pom.xml                        # Java 17, Selenium/TestNG/ExtentReports/Gson/Log4j
├── testng.xml                     # suite: one block per browser via `browser` param
├── src/main/java/com/orangehrm/
│   ├── core/                      # config, driver, waits, utils, reporting
│   └── business/                  # pages/*, models/Employee
└── src/test/
    ├── java/com/orangehrm/        # base/BaseTest, dataproviders/*, tests/*
    └── resources/                 # config.properties, testdata/employees.json
```
