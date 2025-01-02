package com.learnings.seleniumwithjava;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    WebDriver driver;
    Properties properties = new Properties();

    

    @BeforeTest
    public void setup() throws Exception {

        try {
            FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/learnings/seleniumwithjava/resources/config.properties");
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String browser = System.getProperty("browser") != null ? System.getProperty("browser") : properties.getProperty("browser");
        
        switch (browser) {
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-notifications");
                options.addArguments("--start-maximized");
                options.addArguments("--disable-infobars");
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--disable-extensions");
                options.addArguments("--incognito");
                driver = new ChromeDriver(options);
            }
            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--disable-notifications");
                options.addArguments("--start-maximized");
                options.addArguments("--disable-infobars");
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--disable-extensions");
                options.addArguments("--incognito");
                driver = new FirefoxDriver(options);
            }
            case "edge" -> {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--disable-notifications");
                options.addArguments("--start-maximized");
                options.addArguments("--disable-infobars");
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--disable-extensions");
                options.addArguments("--incognito");
                driver = new EdgeDriver(options);
            }
            default -> throw new Exception("Invalid browser");
        }
        
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
