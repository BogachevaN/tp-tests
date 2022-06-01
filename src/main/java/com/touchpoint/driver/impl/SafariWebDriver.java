package com.touchpoint.driver.impl;

import com.touchpoint.exceptions.DriverTypeNotSupported;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.logging.Level;

public class SafariWebDriver implements IDriver {

    @Override
    public WebDriver newDriver() {
        SafariOptions safariOptions = new SafariOptions();

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
        safariOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        try {
            downloadLocalWebDriver(DriverManagerType.SAFARI);
        } catch (DriverTypeNotSupported ex) {
            ex.printStackTrace();
        }

        return new SafariDriver(safariOptions);
    }
}
