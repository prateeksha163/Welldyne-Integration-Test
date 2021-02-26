package pageobjects.clientconfiguration.plans.editplans.overview;

import pageobjects._common.annotations.ElementMeta;
import pageobjects._common.elements.Checkbox;
import pageobjects._common.elements.InputBox;
import pageobjects._common.elements.RadioOptions;
import pageobjects._common.elements.TextArea;
import pageobjects._common.modal.BaseModal;

public class GenericIncentiveProgramModal extends BaseModal {

    @ElementMeta(fieldName = "PSC DAW Codes", locator = "pscDawCodes")
    Checkbox pscDawCodes;

    @ElementMeta(fieldName = "Penalty Notes", locator = "penaltyNotes")
    TextArea penaltyNotes;

    @ElementMeta(fieldName = "Copay Applied", locator = "copayApplied")
    RadioOptions copayApplied;

    public GenericIncentiveProgramModal() {
        super(GenericIncentiveProgramModal.class);
    }
}
