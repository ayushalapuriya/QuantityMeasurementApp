package com.apps.quantitymeasurement.util;

import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfig {

    private static final Properties properties = new Properties();

    static {
        try {
            InputStream input =
                    ApplicationConfig.class.getClassLoader()
                            .getResourceAsStream("application.properties");

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getDbUrl() {
        return properties.getProperty("db.url");
    }

    public static String getUsername() {
        return properties.getProperty("db.username");
    }

    public static String getPassword() {
        return properties.getProperty("db.password");
    }
}