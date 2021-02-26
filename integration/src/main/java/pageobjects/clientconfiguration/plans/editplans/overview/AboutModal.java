package pageobjects.clientconfiguration.plans.editplans.overview;

import pageobjects._common.annotations.ElementMeta;
import pageobjects._common.elements.InputBox;
import pageobjects._common.elements.TextArea;
import pageobjects._common.modal.BaseModal;

public class AboutModal extends BaseModal {
    @ElementMeta(fieldName = "Plan Name", locator = "planName")
    InputBox planName;

    @ElementMeta(fieldName = "Plan Code", locator = "planCode")
    InputBox planCode;

    @ElementMeta(fieldName = "Notes", locator = "planDescription")
    TextArea planDescription;

    @ElementMeta(fieldName = "Term Date", locator = "termedDate")
    InputBox termedDate;

    @ElementMeta(fieldName = "Effective Date", locator = "effectiveDate")
    InputBox effectiveDate;

    @ElementMeta(fieldName = "Plan Benefit Year", locator = "planBenefitYear")
    InputBox planBenefitYear;

    public AboutModal() {
        super(AboutModal.class);
    }
}
