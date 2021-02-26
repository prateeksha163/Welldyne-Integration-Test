package pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class UnauthenticatedPage {

    private final SelenideElement errorTitle = $(By.className("ui-error-page__view__messages__message__title"));
    private final SelenideElement errorText = $(By.className("ui-error-page__view__messages__message__subtitle"));

    public void waitForPageLoad() {
        errorTitle.waitUntil(Condition.visible, 15000);
        errorText.waitUntil(Condition.visible, 15000);
    }

    public String getErrorTitle() {
        return errorTitle.getText();
    }

    public String getErrorText() {
        return errorText.getText();
    }
}
