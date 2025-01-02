package com.learnings.seleniumwithjava;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.learnings.seleniumwithjava.helper.SeleniumHelper;


/* Implements PageObject Model, PageFactory Model */
public class LetsShopRegistrationPage extends SeleniumHelper {

    WebDriver driver;

    public LetsShopRegistrationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private final String REGISTER_URL_PARTIAL_TEXT = "/auth/register";

    public String getRegisterPageURLPartialText() {
        return REGISTER_URL_PARTIAL_TEXT;
    }

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "userEmail")
    private WebElement userEmail;

    @FindBy(id = "userMobile")
    private WebElement userMobile;

    @FindBy(css = "select[formcontrolname='occupation']")
    private WebElement occupation;

    @FindBy(css = "input[type='radio']")
    private List<WebElement> gender;

    @FindBy(id = "userPassword")
    private WebElement userPassword;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPassword;

    @FindBy(css = "input[type='checkbox']")
    private WebElement ageValidation;

    @FindBy(css = "input[type='submit']")
    private WebElement registerButton;

    public void clickRegisterButton() {
        this.registerButton.click();
    }

    public void checkAgeValidation() {
        this.ageValidation.click();
    }

    public void setConfirmPassword(String confirmPasswordToSet) {
        this.confirmPassword.sendKeys(confirmPasswordToSet);
    }

    public void setUserPassword(String passwordToSet) {
        this.userPassword.sendKeys(passwordToSet);
    }

    public void chooseGender(String genderToChoose) {
        this.gender.stream().filter(genderRadio -> genderRadio.getDomAttribute("value").contains(genderToChoose)).findFirst().get().click();
    }

    public void selectOccupation(String occupationToSelect) {
        Select occupationDropdown = new Select(this.occupation);
        occupationDropdown.selectByVisibleText(occupationToSelect);
    }

    public void setUserMobile(String mobileToSet) {
        this.userMobile.sendKeys(mobileToSet);
    }

    public void setUserEmail(String emailToSet) {
        this.userEmail.sendKeys(emailToSet);
    }

    public void setLastName(String lastNameToSet) {
        this.lastName.sendKeys(lastNameToSet);
    }

    public void setFirstName(String firstNameToSet) {
        this.firstName.sendKeys(firstNameToSet);
    }

    public WebElement getFirstName() {
        return this.firstName;
    }

    public WebElement getLastName() {
        return this.lastName;
    }

    public WebElement getUserEmail() {
        return this.userEmail;
    }

    public WebElement getUserMobile() {
        return this.userMobile;
    }

    public WebElement getOccupation() {
        return this.occupation;
    }

    public List<WebElement> getGender() {
        return this.gender;
    }

    public WebElement getUserPassword() {
        return this.userPassword;
    }

    public WebElement getConfirmPassword() {
        return this.confirmPassword;
    }

    public WebElement getAgeValidation() {
        return this.ageValidation;
    }

    public WebElement getRegisterButton() {
        return this.registerButton;
    }

    public boolean validateRegisterPageURL() {
        return driver.getCurrentUrl().contains(REGISTER_URL_PARTIAL_TEXT);
    }

    public void submitRegistrationForm(String firstName, String lastName, String email,
            String mobile, String occupation, String gender, String password) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setUserEmail(email);
        this.setUserMobile(mobile);
        this.selectOccupation(occupation);
        this.chooseGender(gender);
        this.setUserPassword(password);
        this.setConfirmPassword(password);
        this.checkAgeValidation();
        this.clickRegisterButton();
    }

}
