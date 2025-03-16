package com.epam.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.logging.Logger;

public class AppTest extends BaseTest {

    private static final Logger logger = Logger.getLogger(AppTest.class.getName());

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
                {"", "", "Epic sadface: Username is required"}, // UC-1
                {"standard_user", "", "Epic sadface: Password is required"}, // UC-2
                {"standard_user", "secret_sauce", "Swag Labs"}, // UC-3 (успішний вхід)
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."}, // Заблокований користувач
                {"problem_user", "secret_sauce", "Swag Labs"},
                {"performance_glitch_user", "secret_sauce", "Swag Labs"},
                {"error_user", "secret_sauce", "Swag Labs"},
                {"visual_user", "secret_sauce", "Swag Labs"}
        };
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password, String expectedMessage) {
        logger.info("Starting test with username: " + username + " and password: " + (password.isEmpty() ? "[empty]" : "[provided]"));

        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        // Вводимо дані
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);

        // Очищаємо, якщо потрібно (для UC-1 і UC-2)
        if (username.isEmpty()) {
            usernameField.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        }
        if (password.isEmpty()) {
            passwordField.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        }

        // Натискаємо кнопку входу
        loginButton.click();

        // Очікуємо та перевіряємо повідомлення про помилку або успішний логін
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        if (expectedMessage.equals("Swag Labs")) {
            wait.until(ExpectedConditions.titleIs(expectedMessage));
            String actualTitle = driver.getTitle();
            Assert.assertEquals(actualTitle, expectedMessage, "Title does not match!");
            logger.info("Successfully logged in as " + username);
        } else {
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']")));
            String actualMessage = errorMessage.getText();
            Assert.assertEquals(actualMessage, expectedMessage, "Error message does not match!");
            logger.warning("Expected error: " + expectedMessage + " but found: " + actualMessage);
        }
    }
}
