package pageobjects._common.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pageobjects._common.modal.BaseModal;

import java.util.List;
import java.util.stream.Collectors;

public class Checkbox implements BaseElement {

    ElementsCollection options;
    SelenideElement name;
    SelenideElement error;

    public Checkbox(String elementName) {
        SelenideElement element = new BaseModal().getFormElement(elementName);
        element.scrollIntoView(false);
        options = element.$$("li");
        name = element.$("h5");
        error = element.$("p[class*='error-field']");
    }

    private boolean isChecked(SelenideElement option) {
        return option.$("svg").exists();
    }

    public void markChecked(String value) {
        SelenideElement option = options.find(Condition.text(value));
        if (!isChecked(option))
            option.click();
    }

    public void markUnchecked(String value) {
        SelenideElement option = options.find(Condition.text(value));
        if (isChecked(option))
            option.click();
    }

    public List<String> getValues() {
        return options.stream().map(SelenideElement::getText).collect(Collectors.toList());
    }

    public void setValue(String label, Boolean value) {
        if(value)
            markChecked(label);
        else
            markUnchecked(label);
    }

    public String getName() {
        return name.innerText();
    }

    public String getError() {

        return error.getText();
    }
}
