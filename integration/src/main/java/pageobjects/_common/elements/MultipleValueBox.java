package pageobjects._common.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pageobjects._common.modal.BaseModal;

import java.util.List;

import static com.codeborne.selenide.Selectors.byAttribute;

public class MultipleValueBox implements BaseElement {
    protected final SelenideElement element;
    protected SelenideElement addButton;
    protected SelenideElement inputBox;
    protected ElementsCollection values;
    protected SelenideElement name;
    protected SelenideElement error;

    public MultipleValueBox(String elementName) {
        this.element = new BaseModal().getMultiFieldFormElement(elementName);
        element.scrollIntoView(false);
        inputBox = element.$("input");
        values = element.$$("li[class*='field-value-capsule']");
        error = element.$("p[class*='error-field']");
        if (element.$(byAttribute("data-action-name", "multipleValueAdd")).exists())
            addButton = element.$(byAttribute("data-action-name", "multipleValueAdd"));
        if (element.$("h5").exists())
            name = element.$("h5");
    }

    public void setValue(String value) {
        inputBox.click();
        inputBox.setValue(value);
        addButton.scrollIntoView(true).click();
    }

    public void clearField(String value) {
        values.find(Condition.text(value)).$("svg").click();
    }

    public void setValue(List<String> values) {
        values.forEach(this::setValue);
    }

    public void clearField() {
        while (values.size() > 0) {
            values.get(0).$("svg").click();
            values = element.$$("li[class*='field-value-capsule']");
        }
    }

    public String getName() {
        if (name != null && name.exists()) {
            return name.innerText();
        } else return inputBox.getAttribute("placeholder");
    }

    public String getError() {
        return error.getText();
    }

    public boolean isFieldDisabled() {
        return inputBox.getAttribute("class").contains("disabled");
    }
}