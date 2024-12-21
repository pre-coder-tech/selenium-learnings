package com.learnings.seleniumwithjava;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class UdemyAssignments {

    @Test(description="Assignment3 from the Udemy Course")
    public void testAddItemsToCart() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("https://rahulshettyacademy.com/loginpagePractise/");

            // Login Form
            driver.findElement(By.id("username")).sendKeys("rahulshettyacademy");
            driver.findElement(By.id("password")).sendKeys("learning");
            driver.findElement(By.cssSelector("input#usertype[value='user']")).click();
            new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOfElementLocated(By.id("okayBtn")));
            driver.findElement(By.id("okayBtn")).click();
            Select userTypeObj = new Select(driver.findElement(By.tagName("select")));
            userTypeObj.selectByVisibleText("Consultant");
            driver.findElement(By.id("terms")).click();
            driver.findElement(By.id("signInBtn")).click();

            // Wait till the page is loaded after login
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Checkout")));

            // Add items to cart
            List<WebElement> products = driver.findElements(By.cssSelector("button[class='btn btn-info']"));
            for (WebElement product : products) {
                product.click();
            }

            // Checkout
            driver.findElement(By.partialLinkText("Checkout")).click();
        } finally {
            driver.quit();
        }
    }

    @Test(description="Assignmnet4 from the Udemy Course- Handling Multiple Windows")
    public void testMultipleWindowHandling() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("https://the-internet.herokuapp.com/windows");
            driver.findElement(By.linkText("Click Here")).click();
            String parentWindow = driver.getWindowHandle();
            for (String window : driver.getWindowHandles()) {
                if (!window.equals(parentWindow)) {
                    driver.switchTo().window(window);
                    System.out.println(driver.findElement(By.tagName("h3")).getText());
                    driver.close();
                }
            }
            driver.switchTo().window(parentWindow);
            System.out.println(driver.findElement(By.tagName("h3")).getText());
        } finally {
            driver.quit();
        }
    }

    @Test(description="Assignment5 from the Udemy Course- Handling Nested Frames")
    public void testNestedFrames() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("https://the-internet.herokuapp.com/nested_frames");
            driver.switchTo().frame(driver.findElement(By.name("frame-top")));
            driver.switchTo().frame(driver.findElement(By.name("frame-middle")));
            System.out.println(driver.findElement(By.id("content")).getText());
            driver.switchTo().defaultContent();
        } finally {
            driver.quit();
        }
    }
}
