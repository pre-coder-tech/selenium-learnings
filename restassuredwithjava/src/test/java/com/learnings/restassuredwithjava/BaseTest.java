package com.learnings.restassuredwithjava;

import org.testng.annotations.DataProvider;

public class BaseTest {
    

    @DataProvider(name="getSingleResourceTestData")
    public Object[][] getSingleResourceTestData() {
        return new Object[][] 
        {{1, 200, 1}, {4, 200, 1}, {18, 200, 2}};
    }
}
