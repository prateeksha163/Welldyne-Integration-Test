package pageobjects._common.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pageobjects._common.modal.BaseModal;

import java.util.List;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;

public class RulesFormSection {

    protected SelenideElement element;
    ElementsCollection ruleSections;
    SelenideElement title;

    public RulesFormSection(String elementName) {
        this.element = new BaseModal().getFormElement(elementName);
        ruleSections = element.$$("div[class='ui-common-rules-field__rules-sets__item']");
    }

    public void setValue(String dropdownValue, String value, String ruleSet) {
        title = ruleSections.findBy(Condition.text(ruleSet));
        title.$("p[class*='section__chevron']").click();
        $(".ui-choice-list").$$("*[data-option]").find(Condition.text(dropdownValue)).click();
        title.$(byAttribute("placeholder", "Age*")).setValue(value);
    }

    public void setValue(String dropdownValue, String min, String max,String ruleSet) {
        title = ruleSections.findBy(Condition.text(ruleSet));
        title.$("p[class*='section__chevron']").click();
        $(".ui-choice-list").$$("*[data-option]").find(Condition.text(dropdownValue)).click();
        title.$("div[class='ui-value-and-type-select__field__range-input__input-fields']").$$("input").get(0).setValue(min);
        title.$("div[class='ui-value-and-type-select__field__range-input__input-fields']").$$("input").get(1).setValue(max);
    }

    public void removeSection(String ruleSet) {
        title = ruleSections.findBy(Condition.text(ruleSet));
        title.$("p[class*='remove']").click();
    }

    public List<String> getSectionNames() {
        return element.$$("p[class*='item__head__title']").texts();
    }

    public String getName() {
        return element.$("input[class*='input--undefined']").getAttribute("placeholder");
    }
}