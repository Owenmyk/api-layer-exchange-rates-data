package com.example.experimental.apilayer.tests;

import com.example.experimental.apilayer.utils.ConfigLoader;
import org.testng.annotations.BeforeSuite;

public abstract class BaseTest {

    protected String apiKey;

    @BeforeSuite
    public void beforeSuite() {
        apiKey = ConfigLoader.getInstance().getApiKey();
    }
}
