package com.learnings.seleniumwithjava;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.learnings.seleniumwithjava.helper.SeleniumHelper;


/* Implements PageObject Model, PageFactory Model */
public class LetsShopHeaderSection extends SeleniumHelper {

    WebDriver driver;

    public LetsShopHeaderSection(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(text(), 'Sign Out')]")
    private WebElement signOutButton;

    public void clickSignOutButton() {
        this.signOutButton.click();
    }

    public boolean isSignOutButtonDisplayed() {
        return this.signOutButton.isDisplayed();
    }

    public WebElement getSignOutButton() {
        return this.signOutButton;
    }

}
