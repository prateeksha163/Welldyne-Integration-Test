package pageobjects.clientconfiguration.plans.editplans.drugsettings;

import pageobjects._common.containers.ParentDataContainer;

public class MaintenanceDrug extends ParentDataContainer {

    public MaintenanceDrug(String name) {
        super(name);
    }

    public MaintenanceDrugModal getMaintenanceDrugModal(){
        return new MaintenanceDrugModal();
    }

}
