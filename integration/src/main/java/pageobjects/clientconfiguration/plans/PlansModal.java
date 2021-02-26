package pageobjects.clientconfiguration.plans;

import pageobjects._common.annotations.ElementMeta;
import pageobjects._common.elements.*;
import pageobjects._common.modal.BaseModal;

public class PlansModal extends BaseModal {

    @ElementMeta(fieldName = "Plan Name", locator = "planName")
    InputBox planName;

    @ElementMeta(fieldName = "Plan Code", locator = "planCode")
    InputBox planCode;

    @ElementMeta(fieldName = "Notes", locator = "planDescription")
    TextArea planDescription;

    @ElementMeta(fieldName = "Copy Existing Plan", locator = "copyExistingPlan")
    RadioOptions copyExistingPlan;

    @ElementMeta(fieldName = "Effective Date", locator = "effectiveDate")
    InputBox effectiveDate;

    @ElementMeta(fieldName = "Termination Date", locator = "terminationDate")
    InputBox terminationDate;

    @ElementMeta(fieldName = "Plan Benefit Year", locator = "planBenefitYear")
    InputBox planBenefitYear;

    @ElementMeta(fieldName = "Search Plan", locator = "searchPlan")
    TypeAheadSearch searchPlan;

    public PlansModal() {
        super(PlansModal.class);
    }

}