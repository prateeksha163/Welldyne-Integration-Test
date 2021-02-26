package pageobjects.clientconfiguration.plans.editplans.overview;

import pageobjects._common.annotations.ElementMeta;
import pageobjects._common.elements.Checkbox;
import pageobjects._common.elements.InputBox;
import pageobjects._common.elements.RadioOptions;
import pageobjects._common.modal.BaseModal;

public class CoordinationOfBenefitsModal extends BaseModal {

    @ElementMeta(fieldName = "Plan Type", locator = "planType")
    RadioOptions planType;

    @ElementMeta(fieldName = "Cost Share for Secondary Claim", locator = "secondaryClaimCostShare")
    RadioOptions secondaryClaimCostShare;

    @ElementMeta(fieldName = "Other Coverage Codes Allowed For COB", locator = "otherCoverageCodesAllowedForCOB")
    Checkbox otherCoverageCodesAllowedForCOB;

    @ElementMeta(fieldName = "Custom Cost Share", locator = "customCostShare")
    InputBox customCostShare;

    public CoordinationOfBenefitsModal() {
        super(CoordinationOfBenefitsModal.class);
    }
}
