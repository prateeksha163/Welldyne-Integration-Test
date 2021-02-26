package pageobjects.clientconfiguration.planattributes;

import pageobjects._common.containers.FlatDataContainer;
import pageobjects._common.elements.Popover;

public class PlanAttributes extends FlatDataContainer {
    public PlanAttributes(String name) {
        super(name);
    }

    public PlanAttributesModal updatePlanAttributes() {
        return new PlanAttributesModal();
    }

    public Popover getInfo() {
        getInfoButton().click();
        return new Popover();
    }
}