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

    @Test(description = "Able to search the flight")
    public void ableToSearchFlight() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications"); //`disable-notifications` flag to disable the browser notifications
        options.addArguments("--disable-geolocation"); //`disable-geolocation` flag to disable the browser geolocation
        options.addArguments("--disable-infobars"); //`disable-infobars` flag to disable the infobars
        options.addArguments("--start-maximized"); //`start-maximized` flag to start the browser in maximized mode
        options.addArguments("--disable-extensions"); //`disable-extensions` flag to disable the browser extensions
        options.addArguments("--disable-popup-blocking"); //`disable-popup-blocking` flag to disable the popup blocking
        options.addArguments("--incognito"); //`incognito` flag to start the browser in incognito mode
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        try {
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

        } catch (Exception e) {
            Assert.fail("Failed to Search the flight", e);
        } finally {
            driver.quit();
        }
    }
}
