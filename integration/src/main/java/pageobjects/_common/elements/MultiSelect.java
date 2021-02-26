package pageobjects._common.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pageobjects._common.modal.BaseModal;

import java.util.List;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;

public class MultiSelect implements BaseElement {

    SelenideElement label;
    SelenideElement input;
    SelenideElement clear;
    SelenideElement error;
    SelenideElement dropdown_options = $(byAttribute("data-placement", "bottom"));

    public MultiSelect(SelenideElement element) {
        element.scrollIntoView(false);
        input = element.$("input");
        label = element.$("div[class*='label']");
        clear = element.$("div[class*='clear-button']");
        error = element.$("p[class*='error-field']");
    }

    public MultiSelect(String elementName) {
        SelenideElement element = new BaseModal().getFormElement(elementName);
        element.scrollIntoView(false);
        input = element.$("input");
        label = element.$("div[class*='label']");
    }

    public String getName() {
        return label.innerText();
    }

    public void setValue(String value) {
        dropdown_options.$$("*[data-option]").find(Condition.text(value)).click();
    }

    public void setValue(List<String> values) {
        values.forEach(this::setValue);
    }

    public String[] getValues() {
        return input.getValue().split(",");
    }

    public String getValue() {
        return input.getValue();
    }

    public void selectElement() {
        input.click();
    }

    public void clearFilter() {
        clear.waitUntil(Condition.visible,2000).click();
    }

    public void shouldBeVisible() {
        input.waitUntil(Condition.visible, 10000);
    }

    public String getError() {
        return error.getText();
    }
}
