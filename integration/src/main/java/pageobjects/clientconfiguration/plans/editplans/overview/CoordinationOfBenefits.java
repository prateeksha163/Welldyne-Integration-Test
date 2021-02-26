package pageobjects.clientconfiguration.plans.editplans.overview;

import pageobjects._common.containers.FlatDataContainer;

public class CoordinationOfBenefits extends FlatDataContainer {

    public CoordinationOfBenefits(String name) {
        super(name);
    }

    public CoordinationOfBenefitsModal updateCoordinationOfBenefits() {
        return new CoordinationOfBenefitsModal();
    }

}

