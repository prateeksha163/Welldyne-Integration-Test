package pageobjects.clientconfiguration.plans.editplans.overview;

import pageobjects._common.containers.FlatDataContainer;
import pageobjects.clientconfiguration.plans.editplans.EditPlan;

public class PlanSettings extends FlatDataContainer {

    public PlanSettings(String name) {
        super(name);
    }

    public PlanSettingsModal updatePlanSettingsModal() {
        return new PlanSettingsModal();
    }

    public EditPlan getEditPlanPageProperties()
    {
        return new EditPlan();
    }

}
