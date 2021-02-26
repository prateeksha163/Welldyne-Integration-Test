package pageobjects.listmanagement.steptherapy;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import pageobjects._common.annotations.ElementMeta;
import pageobjects._common.elements.*;
import pageobjects._common.modal.BaseModal;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;

public class StepTherapyModal extends BaseModal {

    @ElementMeta(fieldName = "Step Therapy Name", locator = "stepTherapyName")
    InputBox stepTherapyName;

    @ElementMeta(fieldName = "Step Therapy Type", locator = "stepTherapyType")
    SingleSelect stepTherapyType;

    @ElementMeta(fieldName = "Criteria", locator = "criteria")
    TextArea criteria;

    @ElementMeta(fieldName = "Patient Pay Notes", locator = "patientPayNotes")
    TextArea patientPayNotes;

    @ElementMeta(fieldName = "Lookback Period", locator = "lookbackPeriod")
    InputBox lookbackPeriod;

    @ElementMeta(fieldName = "Min Usage", locator = "minUsage")
    InputBox minUsage;

    @ElementMeta(fieldName = "Max Usage", locator = "maxUsage")
    InputBox maxUsage;

    @ElementMeta(fieldName = "Min Fills", locator = "minFills")
    InputBox minFills;

    @ElementMeta(fieldName = "Multi Source Indicator", locator = "multiSourceIndicator")
    SingleSelect multiSourceIndicator;

    @ElementMeta(fieldName = "OTC Name", locator = "otcName")
    TypeAheadSearch otcName;

    @ElementMeta(fieldName = "Drug Status", locator = "drugStatus")
    TypeAheadSearch drugStatus;

    @ElementMeta(fieldName = "Products", locator = "products")
    MultipleValueWithTypeSearch products;

    @ElementMeta(fieldName = "GPI", locator = "gpi")
    MultipleValueWithTypeSearch gpi;

    public StepTherapyModal() {
        super(StepTherapyModal.class);
    }

    public void set_value_in_age_dropbox(String value, String dropdownValue){
        $("p[class*='section__chevron']").click();
        $(".ui-choice-list").$$("*[data-option]").find(Condition.text(dropdownValue)).click();
        $(byAttribute("placeholder", "Age*")).setValue(value);
    }
    public void set_range_values_in_age_dropbox(String min, String max,String dropdownValue){
        $("p[class*='section__chevron']").click();
        $(".ui-choice-list").$$("*[data-option]").find(Condition.text(dropdownValue)).click();
        $(By.xpath("//div[@class='ui-value-and-type-select__field__range-input__input-fields']//div[1]//input[1]")).setValue(min);
        $(By.xpath("//div[@class='ui-value-and-type-select__field__range-input__input-fields']//div[2]//input[1]")).setValue(max);
    }

    public String get_error_message_in_age_field() {
       return $("p[class*='rules_']").getText();
    }

    public boolean get_visibility_of_form_section(String section) {
        return modal.$(byAttribute("data-element-name", ""+section+"")).$("div[class*='__item']").exists();
    }
}
