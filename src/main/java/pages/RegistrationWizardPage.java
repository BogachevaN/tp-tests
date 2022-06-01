package pages;

import com.google.inject.Inject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import support.GuiceScoped;

public class RegistrationWizardPage extends BasePage {
    @Inject
    public RegistrationWizardPage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    @FindBy(css = "input[name='name']")
    private WebElement workspaceName;

    @FindBy(css = "input[name='identity']")
    private WebElement workspaceUrl;

    @FindBy(xpath = "//div[contains(text(),'.touchpoint.com')]")
    private WebElement domain;

    @FindBy(css = "button[type='submit']")
    private WebElement continueBtn;

    public boolean isOpen() {
        return domain.isDisplayed();
    }


}
