package pageobjects.clientconfiguration.services;

import pageobjects._common.annotations.ElementMeta;
import pageobjects._common.elements.InputBox;
import pageobjects._common.elements.SingleSelect;
import pageobjects._common.modal.BaseModal;

public class ClaimSubmissionWindowsModal extends BaseModal {

    @ElementMeta(fieldName = "Rx Fill Submit Window", locator = "rxFillSubmitWindow")
    InputBox rxFillSubmitWindow;

    @ElementMeta(fieldName = "Rx Fill Re-Submit Window", locator = "rxFillReSubmitWindow")
    InputBox rxFillReSubmitWindow;

    @ElementMeta(fieldName = "Rx Fill Window", locator = "rxFillWindow")
    InputBox rxFillWindow;

    @ElementMeta(fieldName = "Manual Claim Submit Window", locator = "dmrSubmissionTimeFrame")
    InputBox dmrSubmissionTimeFrame;

    @ElementMeta(fieldName = "Allow Manual Claims", locator = "allowManualClaims")
    SingleSelect allowManualClaims;

    @ElementMeta(fieldName = "Allow E1 Transaction", locator = "allowE1Transaction")
    SingleSelect allowE1Transaction;

    public ClaimSubmissionWindowsModal() {
        super(ClaimSubmissionWindowsModal.class);
    }
}