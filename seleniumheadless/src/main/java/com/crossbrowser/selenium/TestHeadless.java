package com.crossbrowser.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;

public class TestHeadless {

    public static void main(String[] args) {
        runTest("chrome");
        runTest("firefox");
    }

    public static void runTest(String browser) {
        WebDriver driver = null;

        try {
            if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1200,600");
                driver = new ChromeDriver(options);

            } else if (browser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless");
                options.addArguments("--width=1200");
                options.addArguments("--height=600");
                driver = new FirefoxDriver(options);
            }

            driver.get("https://example.com");

            if (driver.getTitle().contains("Example")) {
                System.out.println("XXXXX ===== >>>>" +browser + " loaded the page successfully.");
            } else {
                System.out.println(browser + " failed to load the expected content.");
            }

        } catch (Exception e) {
            System.out.println(browser + " test failed: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}