package pageobjects._common.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pageobjects._common.modal.BaseModal;

import java.util.List;
import java.util.stream.Collectors;

public class RadioOptions implements BaseElement {

    ElementsCollection options;
    SelenideElement name;
    SelenideElement error;
    SelenideElement option;

    public RadioOptions(String elementName) {
        SelenideElement element = new BaseModal().getFormElement(elementName);
        element.scrollIntoView(false);
        options = element.$$("label");
        option = element.$("label");
        name = element.$("h5");
        error = element.$("p");
    }

    public void setValue(String option) {
        options.find(Condition.text(option)).click();
    }

    public String getValue() {
        String classValue = "ui-radio-group-field__options__option ui-radio-group-field__options__option--selected";
        return options.find(Condition.attribute("class", classValue)).innerText();
    }

    public List<String> getValues() {
        return options.stream().map(SelenideElement::getText).collect(Collectors.toList());
    }

    public String getName() { return name.innerText(); }

    public String getError() {
        return error.getText();
    }

    public boolean isFieldDisabled() {
        return option.getAttribute("class").contains("disabled");
    }
}
