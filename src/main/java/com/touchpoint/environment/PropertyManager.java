package com.touchpoint.environment;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import com.touchpoint.logging.Log;

public final class PropertyManager {
    private static final Properties PROPERTIES;

    static {
        PROPERTIES = new Properties();
        ClassLoader classLoader = PropertyManager.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("application.properties");
        try {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            Log.error(e.getMessage(), e);
        }
    }

    private PropertyManager() {
        throw new UnsupportedOperationException(String.format("Creating an instance of the %s class is forbidden",
                PropertyManager.class));
    }

    public static String getProperty(String propertyName) {
        return PROPERTIES.getProperty(propertyName);
    }
}
