package pages;

import com.google.inject.Inject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import support.GuiceScoped;


public class SignUpPage extends BasePage{
    @Inject
    public SignUpPage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    public static final String path = "/signup";

    @FindBy(css = "input[name='firstName']")
    private WebElement firstName;

    @FindBy(css = "input[name='lastName']")
    private WebElement lastName;

    @FindBy(css = "input[name='email']")
    private WebElement email;

    @FindBy(css = "input[name='password']")
    private WebElement password;

    @FindBy(css = "input[type='country']")
    private WebElement country;

    @FindBy(css = "button[type='submit']")
    private WebElement startFreeTrialBtn;

    public boolean isOpen() {
        return firstName.isDisplayed();
    }
}
