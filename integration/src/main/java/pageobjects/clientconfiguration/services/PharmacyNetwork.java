package pageobjects.clientconfiguration.services;

import pageobjects._common.containers.ParentDataContainer;

public class PharmacyNetwork extends ParentDataContainer {

    public PharmacyNetwork(String name) {
        super(name);
    }

    public PharmacyNetworkModal addUpdatePharmacyNetwork() {
        return new PharmacyNetworkModal();
    }

}
