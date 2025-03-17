# SauceDemo Web Testing Automation

## Project Description
This project implements automated testing of the login form on the [SauceDemo](https://www.saucedemo.com/) website using **Selenium WebDriver** and **TestNG**.
The test cases cover various user interaction scenarios with the login form, ensuring robust validation.

## Features
- Automated tests for the login form.
- Validation of different login input scenarios.
- Parallel test execution.
- Logging of test processes using **SLF4J**.
- Parameterized tests using **DataProvider**.
- Structured test cases following **Page Object Model (POM)**.

## Test Scenarios
✔ **UC-1:** Enter credentials, clear the fields, and verify the error message "Username is required".  
✔ **UC-2:** Enter only the username, clear the password field, and verify the error message "Password is required".  
✔ **UC-3:** Use correct login credentials, verify successful login, and check the "Swag Labs" page title.  
✔ **UC-4:** Attempt to log in with a locked-out user and verify the error message.  
✔ **UC-5:** Check login behavior for problematic user accounts.

## Architecture & Design Patterns
- **Singleton Pattern**: Ensures only one WebDriver instance per test session.
- **Page Object Model (POM)**: Improves test maintenance and scalability.
- **Data-Driven Testing**: Implemented with **TestNG DataProvider** for reusable test cases.
- **Logging Strategy**: Uses **SLF4J** for structured test execution logging.

## Technologies
- **Java**
- **Selenium WebDriver**
- **TestNG**
- **Maven**
- **WebDriverManager**
- **Cucumber (BDD)**
- **AssertJ** (for fluent assertions)
- **SLF4J** (for logging)


