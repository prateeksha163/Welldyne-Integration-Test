package pageobjects.clientconfiguration.services;

import pageobjects._common.containers.FlatDataContainer;
import pageobjects._common.containers.ParentDataContainer;
import pageobjects.clientconfiguration.planattributes.PlanAttributesModal;

public class ClaimSubmissionWindows extends FlatDataContainer {

    public ClaimSubmissionWindows(String name) {
        super(name);
    }
    public ClaimSubmissionWindowsModal updateClaimSubmission() {
        return new ClaimSubmissionWindowsModal();
    }
}
