package com.touchpoint.steps.pages;

import com.google.inject.Inject;
import com.touchpoint.services.BaseService;
import io.cucumber.java.en.Then;
import pages.SignUpPage;

public class SignUpPageSteps {

    @Inject
    private SignUpPage signUpPage;

    @Inject
    private BaseService baseService;

    @Then("SignUp page is open")
    public void signupPageIsOpen() {
        if (signUpPage.isOpen()) baseService.rememberPage(signUpPage);
    }



}
