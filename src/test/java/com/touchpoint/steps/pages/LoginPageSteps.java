package com.touchpoint.steps.pages;

import com.google.inject.Inject;
import com.touchpoint.services.BaseService;
import io.cucumber.java.en.Given;
import pages.LoginPage;

public class LoginPageSteps {
    @Inject
    private LoginPage loginPage;

    @Inject
    private BaseService baseService;

    @Given("LoginPage page is open")
    public void goToPageLoginPage() {
        loginPage.open(loginPage.path);
        baseService.rememberPage(loginPage);
    }
}
