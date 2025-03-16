package com.epam.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class AppTest extends BaseTest {

    @Test
    public void testLoginWithEmptyCredentials() {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        // Переконуємося, що поля порожні
        usernameField.clear();
        passwordField.clear();

        // Натискаємо кнопку входу
        loginButton.click();

        // Отримуємо текст повідомлення про помилку
        WebElement errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']"));
        String actualMessage = errorMessage.getText();

        // Правильне повідомлення
        String expectedMessage = "Epic sadface: Username is required"; // ✅ Оновлене повідомлення

        // Перевірка
        Assert.assertEquals(actualMessage, expectedMessage, "Error message does not match!");
    }

}
