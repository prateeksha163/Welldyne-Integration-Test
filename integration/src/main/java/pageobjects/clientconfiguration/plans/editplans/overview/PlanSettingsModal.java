package pageobjects.clientconfiguration.plans.editplans.overview;

import pageobjects._common.annotations.ElementMeta;
import pageobjects._common.elements.RadioOptions;
import pageobjects._common.modal.BaseModal;

import static com.codeborne.selenide.Selenide.$;

public class PlanSettingsModal extends BaseModal {
    @ElementMeta(fieldName = "Plan Type", locator = "hdhpOrHsaPlan")
    RadioOptions hdhpOrHsaPlan;

    @ElementMeta(fieldName = "Accumulators Derived", locator = "accumulatorsDerivedFrom")
    RadioOptions accumulatorsDerivedFrom;

    @ElementMeta(fieldName = "CostShare Derived", locator = "costShareDerivedFrom")
    RadioOptions costShareDerivedFrom;

    @ElementMeta(fieldName = "Maintenance Drugs", locator = "supportsMaintenanceDrugs")
    RadioOptions supportsMaintenanceDrugs;

    @ElementMeta(fieldName = "Supports Compounds", locator = "supportsCompounds")
    RadioOptions supportsCompounds;

    @ElementMeta(fieldName = "Coordination Of Benefit", locator = "coordinationOfBenefitRequired")
    RadioOptions coordinationOfBenefitRequired;

    @ElementMeta(fieldName = "Generic Incentive Program", locator = "genericIncentiveProgramOptIn")
    RadioOptions genericIncentiveProgramOptIn;

    @ElementMeta(fieldName = "In House Prescriber Network", locator = "hasInHousePrescriberNetwork")
    RadioOptions hasInHousePrescriberNetwork;

    @ElementMeta(fieldName = "In House Pharmacy Network", locator = "hasInHousePharmacyNetwork")
    RadioOptions hasInHousePharmacyNetwork;

    public PlanSettingsModal() {
        super(PlanSettingsModal.class);
    }

    public String getWarningMessage() {
        return $("p[class*='section__text']").getText();
    }
}