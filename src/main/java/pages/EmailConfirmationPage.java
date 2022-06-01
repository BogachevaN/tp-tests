package pages;

import com.google.inject.Inject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import support.GuiceScoped;

import java.util.List;

public class EmailConfirmationPage extends BasePage{
    @Inject
    public EmailConfirmationPage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    @FindBy(xpath = "//a[contains(text(),'Resend')]")
    private WebElement resendLink;

    @FindBy(xpath = "//div[@class='code-parts']/div")
    private List <WebElement> confirmationCode;

    @FindBy (css = ".code-input")
    private WebElement inputFieldForCode;

    public boolean isOpen() {
        return resendLink.isDisplayed();
    }

    public void enterConfirmationCode(String code) {
        char[] codeByChar = code.toCharArray();
        for(int i=0; i<6; i++) {
            confirmationCode.get(i).click();
            inputFieldForCode.sendKeys(String.valueOf(codeByChar[i]));
        }
    }
}
