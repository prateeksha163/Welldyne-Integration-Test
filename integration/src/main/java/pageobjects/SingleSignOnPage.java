package pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static org.fest.assertions.api.Assertions.*;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import scenarios.Context;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import com.codeborne.selenide.Selectors;
public class SingleSignOnPage extends BasePage {

    private final Context context;
    public final String appsLoginUrl = "http://myapps.microsoft.com/";
    public final String appsLoggedInUrl = "https://account.activedirectory.windowsazure.com/r#/applications";

    private final SelenideElement username = $(By.name("loginfmt"));
    private final SelenideElement password = $(By.name("passwd"));
    private final SelenideElement appIcon = $(Selectors.byText("Benefits by Design-qa"));
    private final SelenideElement signInOrContinueButton = $(By.id("idSIButton9"));
    private final SelenideElement staySignedInText = $(By.xpath("//div[contains(text(),'Stay signed in?')]"));

    public SingleSignOnPage (Context context) {
        this.context = context;
    }

    @Override
    void load() {
        Selenide.open(appsLoginUrl);
        loginAuthorizedUser();
    }

    @Override
    void isLoaded() {
        String url = WebDriverRunner.url();
        assertThat(url).containsIgnoringCase(appsLoggedInUrl);
    }

    public void loginAuthorizedUser() {
        login(context.getConfigValue("AUTHORISED_SSO_USER"),
                context.getConfigValue("AUTHORISED_SSO_PASSWORD"));
    }

    public void login(String username, String password) {
        //Enter username and click
        this.username.shouldBe(clickable).click();
        this.username.setValue(username);
        signInOrContinueButton.click();
        //Enter password and click
        this.password.shouldBe(clickable).click();
        this.password.setValue(password);
        signInOrContinueButton.click();
        //Opt for reduced sign-in message
        staySignedInText.shouldBe(Condition.visible);
        signInOrContinueButton.click();
    }

    public void selectAppIcon() {
        appIcon.waitUntil(clickable, 35000);
        appIcon.click();
        switchTo().window(1);
    }
}
