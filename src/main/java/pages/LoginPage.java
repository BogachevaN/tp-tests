package pages;

import com.google.inject.Inject;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import support.GuiceScoped;

@Getter
public class LoginPage extends BasePage<LoginPage>  {

    @Inject
    public LoginPage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    public static final String path = "/login";

    @FindBy(css = "input[name='email']")
    private WebElement emailAddress;

    @FindBy(css = "a[href='/signup']")
    private WebElement startFreeTrialLink;
}
