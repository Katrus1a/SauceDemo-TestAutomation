package com.epam.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AppTest extends BaseTest {

    @Test
    public void testLoginWithEmptyCredentials() {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        // Вводимо довільні дані
        usernameField.sendKeys("test_user");
        passwordField.sendKeys("test_password");

        // Видаляємо символи по одному
        usernameField.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        passwordField.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);

        // Переконуємось, що поля очищені
        Assert.assertEquals(usernameField.getAttribute("value"), "", "Username field is not empty!");
        Assert.assertEquals(passwordField.getAttribute("value"), "", "Password field is not empty!");

        // Натискаємо кнопку входу
        loginButton.click();

        // Очікуємо появу повідомлення про помилку
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']")));

        String actualMessage = errorMessage.getText();
        String expectedMessage = "Epic sadface: Username is required";

        Assert.assertEquals(actualMessage, expectedMessage, "Error message does not match!");
    }

    @Test
    public void testLoginWithMissingPassword() {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        // Вводимо ім'я користувача
        usernameField.sendKeys("test_user");

        // Вводимо пароль, а потім очищаємо його
        passwordField.sendKeys("test_password");
        passwordField.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);

        // Переконуємось, що пароль очищений
        Assert.assertEquals(passwordField.getAttribute("value"), "", "Password field is not empty!");

        // Натискаємо кнопку входу
        loginButton.click();

        // Очікуємо появу повідомлення про помилку
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']")));

        String actualMessage = errorMessage.getText();
        String expectedMessage = "Epic sadface: Password is required";

        Assert.assertEquals(actualMessage, expectedMessage, "Error message does not match!");
    }
}
