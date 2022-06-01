package com.touchpoint.driver;

import com.touchpoint.driver.impl.ChromeWebDriver;
import com.touchpoint.exceptions.DriverTypeNotSupported;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

    private String browserType = System.getProperty("browser");

    public WebDriver getDriver() {
        browserType = "chrome";
        switch (this.browserType) {
            case "chrome": {
                return new ChromeWebDriver().newDriver();
            }
            case "safari": {
                return new SafariDriver();
            }
            default:
                try {
                    throw new DriverTypeNotSupported(this.browserType);
                } catch (DriverTypeNotSupported ex) {
                    ex.printStackTrace();
                    return null;
                }
        }
    }

}
