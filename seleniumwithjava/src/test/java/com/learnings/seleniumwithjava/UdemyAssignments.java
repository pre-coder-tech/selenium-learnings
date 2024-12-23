package com.learnings.seleniumwithjava;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UdemyAssignments {

    @Test(description = "Assignment3 from the Udemy Course")
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
            products.stream().forEach(product -> product.click()); // Update with Java 8 Stream API
            // for (WebElement product : products) {
            //     product.click();
            // } // Old way of doing the same

            // Checkout
            driver.findElement(By.partialLinkText("Checkout")).click();
        } finally {
            driver.quit();
        }
    }

    @Test(description = "Assignmnet4 from the Udemy Course- Handling Multiple Windows")
    public void testMultipleWindowHandling() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("https://the-internet.herokuapp.com/windows");
            driver.findElement(By.linkText("Click Here")).click();
            String parentWindow = driver.getWindowHandle();
            driver.getWindowHandles().forEach(window -> {
                if (!window.equals(parentWindow)) {
                    driver.switchTo().window(window);
                    System.out.println(driver.findElement(By.tagName("h3")).getText());
                    driver.close();
                }
            }); // Update with Java 8 Stream API
            // for (String window : driver.getWindowHandles()) {
            //     if (!window.equals(parentWindow)) {
            //         driver.switchTo().window(window);
            //         System.out.println(driver.findElement(By.tagName("h3")).getText());
            //         driver.close();
            //     }
            // } // Old way of doing the same
            driver.switchTo().window(parentWindow);
            System.out.println(driver.findElement(By.tagName("h3")).getText());
        } finally {
            driver.quit();
        }
    }

    @Test(description = "Assignment5 from the Udemy Course- Handling Nested Frames")
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

    @Test(description = "Assignment6 from the Udemy Course - playing with data")
    public void testDataTransfers() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("https://rahulshettyacademy.com/AutomationPractice/");
            List<WebElement> checkboxes = driver.findElements(By.cssSelector("div#checkbox-example input"));
            WebElement checkboxToBeClicked = checkboxes.get(new Random().nextInt(checkboxes.size()));
            checkboxToBeClicked.click();
            String selectedCheckboxText = checkboxToBeClicked.findElement(By.xpath("./..")).getText().trim();
            Select dropdown = new Select(driver.findElement(By.id("dropdown-class-example")));
            dropdown.selectByVisibleText(selectedCheckboxText);
            driver.findElement(By.id("name")).sendKeys(selectedCheckboxText);
            driver.findElement(By.id("alertbtn")).click();
            System.out.println(driver.switchTo().alert().getText().contains(selectedCheckboxText));
        } finally {
            driver.quit();
        }
    }

    @Test(description = "Assignment7 from the Udemy Course - Handling Web Tables")
    public void testWebTables() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("https://rahulshettyacademy.com/AutomationPractice/");
            List<WebElement> rows = driver.findElements(By.cssSelector("table#product[name=''] tr"));
            System.out.println("Number of rows: " + rows.size());
            System.out.println("Number of columns:" + rows.get(0).findElements(By.tagName("th")).size());
            List<WebElement> secondRowValues = rows.get(2).findElements(By.tagName("td"));
            secondRowValues.stream().forEach(value -> System.out.println(value.getText())); // Update with Java 8 Stream API
            // for (WebElement value : secondRowValues) {
            //     System.out.println(value.getText());
            // } // Old way of doing the same
        } finally {
            driver.quit();
        }
    }

    @Test(description = "Assignment8 from the Udemy Course - Handling Dynamic Web Elements")
    public void testDynamicWebElements() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("https://rahulshettyacademy.com/AutomationPractice/");

            // Method 1
            driver.findElement(By.id("autocomplete")).sendKeys("Ind");
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.ui-menu li")));
            List<WebElement> countries = driver.findElements(By.cssSelector("ul.ui-menu li"));
            for (WebElement country : countries) {
                if (country.getText().equalsIgnoreCase("India")) {
                    country.click();
                    Thread.sleep(Duration.ofSeconds(2)); // To see if the Country was selected
                    break;
                }
            }

            // Method 2
            driver.findElement(By.id("autocomplete")).clear();
            driver.findElement(By.id("autocomplete")).sendKeys("India");
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.ui-menu li")));
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
            Thread.sleep(Duration.ofSeconds(2)); // To see if the Country was selected

        } finally {
            driver.quit();
        }
    }

    @Test(description = "Learning override Badssl Error")
    public void testBadSSL() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        options.setAcceptInsecureCerts(true);
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("https://expired.badssl.com/");
            System.out.println(driver.getTitle());
        } finally {
            driver.quit();
        }
    }

    @Test(description = "Learning Take Screenshot")
    public void testTakeScreenshot() throws IOException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("https://rahulshettyacademy.com/AutomationPractice/");
            File src = ((ChromeDriver) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File("screenshot.png"));
            /**
             * For FileUtils by default the import is not shown. To include the
             * same I have added dependency in pom.xml file for commons-io. Once
             * the dependency is added and project is re-built, you can import
             * as import org.apache.commons.io.FileUtils;
             */
        } finally {
            driver.quit();
        }
    }

    @Test(description = "Learning Broken Images")
    public void testBrokenImages() throws MalformedURLException, URISyntaxException, IOException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        SoftAssert softAssert = new SoftAssert(); // Soft Assert is used to continue the execution even if the test fails
        try {
            // Broken Images
            driver.get("https://the-internet.herokuapp.com/broken_images");
            List<WebElement> images = driver.findElements(By.tagName("img"));
            for (WebElement image : images) {
                String src = image.getDomAttribute("src");
                URI uri = new URI(src);
                if (!uri.isAbsolute()) {
                    src = "https://the-internet.herokuapp.com/" + src;
                    uri = uri.resolve(src);
                }
                URL url = uri.toURL();
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                softAssert.assertTrue(connection.getResponseCode() < 400, "Broken Image: " + src + " with response code: " + connection.getResponseCode());
            }

            // Broken images
            driver.get("https://rahulshettyacademy.com/AutomationPractice/");
            WebElement footer = driver.findElement(By.id("gf-BIG"));
            List<WebElement> links = footer.findElements(By.tagName("a"));
            for (WebElement link : links) {
                String linkString = link.getDomAttribute("href");
                URI uri = new URI(linkString);
                if (!uri.isAbsolute()) {
                    linkString = "https://rahulshettyacademy.com/AutomationPractice/" + linkString;
                    uri = uri.resolve(linkString);
                }
                URL url = uri.toURL();
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                softAssert.assertTrue(connection.getResponseCode() < 400, "Broken Link: " + linkString + " with response code: " + connection.getResponseCode());
            }
        } finally {
            driver.quit();
            softAssert.assertAll();
        }
    }

    @Test(description="Learning WebTables Handling using Java Streams")
    public void testDataRetrievalFromWebTable(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
            driver.findElement(By.xpath("//th[1]")).click(); //Sort the Table

            //Asserting if the sorted value fetched from the table and derived from us is the same
            List<String> originalNamesListFromTable = driver.findElements(By.xpath("//td[1]")).stream().map(element -> element.getText()).collect(Collectors.toList());
            List<String> sortedNamesListDerived = originalNamesListFromTable.stream().sorted().collect(Collectors.toList());
            Assert.assertTrue(originalNamesListFromTable.equals(sortedNamesListDerived));

            //Coding to get a specific item price from the table - handles pagination as well
            List<String> itemPrice;
            List<WebElement> itemNameElements;
            do { 
                itemNameElements = driver.findElements(By.xpath("//td[1]"));
                itemPrice = itemNameElements.stream()
                    .filter(element -> element.getText().contains("Wheat"))
                    .map(element -> {
                        return element.findElement(By.xpath("following-sibling::td[1]")).getText();
                    })
                    .collect(Collectors.toList());
                itemPrice.forEach(price -> System.out.println("Price of item: " + price));
                if (itemPrice.isEmpty()) {
                    driver.findElement(By.xpath("//a[@aria-label='Next']")).click();
                }   
            } while (itemPrice.isEmpty());
            
        } finally {
            driver.quit();
        }
    }

    @Test(description="Learning - Verifying the Search result with the data in the table") 
    public void testSearchResultVerification() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
            driver.findElement(By.id("search-field")).sendKeys("to"); //Search for items containing word "to" in it

            //Get the search result and verify if the search result is present in the table
            List<WebElement> searchResult = driver.findElements(By.xpath("//td[1]"));
            List<WebElement> filteredSearchResultDerived = driver.findElements(By.xpath("//td[1]")).stream()
                                            .filter(element -> element.getText().contains("to"))
                                            .collect(Collectors.toList());
            Assert.assertTrue("Search didn't work properly", searchResult.size() == filteredSearchResultDerived.size());
        } finally {
            driver.quit();
        }
    }
}
