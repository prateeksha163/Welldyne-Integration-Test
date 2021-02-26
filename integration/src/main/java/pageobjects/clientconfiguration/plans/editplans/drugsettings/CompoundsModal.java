package pageobjects.clientconfiguration.plans.editplans.drugsettings;

import pageobjects._common.annotations.ElementMeta;
import pageobjects._common.elements.InputBox;
import pageobjects._common.elements.SingleSelect;
import pageobjects._common.elements.TextArea;
import pageobjects._common.modal.BaseModal;

public class CompoundsModal extends BaseModal {

    @ElementMeta(fieldName = "High Dollar Limit", locator = "highDollarLimit")
    InputBox highDollarLimit;

    @ElementMeta(fieldName = "Exceed High Dollar Status", locator = "handlingOverHighDollar")
    SingleSelect exceedHighDollarStatus;

    @ElementMeta(fieldName = "Minimum Ingredients", locator = "minimumIngredients")
    InputBox minimumIngredients;

    @ElementMeta(fieldName = "Maximum Ingredients", locator = "maximumIngredients")
    InputBox maximumIngredients;

    @ElementMeta(fieldName = "Clarification Code Recognition", locator = "clarificationCodeRecognition")
    SingleSelect clarificationCodeRecognition;

    @ElementMeta(fieldName = "Legend Ingredient Required", locator = "legendIngredientRequired")
    SingleSelect legendIngredientRequired;

    @ElementMeta(fieldName = "Maximum Refills", locator = "maximumRefills")
    InputBox maximumRefills;

    @ElementMeta(fieldName = "Fixed Copay", locator = "fixedCopay")
    InputBox fixedCopay;

    @ElementMeta(fieldName = "Notes", locator = "notes")
    TextArea notes;

    public CompoundsModal() { super(CompoundsModal.class); }

}
