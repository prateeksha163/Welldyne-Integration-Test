package pageobjects.clientconfiguration.plans.editplans.drugsettings;

import pageobjects._common.containers.FlatDataContainer;

public class Compounds extends FlatDataContainer {
    public Compounds(String name) {
        super(name);
    }

    public CompoundsModal getCompoundsModal(){
        return new CompoundsModal();
    }
}
