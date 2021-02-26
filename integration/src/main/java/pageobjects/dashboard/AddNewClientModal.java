package pageobjects.dashboard;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import pageobjects._common.modal.BaseModal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AddNewClientModal extends BaseModal {

    SelenideElement clientLogo;
    SelenideElement clientName;
    SelenideElement clientCode;
    SelenideElement effectiveDate;
    SelenideElement imageError = $(".ui-info-pill__text-section__text");
    SelenideElement duplicationError = $(".ui-overlay-form-frame-status-acknowledgement__description");
    ElementsCollection errors = $$(".ui-error-field.ui-input-field__box__error.ui-error-field--small");

    public AddNewClientModal() {
        clientLogo = modal.$(".ui-file-uploader input");
        clientName = modal.$(byAttribute("data-input-name", "clientName"));
        clientCode = modal.$(byAttribute("data-input-name", "clientCode"));
        effectiveDate = modal.$(byAttribute("data-input-name", "effectiveDate"));
    }

    public List<String> getAvailableFormFields() {
        ArrayList<String> fieldList = new ArrayList<>();
        if (clientLogo.exists())
            fieldList.add("Client Logo");
        if (clientName.isDisplayed())
            fieldList.add("Client Name");
        if (clientCode.isDisplayed())
            fieldList.add("Client Code");
        if (effectiveDate.isDisplayed())
            fieldList.add("Effective Date");
        if (effectiveDate.isDisplayed())
            fieldList.add("Submit Button");
        if (effectiveDate.isDisplayed())
            fieldList.add("Cancel Button");
        if (effectiveDate.isDisplayed())
            fieldList.add("Close Overlay Button");
        return fieldList;
    }

    public void updateFieldValue(String field, String value) {
        switch (field.toUpperCase()) {
            case "LOGO":
                setLogo(value);
                break;
            case "CLIENT NAME":
                setClientName(value);
                break;
            case "CLIENT CODE":
                setClientCode(value);
                break;
            case "EFFECTIVE DATE":
                setEffectiveDate(value);
                break;
        }
    }

    private void setLogo(String filenameIncludingPathFromResources) {
        clientLogo.uploadFile(new File("src/main/resources/" + filenameIncludingPathFromResources));
    }

    private void setClientName(String name) {
        clientName.setValue(name);
    }

    private void setClientCode(String code) {
        clientCode.setValue(code);
    }

    private void setEffectiveDate(String effectiveDate) {
        clearEffectiveDate();
        this.effectiveDate.setValue(effectiveDate);
    }

    public String getEffectiveDate() {
        return effectiveDate.getValue();
    }

    public void clearEffectiveDate() {
        while (!effectiveDate.getValue().equals("")) {
            effectiveDate.getWrappedElement().sendKeys(Keys.BACK_SPACE);
        }
        effectiveDate.getWrappedElement().sendKeys(Keys.TAB);
    }

    public ElementsCollection getAllErrors() {
        return errors;
    }

    public String getImageError() {
        return imageError.getText();
    }

    public String getDuplicationErrorText() {
        return duplicationError.getText();
    }
}
