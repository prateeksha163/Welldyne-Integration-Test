package pageobjects.clientconfiguration.plans.editplans.accumulators;

import pageobjects._common.annotations.ElementMeta;
import pageobjects._common.elements.Checkbox;
import pageobjects._common.elements.InputBox;
import pageobjects._common.elements.RadioOptions;
import pageobjects._common.modal.BaseModal;

public class AccumulatorSettingsModal extends BaseModal {

    @ElementMeta(fieldName = "Select Accumulator", locator = "accumulatorType")
    RadioOptions selectAccumulator;

    @ElementMeta(fieldName = "Select Mode", locator = "mode")
    RadioOptions selectMode;

    @ElementMeta(fieldName = "Select Category", locator = "category")
    RadioOptions selectCategory;

    @ElementMeta(fieldName = "Select Period", locator = "period")
    RadioOptions selectPeriod;

    @ElementMeta(fieldName = "Select Tracking", locator = "tracking")
    RadioOptions selectTracking;

    @ElementMeta(fieldName = "Fourth Quarter Carry Over", locator = "carryOver")
    RadioOptions fourthQuarterCarryOver;

    @ElementMeta(fieldName = "PSC/DAW Penalties Applies", locator = "pscDawPenaltiesApplyToAccumulator")
    RadioOptions penaltiesApplies;

    @ElementMeta(fieldName = "Penalty Continues after OOP Is met", locator = "penaltyContinueAfterOopIsMet")
    RadioOptions penaltyContinuesAfterOOPIsMet;

    @ElementMeta(fieldName = "Accumulator is Near Real Time", locator = "realTimeAccumulator")
    RadioOptions accumulatorIsNearRealTime;

    @ElementMeta(fieldName = "Bypass Category", locator = "byPassCategories")
    Checkbox byPassCategory;

    @ElementMeta(fieldName = "Period Start Date From", locator = "customPeriodStartDate")
    InputBox periodStartDateFrom;

    @ElementMeta(fieldName = "Period End Date To", locator = "customPeriodEndDate")
    InputBox periodStartDateTo;

    @ElementMeta(fieldName = "Claim Adjudication after Benefit Maximum is met", locator = "claimAdjudicationAfterBenefitMaximumIsMet")
    RadioOptions claimAdjudicationAfterBenefitMaximumIsMet;

    @ElementMeta(fieldName = "Effective From Date", locator = "effectiveFromDate")
    InputBox effectiveFromDate;

    public AccumulatorSettingsModal() {
        super(AccumulatorSettingsModal.class);
    }
}
