package com.touchpoint.services;

import com.google.inject.Inject;
import com.touchpoint.logging.Log;
import com.touchpoint.scenario.ScenarioEnvironment;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import support.GuiceScoped;

import static com.touchpoint.environment.PropertyManager.getProperty;
import static com.touchpoint.utils.UtilMethods.getRandCharSequence;

public class BaseService {
    @Inject
    private DataBaseService dataBaseService;

    @Inject
    protected GuiceScoped guiceScoped;
    private ScenarioEnvironment scenarioEnvironment;

    private static final ThreadLocal<ScenarioEnvironment> SCENARIO_ENVIRONMENT = new ThreadLocal<>();

    public static ScenarioEnvironment getScenarioEnvironment() {
        if (SCENARIO_ENVIRONMENT.get() == null) {
            SCENARIO_ENVIRONMENT.set(new ScenarioEnvironment());
        }
        return SCENARIO_ENVIRONMENT.get();
    }

    public void rememberPage(Object page) {
        getScenarioEnvironment().setCurrentPage(page);
    }

    public void clickElement(String element) throws IllegalAccessException, NoSuchFieldException {
        WebElement webElement = getScenarioEnvironment().getElement(element);
        webElement.click();
        Log.info(String.format("Clicked on \"%s\" element", element));
    }

    public void fillInputField(String inputField, String value, String numberOfCharacters) throws NoSuchFieldException, IllegalAccessException {
        WebElement webElement = getScenarioEnvironment().getElement(inputField);
        String fieldValue;
        if (value.equals("random")) {
            fieldValue = getRandCharSequence (Integer.parseInt(numberOfCharacters), "en");
        } else {
            switch (inputField) {
                case "email":
                    fieldValue = dataBaseService.getEmailValue(value, numberOfCharacters);
                    break;
                case "password":
                    fieldValue = getProperty("app.testPassword");
                    break;
                default:
                    fieldValue = value;
            }
        }
        getScenarioEnvironment().setVar(inputField, fieldValue);
        webElement.sendKeys(fieldValue);
        Log.info(String.format("Input field \"%s\" is filled with value \"%s\"", inputField, fieldValue));
    }

    public void selectValueInSearchList(String list, String value) throws NoSuchFieldException, IllegalAccessException {
        WebElement webElement = getScenarioEnvironment().getElement(list);
        webElement.click();
        webElement.sendKeys(value);
        clickElementByText(value);
    }

    private void clickElementByText(String value) {
        String xpath = String.format("//*[contains(text(),'%s')]", value);
        guiceScoped.driver.findElement(By.xpath(xpath)).click();

    }
}
