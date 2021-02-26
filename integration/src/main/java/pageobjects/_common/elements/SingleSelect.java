package pageobjects._common.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pageobjects._common.modal.BaseModal;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class SingleSelect implements BaseElement {

    SelenideElement input;
    SelenideElement clear;
    SelenideElement error;
    ElementsCollection allErrors;
    SelenideElement dropdown_options = $(".ui-choice-list");
    SelenideElement dropdown;

    public SingleSelect(SelenideElement element) {
        element.scrollIntoView(false);
        input = element.$("input");
        clear = element.$("div[class*='clear-button']");
    }

    public SingleSelect(String elementName) {
        SelenideElement element = new BaseModal().getFormElement(elementName);
        element.scrollIntoView(false);
        input = element.$("input");
        error = element.$("p[class*='error-field']");
        allErrors = element.$$("p[class*='error-field']");
    }

    public String getValue() {
        return input.getValue();
    }

    public void setValue(String value) {
        input.click();
        dropdown_options.$$("*[data-option]").find(Condition.text(value)).click();
    }

    public String getName() {
        return input.getAttribute("placeholder");
    }

    public SelenideElement getElement() {
        return input;
    }

    public void clearFilter() {
        clear.waitUntil(Condition.visible, 2000).click();
    }

    public String getError() {
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

    public List<String> getDropdownValues() {
        input.click();
        dropdown = $("div[class*='ui-popover--no']").$("div[class='ui-choice-list']");
        ElementsCollection text = dropdown.$$(By.className("ui-choice-list__option__label"));
        return text.texts();
    }
}
