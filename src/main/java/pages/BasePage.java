package pages;

import lombok.Getter;
import org.openqa.selenium.support.PageFactory;
import support.GuiceScoped;

import java.util.concurrent.TimeUnit;

@Getter
public class BasePage<T> {

    protected GuiceScoped guiceScoped;

    public BasePage(GuiceScoped guiceScoped) {
        this.guiceScoped = guiceScoped;
        PageFactory.initElements(guiceScoped.driver, this);
    }

    public T open(String path) {
        String baseUrl = System.getProperty("base.url");
        baseUrl = "https://app.touchpointtesting.us";
        guiceScoped.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        guiceScoped.driver.get(baseUrl + path);
        return (T) this;
    }

}
