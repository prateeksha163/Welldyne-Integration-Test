package pageobjects._common.modal;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class DeleteModal {
    private final SelenideElement loader = $(".ui-heart-beat-preloader");
    protected SelenideElement modal;
    SelenideElement cancelButton;
    SelenideElement confirmButton;
    SelenideElement okButton;
    SelenideElement closeButton;
    SelenideElement errorOnConfirmation;
    SelenideElement image;
    SelenideElement title;
    SelenideElement description;

    public DeleteModal() {
        modal = $(".ui-overlay__content-pane");
        cancelButton = $(byText("Cancel"));
        confirmButton = $(byText("Confirm"));
        okButton = $(byText("OK"));
        closeButton = $(byAttribute("data-action-name", "closeOverlay"));
        errorOnConfirmation = modal.$("p[class*='acknowledgement__description']");
        image = modal.$("svg");
        title = modal.$("h4");
        description = modal.$("p");
    }

    public String getDescription() {
        return description.getText();
    }

    public void clickConfirmButton() {
        confirmButton.click();
    }

    public void shouldNotExist() {
        modal.shouldNot(Condition.exist);
    }

    public void shouldBeVisible() {
        modal.shouldBe(Condition.visible);
    }

    public String errorOnConfirmation() {
        return errorOnConfirmation.getText();
    }

    public void clickOnActionButton(String action) {
        switch (action.toUpperCase()) {
            case "CANCEL":
                cancelButton.click();
                loader.waitUntil(Condition.disappear, 5000);
                break;
            case "CLOSE":
                closeButton.click();
                loader.waitUntil(Condition.disappear, 5000);
                break;
            case "CONFIRM":
                confirmButton.click();
                loader.waitUntil(Condition.disappear, 5000);
                break;
            case "OK":
                okButton.click();
                loader.waitUntil(Condition.disappear, 5000);
                break;
        }
    }

    public String getTitle() {
        return title.getText();
    }

    public Boolean isImageVisible() {
        return image.exists();
    }
}
