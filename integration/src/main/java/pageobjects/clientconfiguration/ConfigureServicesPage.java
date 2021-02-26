package pageobjects.clientconfiguration;

import pageobjects._common.containers.DataContainer;
import pageobjects.clientconfiguration.services.PharmacyNetwork;
import pageobjects.clientconfiguration.services.ClaimSubmissionWindows;

public class ConfigureServicesPage {

    public DataContainer getDataContainer(String container) {
        switch (container.toUpperCase()) {
            case "PHARMACY NETWORKS":
                return getPharmacyNetworks();
            case "CLAIM SUBMISSION WINDOWS":
                return getClaimSubmissionWindows();
        }
        return null;
    }

    private DataContainer getPharmacyNetworks() {
        return new PharmacyNetwork("pharmacyNetworks");
    }
        private DataContainer getClaimSubmissionWindows () {
            return new ClaimSubmissionWindows("claimSubmissionWindows");

        }
    }
