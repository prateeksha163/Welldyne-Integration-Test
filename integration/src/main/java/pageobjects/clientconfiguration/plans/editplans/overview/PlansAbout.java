package pageobjects.clientconfiguration.plans.editplans.overview;

import pageobjects._common.containers.FlatDataContainer;

public class PlansAbout extends FlatDataContainer {

    public PlansAbout(String name) {
        super(name);
    }

    public AboutModal updateAboutModal() {
        return new AboutModal();
    }
}

