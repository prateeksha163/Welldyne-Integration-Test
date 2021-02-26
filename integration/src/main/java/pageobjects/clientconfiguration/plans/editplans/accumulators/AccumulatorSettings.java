package pageobjects.clientconfiguration.plans.editplans.accumulators;

import pageobjects._common.containers.ParentDataContainer;

public class AccumulatorSettings extends ParentDataContainer {

    public AccumulatorSettings(String name) {
        super(name);
    }

    public AccumulatorSettingsModal getAccumSettingsModal(){
        return new AccumulatorSettingsModal();
    }
}
