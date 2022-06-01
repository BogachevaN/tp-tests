package com.touchpoint.steps;

import com.google.inject.Inject;
import com.touchpoint.services.BaseService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class CommonSteps {

    @Inject
    private BaseService baseService;



    @And("clicked on {string} element")
    public void clickedOnElement(String element) throws IllegalAccessException, NoSuchFieldException {
        baseService.clickElement(element);
    }

    @When("{string} value of {string} characters is entered into {string} field")
    public void valueOfCharactersIsEnteredIntoField(String value, String numberOfCharacters, String inputField) throws NoSuchFieldException, IllegalAccessException {
        baseService.fillInputField(inputField, value, numberOfCharacters);
    }

    @And("in {string} list is selected as {string}")
    public void selectValueInList(String list, String value) throws NoSuchFieldException, IllegalAccessException {
        baseService.selectValueInSearchList(list, value);
    }
}
