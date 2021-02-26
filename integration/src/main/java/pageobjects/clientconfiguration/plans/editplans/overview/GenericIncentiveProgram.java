package pageobjects.clientconfiguration.plans.editplans.overview;

import pageobjects._common.containers.FlatDataContainer;

public class GenericIncentiveProgram extends FlatDataContainer {

    public GenericIncentiveProgram(String name) {
        super(name);
    }

    public GenericIncentiveProgramModal updateGenericIncentiveProgram() {
        return new GenericIncentiveProgramModal();
    }

}
