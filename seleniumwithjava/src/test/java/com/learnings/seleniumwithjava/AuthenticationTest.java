package com.learnings.seleniumwithjava;

import org.testng.annotations.Test;

public class AuthenticationTest extends BaseTest {

    @Test
    public void testAuthentication() {
        driver.get("https://rahulshettyacademy.com/client");
    }
    
}
