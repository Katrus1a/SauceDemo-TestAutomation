Feature: Login Functionality

  Scenario: Login with empty credentials
    Given User is on login page
    When User enters "" and "" and clicks login
    Then Error message should be "Epic sadface: Username is required"

  Scenario: Login with missing password
    Given User is on login page
    When User enters "standard_user" and "" and clicks login
    Then Error message should be "Epic sadface: Password is required"

  Scenario: Login with correct credentials
    Given User is on login page
    When User enters "standard_user" and "secret_sauce" and clicks login
    Then User should be logged in and see "Swag Labs"
