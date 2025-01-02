package com.learnings.seleniumwithjava;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.learnings.seleniumwithjava.helper.SeleniumHelper;


/* Implements PageObject Model, PageFactory Model */
public class LetsShopHomePage extends SeleniumHelper {

    WebDriver driver;

    public LetsShopHomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Register here")
    private WebElement registerHereLink;

    @FindBy(id = "userEmail")
    private WebElement userEmail;

    @FindBy(id = "userPassword")
    private WebElement userPassword;

    @FindBy(css = "input[type='submit']")
    private WebElement loginButton;

    @FindBy(css = ".forgot-password-link")
    private WebElement forgotPasswordLink;

    public LetsShopForgotPasswordPage clickForgotPasswordLink() {
        this.forgotPasswordLink.click();
        return new LetsShopForgotPasswordPage(this.driver);
    }

    public void clickLoginButton() {
        this.loginButton.click();
    }

    public void setUserPassword(String password) {
        this.userPassword.sendKeys(password);
    }

    public void setUserEmail(String email) {
        this.userEmail.sendKeys(email);
    }

    public LetsShopRegistrationPage clickRegisterHereLink() {
        this.registerHereLink.click();
        return new LetsShopRegistrationPage(this.driver);
    }

    public WebElement getRegisterHereLink() {
        return this.registerHereLink;
    }

    public WebElement getUserEmail() {
        return this.userEmail;
    }

    public WebElement getUserPassword() {
        return this.userPassword;
    }

    public WebElement getLoginButton() {
        return this.loginButton;
    }

    public WebElement getForgotPasswordLink() {
        return this.forgotPasswordLink;
    }

    public void loginToLetsShop(String email, String password) {
        this.setUserEmail(email);
        this.setUserPassword(password);
        this.clickLoginButton();
    }

}
