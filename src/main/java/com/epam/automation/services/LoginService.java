package com.epam.automation.services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface LoginService {
    void performLogin(WebDriver driver, String username, String password);
}

class StandardLogin implements LoginService {
    private static final Logger logger = LoggerFactory.getLogger(StandardLogin.class);

    @Override
    public void performLogin(WebDriver driver, String username, String password) {
        logger.info("Performing standard login for user: {}", username);
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }
}

class ErrorLogin implements LoginService {
    private static final Logger logger = LoggerFactory.getLogger(ErrorLogin.class);

    @Override
    public void performLogin(WebDriver driver, String username, String password) {
        logger.info("Performing error case login for user: {}", username);
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        logger.warn("Expected error message should be verified.");
    }
}
