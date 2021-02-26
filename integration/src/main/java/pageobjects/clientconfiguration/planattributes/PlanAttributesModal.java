package pageobjects.clientconfiguration.planattributes;
import pageobjects._common.annotations.ElementMeta;
import pageobjects._common.elements.RadioOptions;
import pageobjects._common.modal.BaseModal;

public class PlanAttributesModal extends BaseModal {
    @ElementMeta(fieldName = "Vacation Supply Up To 90 Days", locator = "vacationSupplyUpto90Day")
    RadioOptions vacationSupplyUpto90Day;

    @ElementMeta(fieldName = "Vacation Supply Greater Than 90 Days", locator = "vacationSupplyGreaterThan90Days")
    RadioOptions vacationSupplyGreaterThan90Days;

    @ElementMeta(fieldName = "Refill Too Soon", locator = "refillTooSoon")
    RadioOptions refillTooSoon;

    @ElementMeta(fieldName = "Lost Medication", locator = "lostMedication")
    RadioOptions lostMedication;

    @ElementMeta(fieldName = "Maximum Claim Dollar Exceeds Amount", locator = "maximumClaimDollarExceedsAmount")
    RadioOptions maximumClaimDollarExceedsAmount;

    @ElementMeta(fieldName = "Increased Dosage", locator = "increasedDosage")
    RadioOptions increasedDosage;

    @ElementMeta(fieldName = "Medical Exception", locator = "medicalException")
    RadioOptions medicalException;

    @ElementMeta(fieldName = "PA Appeals First Level", locator = "paAppealsFirstLevel")
    RadioOptions paAppealsFirstLevel;

    @ElementMeta(fieldName = "PA Appeals Second Level", locator = "paAppealsSecondLevel")
    RadioOptions paAppealsSecondLevel;

    @ElementMeta(fieldName = "Clinical PAs", locator = "clinicalPAs")
    RadioOptions clinicalPAs;

    @ElementMeta(fieldName = "Age Related Rules", locator = "ageRelatedRules")
    RadioOptions ageRelatedRules;

    @ElementMeta(fieldName = "Step Therapy", locator = "stepTherapy")
    RadioOptions stepTherapy;


    public PlanAttributesModal() {
        super(PlanAttributesModal.class);
    }
}