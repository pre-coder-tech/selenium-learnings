package com.learnings.seleniumwithjava;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.learnings.seleniumwithjava.testdata.SpicejetSearchFlightData;

public class SpicejetTest {

    private final List<Map<String, String>> flightDetails = SpicejetSearchFlightData.getSearchFlightData();

    @Test
    public void ableToSearchFlight() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-geolocation");
        options.addArguments("--disable-infobars");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        
        flightDetails.forEach(flightDetail -> {
            driver.get("https://www.spicejet.com/");

            Spicejet spicejet = new Spicejet(driver, flightDetail);
            try {
                spicejet.searchFlight();
                Thread.sleep(Duration.ofSeconds(10)); // waiting for next page to load
            } catch (InterruptedException e) {
                Assert.fail("Failed to Search the flight", e);
            } 
            Assert.assertTrue(driver.getCurrentUrl().contains("/search"));

        });
        driver.quit();
    }
}
