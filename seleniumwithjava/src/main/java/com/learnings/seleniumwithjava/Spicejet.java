package com.learnings.seleniumwithjava;

import java.text.DateFormatSymbols;
import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This class represents the automation of the Spicejet flight booking process.
 * It uses Selenium WebDriver to interact with the web elements on the Spicejet
 * website. The class contains locators for various web elements and methods to
 * perform actions on them.
 */
public class Spicejet {

    private final WebDriver driver;
    private final Map<String, String> flightDetails;

    // constructor with driver and flightDetails
    Spicejet(WebDriver driver, Map<String, String> flightDetails) {
        this.driver = driver;
        this.flightDetails = flightDetails;
    }

    /* Static Variables - Start */
    private static final Map<String, String> tripTypeMapping = Map.of(
            "Round Trip", "round trip",
            "One Way", "one way"
    );

    /* Locators - Start */
    private final String tripType = "//div[text()='${tripType}']/ancestor::div[contains(@data-testid, 'radio-button')]";
    private final String originInput = "//div[@data-testid='to-testID-origin']//input";
    private final String originSelection = "//div[@data-testid='to-testID-origin']//div[text()='${origin}']";
    private final String destinationInput = "//div[@data-testid='to-testID-destination']//input";
    private final String destinationSelection = "//div[@data-testid='to-testID-destination']//div[text()='${destination}']";
    private final String departureDateInput = "//div[@data-testid='departure-date-dropdown-label-test-id']";
    private final String returnDateInput = "//div[@data-testid='return-date-dropdown-label-test-id']";
    private final String calendarPicker = "//div[@data-testid='undefined-calendar-picker']";
    private final String calendarNextButton = "//div[@data-testid='undefined-calendar-picker']/div[1]";
    private final String calendarDateSelection = "//div[@data-testid='undefined-calendar-picker']//div[contains(@data-testid, '${monthYear}')]//div[text()='${date}']";
    private final String passengersInput = "//div[@data-testid='home-page-travellers']";
    private final String passengersSelectionAdult = "//div[@data-testid='Adult-testID-plus-one-cta']";
    private final String passengersSelectionChildren = "//div[@data-testid='Children-testID-plus-one-cta']";
    private final String passengersSelectionInfant = "//div[@data-testid='Infant-testID-plus-one-cta']";
    private final String passengersSelectionDone = "//div[@data-testid='home-page-travellers-done-cta']";
    private final String currencyDiv = "//div[text()='Currency']";
    private final String currencySelection = "//div[text()='Currency']/parent::div/following-sibling::div//div[text()='${currency}']";
    private final String passengerTypeDiv = "//div[text()='${passengerType}']";
    private final String searchFlightButton = "//div[@data-testid='home-page-flight-cta']";
    // private final String searchResultsFilledContent = "//div[text()='Modify Search']/parent::div/parent::div/preceding-sibling::div/div/child::span";

    /* Locators - End */
    public void searchFlight() throws InterruptedException {
        // Select Trip Type
        driver.findElement(By.xpath(tripType.replace("${tripType}", tripTypeMapping.get(flightDetails.get("tripType"))))).click();

        // Select Origin
        driver.findElement(By.xpath(originInput)).click();

        // Select Origin from the Dropdown
        driver.findElement(By.xpath(originSelection.replace("${origin}", flightDetails.get("from")))).click();

        // Select Destination
        driver.findElement(By.xpath(destinationInput)).click();

        // Select Destination from the Dropdown
        driver.findElement(By.xpath(destinationSelection.replace("${destination}", flightDetails.get("to")))).click();

        // Select Departure Date Input if the calender is not displayed
        if (!driver.findElement(By.xpath(calendarPicker)).isDisplayed()) {
            driver.findElement(By.xpath(departureDateInput)).click();
        }
        Thread.sleep(Duration.ofSeconds(2)); // to handle the calendar opening time
        // Select Departure Date from the Calendar
        String[] departureDate = flightDetails.get("departureDate").split("-");
        //convert month from 01 to January
        String monthString = new DateFormatSymbols().getMonths()[Integer.parseInt(departureDate[1]) - 1];
        String monthYear = monthString + "-" + departureDate[0];
        // remove leading 0 from the date
        String day = departureDate[2].replaceFirst("^0+", "");
        String dateSelectionXpath = calendarDateSelection.replace("${monthYear}", monthYear).replace("${date}", day);
        while (!(driver.findElement(By.xpath(dateSelectionXpath)).isDisplayed())) {
            driver.findElement(By.xpath(calendarNextButton)).click();
            Thread.sleep(Duration.ofSeconds(2)); // to handle the animation of the calendar when clicked on next button
        }
        driver.findElement(By.xpath(dateSelectionXpath)).click();

        Thread.sleep(Duration.ofSeconds(2)); // to handle the animation of the calendar when clicked on date

        // Select Return date if Round trip
        if (flightDetails.get("tripType").equals("Round Trip")) {
            // Select Return Date Input if the Calender is not open
            if (!driver.findElement(By.xpath(calendarPicker)).isDisplayed()) {
                driver.findElement(By.xpath(returnDateInput)).click();
            }
            Thread.sleep(Duration.ofSeconds(2)); // to handle the calendar opening time
            // Select Return Date from the Calendar
            String[] returnDate = flightDetails.get("returnDate").split("-");
            //convert month from 01 to January
            monthString = new DateFormatSymbols().getMonths()[Integer.parseInt(returnDate[1]) - 1];
            monthYear = monthString + "-" + returnDate[0];
            // remove leading 0 from the date
            day = returnDate[2].replaceFirst("^0+", "");
            String returndateSelectionXpath = calendarDateSelection.replace("${monthYear}", monthYear).replace("${date}", day);
            while (!(driver.findElement(By.xpath(returndateSelectionXpath)).isDisplayed())) {
                driver.findElement(By.xpath(calendarNextButton)).click();
                Thread.sleep(Duration.ofSeconds(2)); // to handle the animation of the calendar when clicked on next button
            }
            driver.findElement(By.xpath(returndateSelectionXpath)).click();
        }

        Thread.sleep(Duration.ofSeconds(2)); // to handle the animation of the calendar when clicked on date

        // Select Passengers Input
        driver.findElement(By.xpath(passengersInput)).click();

        // Select Adult Passengers
        for (int i = 0; i < Integer.parseInt(flightDetails.get("passengerAdult")); i++) {
            driver.findElement(By.xpath(passengersSelectionAdult)).click();
        }

        // Select Children Passengers
        for (int i = 0; i < Integer.parseInt(flightDetails.get("passengerChildren")); i++) {
            driver.findElement(By.xpath(passengersSelectionChildren)).click();
        }

        // Select Infant Passengers
        for (int i = 0; i < Integer.parseInt(flightDetails.get("passengerInfant")); i++) {
            driver.findElement(By.xpath(passengersSelectionInfant)).click();
        }

        // Click Done after selecting Passengers
        driver.findElement(By.xpath(passengersSelectionDone)).click();

        Thread.sleep(Duration.ofSeconds(2)); // to handle the Done button click

        // Select Currency
        driver.findElement(By.xpath(currencyDiv)).click();

        // Select Currency from the Dropdown
        driver.findElement(By.xpath(currencySelection.replace("${currency}", flightDetails.get("currency")))).click();

        // Select Passenger Type
        driver.findElement(By.xpath(passengerTypeDiv.replace("${passengerType}", flightDetails.get("passengerType")))).click();

        // Click Search Flight Button
        driver.findElement(By.xpath(searchFlightButton)).click();
    }

}
