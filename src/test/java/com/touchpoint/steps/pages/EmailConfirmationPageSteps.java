package com.touchpoint.steps.pages;

import com.google.inject.Inject;
import com.touchpoint.services.BaseService;
import com.touchpoint.services.EmailService;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.EmailConfirmationPage;

import javax.mail.MessagingException;
import java.io.IOException;

public class EmailConfirmationPageSteps {
    @Inject
    private EmailConfirmationPage emailConfirmationPage;

    @Inject
    private BaseService baseService;

    @Inject
    private EmailService emailService;


    @Then("EmailConfirmation page is open")
    public void emailConfirmationPageIsOpen() {
        if (emailConfirmationPage.isOpen()) baseService.rememberPage(emailConfirmationPage);
    }

    @When("confirmation code entered")
    public void confirmationCodeEntered() throws MessagingException, IOException {
        emailConfirmationPage.enterConfirmationCode(emailService.getConfirmationCode());
    }
}
