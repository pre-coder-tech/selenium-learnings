package com.learnings.seleniumwithjava;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MakeMyTripTest {
    @Test
    public void testFlightBooking() throws InterruptedException {
       FirefoxOptions options = new FirefoxOptions();
       options.addArguments("--start-maximized");
       options.addArguments("--incognito");
       WebDriver driver = new FirefoxDriver(options);
       driver.get("https://www.makemytrip.com/");

       new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".commonModal__close"))));
   
       //close the login popup
       driver.findElement(By.cssSelector(".commonModal__close")).click();
       Thread.sleep(Duration.ofSeconds(1));
   
       // select from & To
       driver.findElement(By.id("fromCity")).click();
       new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".react-autosuggest__input"))));
       driver.findElement(By.cssSelector(".react-autosuggest__input")).sendKeys("AMD");
       Thread.sleep(Duration.ofSeconds(1));
       driver.findElements(By.cssSelector("li[role='option']")).get(0).click();
   
       driver.findElement(By.id("toCity")).click();
       new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".react-autosuggest__input"))));
       driver.findElement(By.cssSelector(".react-autosuggest__input")).sendKeys("DEL");
       Thread.sleep(Duration.ofSeconds(1));
       driver.findElements(By.cssSelector("li[role='option']")).get(0).click();
   
   
       //Select departure date   
       new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".datePickerContainer"))));
   
       driver.findElement(By.xpath("//div[@class='dateInnerCell']/p[contains(text(), 25)]/parent::div/parent::div")).click();
       Thread.sleep(Duration.ofSeconds(2));
   
       //select travellers
       driver.findElement(By.cssSelector(".flightTravllers")).click();
       new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".fltTravellers "))));
       driver.findElement(By.xpath("//ul[contains(@class, 'guestCounter')]/li[contains(@data-cy, '3')]")).click();
       driver.findElement(By.cssSelector(".btnApply")).click();
   
       //Search
       driver.findElement(By.cssSelector(".widgetSearchBtn")).click();
   
       // Wait for Coachmarks to be visible and click Got It
       new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".pushRight"))));
       driver.findElement(By.cssSelector(".pushRight")).click();
   
       Thread.sleep(Duration.ofSeconds(2));
   
       // click on first book button
       driver.findElements(By.xpath("//button[contains(@id, 'bookbutton')]")).get(0).click();
   
       //wait for Fare modal to open up and click on 1st book now
       new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".fareFamilyOverlay"))));
   
       driver.findElements(By.xpath("//button[contains(text(), 'BOOK NOW')]")).get(0).click();
   
       // waiting for new page to open up & switch to new tab and verify the h2 tag
       Thread.sleep(Duration.ofSeconds(5));
   
       Set<String> windowHandles = driver.getWindowHandles();
       String currentWindowHandle = driver.getWindowHandle();
   
       for (String handle: windowHandles) {
        if (!handle.equals(currentWindowHandle)) {
            driver.switchTo().window(handle);
            break;
        }
       }
   
       Assert.assertTrue(driver.findElements(By.tagName("h2")).get(0).getText().equals("Complete your booking"));
       driver.quit();
    }
   }