package com.epam.automation.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverManager {
    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);
    private static DriverManager instance;
    private WebDriver driver;

    private DriverManager() {
        logger.info("Initializing WebDriver...");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            logger.info("Quitting WebDriver...");
            driver.quit();
            instance = null;
        }
    }
}
