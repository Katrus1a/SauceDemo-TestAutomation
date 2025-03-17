package com.epam.automation.bdd;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.epam.automation.utils.DriverManager;
import io.cucumber.java.en.*;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps {
    private WebDriver driver = DriverManager.getInstance().getDriver();

    @Given("User is on login page")
    public void userIsOnLoginPage() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("User enters {string} and {string} and clicks login")
    public void userEntersCredentials(String username, String password) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }

    @Then("Error message should be {string}")
    public void verifyErrorMessage(String expectedError) {
        String actualError = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
        assertThat(actualError).isEqualTo(expectedError);
    }

    @Then("User should be logged in and see {string}")
    public void verifyLoginSuccess(String expectedTitle) {
        String actualTitle = driver.getTitle();
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }
}
