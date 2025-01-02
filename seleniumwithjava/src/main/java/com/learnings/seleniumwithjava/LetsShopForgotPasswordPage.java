package com.learnings.seleniumwithjava;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.learnings.seleniumwithjava.helper.SeleniumHelper;


/* Implements PageObject Model, PageFactory Model */
public class LetsShopForgotPasswordPage extends SeleniumHelper {

    WebDriver driver;

    public LetsShopForgotPasswordPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private final String FORGOT_PASSWORD_URL_PARTIAL_TEXT = "/auth/password-new";

    public String getForgotPasswordPageURLPartialText() {
        return FORGOT_PASSWORD_URL_PARTIAL_TEXT;
    }

    @FindBy(css = "input[type='email']")
    private WebElement userEmail;

    @FindBy(id = "userPassword")
    private WebElement userPassword;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPassword;

    @FindBy(css = "input[type='submit']")
    private WebElement savePasswordButton;

    @FindBy(linkText = "Login")
    private WebElement loginLink;

    @FindBy(linkText="Register")
    private WebElement registerLink;

    public void clickRegisterLink() {
        this.registerLink.click();
    }

    public void clickLoginLink() {
        this.loginLink.click();
    }
    
    public void clickSavePasswordButton() {
        this.savePasswordButton.click();
    }
    public void setConfirmPassword(String confirmPasswordToSet) {
        this.confirmPassword.sendKeys(confirmPasswordToSet);
    }

    public void setUserPassword(String password) {
        this.userPassword.sendKeys(password);
    }

    public void setUserEmail(String email) {
        this.userEmail.sendKeys(email);
    }

    public WebElement getUserEmail() {
        return this.userEmail;
    }

    public WebElement getUserPassword() {
        return this.userPassword;
    }

    public WebElement getConfirmPassword() {
        return this.confirmPassword;
    }

    public WebElement getSavePasswordButton() {
        return this.savePasswordButton;
    }

    public WebElement getLoginLink() {
        return this.loginLink;
    }

    public WebElement getRegisterLink() {
        return this.registerLink;
    }

    public boolean validateForgotPasswordPageURL() {
        return this.driver.getCurrentUrl().contains(FORGOT_PASSWORD_URL_PARTIAL_TEXT);
    }

    public void submitForgotPasswordForm(String email, String password) {
        this.setUserEmail(email);
        this.setUserPassword(password);
        this.setConfirmPassword(password);
        this.clickSavePasswordButton();
    }
    


}
