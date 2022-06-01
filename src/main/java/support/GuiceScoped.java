package support;

import com.touchpoint.driver.DriverFactory;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;

import java.security.PublicKey;
import java.util.concurrent.TimeUnit;

@ScenarioScoped
public class GuiceScoped {
    public WebDriver driver = new DriverFactory().getDriver();
}
