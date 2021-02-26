package pageobjects._common.elements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import pageobjects._common.modal.BaseModal;

public class InputBox implements BaseElement {

    SelenideElement element;
    SelenideElement input;
    SelenideElement label;
    SelenideElement error;

    public InputBox(String elementName) {
        element = new BaseModal().getFormElement(elementName);
        element.scrollIntoView(false);
        if (element.$("input").exists()) input = element.$("input");
        label = element.$("label.ui-input-field__label");
        error = element.$("p[class*='error-field']");
    }

    public void setValue(String value) {
        this.clearField();
        input.click();
        input.setValue(value);
    }

    public String getValue() {
        return input.getValue();
    }

    public String getName() {
        if (label.exists())
            return label.innerText();
        else
            return input.getAttribute("placeholder");
    }

    public String getError(){
        return error.getText();
    }

    public void clearField() {
        while (!input.getValue().equals("")) {
            input.getWrappedElement().sendKeys(Keys.BACK_SPACE);
        }
        input.getWrappedElement().sendKeys(Keys.TAB);
    }

    public boolean isFieldDisabled() {
        return input.getAttribute("class").contains("disabled");
    }
}