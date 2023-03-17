package com.example.experimental.apilayer.utils;

import java.util.Properties;

public class ConfigLoader {

    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader() {
        properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
    }

    private String getPropertyOrThrow(String propertyName) {
        var property = System.getProperty(propertyName);

        property = properties.getProperty(propertyName);
        if (property != null) {
            return property;
        }
        throw new IllegalArgumentException(String.format("property %s is not specified in config.properties file", propertyName));
    }

    public static ConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getApiBase() {
        return getPropertyOrThrow("api_base");
    }

    public String getApiKey() {
        return getPropertyOrThrow("api_key");
    }


}