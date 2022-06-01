package com.touchpoint.driver.impl;

import com.touchpoint.exceptions.DriverTypeNotSupported;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;


import java.util.logging.Level;

public class ChromeWebDriver implements IDriver {

    @Override
    public WebDriver newDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notification");
        chromeOptions.addArguments("--no-first-run");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--ignore-certificate-errors");

        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--enable-extensions");
        chromeOptions.addArguments("--homepage=about:blank");
        chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        chromeOptions.setCapability("enableVNC", Boolean.parseBoolean(System.getProperty("enableVNC", "false")));


        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
        chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);


        try {
            downloadLocalWebDriver(DriverManagerType.CHROME);
        } catch (DriverTypeNotSupported ex) {
            ex.printStackTrace();
        }

        return new ChromeDriver(chromeOptions);
    }
}
