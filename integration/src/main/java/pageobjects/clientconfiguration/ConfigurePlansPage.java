package pageobjects.clientconfiguration;

import pageobjects._common.containers.DataContainer;
import pageobjects.clientconfiguration.plans.Plans;
import pageobjects.clientconfiguration.plans.TemplatePlanTable;
import pageobjects.clientconfiguration.plans.editplans.accumulators.AccumulatorSettings;
import pageobjects.clientconfiguration.plans.editplans.drugsettings.Compounds;
import pageobjects.clientconfiguration.plans.editplans.drugsettings.MaintenanceDrug;
import pageobjects.clientconfiguration.plans.editplans.overview.*;

public class ConfigurePlansPage {

    public Plans getPlans(String tableName) {
        return new Plans(tableName);
    }

    public TemplatePlanTable getTemplatePlans(String tableName) {
        return new TemplatePlanTable(tableName);
    }


    public DataContainer getDataContainer(String container) {
        switch (container.toUpperCase()) {
            case "ABOUT":
                return getAbout();
            case "PLAN SETTINGS":
                return getPlanSettings();
            case "ACCUMULATOR SETTINGS":
                return getAccumulatorSettings();
            case "CO-ORDINATION OF BENEFITS":
                return getCoordinationOfBenefits();
            case "GENERIC INCENTIVE PROGRAM":
                return getGenericIncentiveProgram();
            case "COMPOUNDS":
                return getCompounds();
            case "MAINTENANCE DRUG":
                return getMaintenanceDrug();

        }
        return null;
    }

    private DataContainer getMaintenanceDrug() {
        return new MaintenanceDrug("widget--planDrugSettingsMaintenanceDrug");
    }

    private DataContainer getAbout() {
        return new PlansAbout("widget--planOverviewAbout");
    }

    private DataContainer getPlanSettings() {
        return new PlanSettings("planOverviewPlanSettings");
    }

    private DataContainer getAccumulatorSettings() {
        return new AccumulatorSettings("widget--drugEditAccumulatorSettings");
    }

    private DataContainer getCoordinationOfBenefits() {
        return new CoordinationOfBenefits("planOverviewCoordinationOfBenefits");
    }

    private DataContainer getGenericIncentiveProgram() {
        return new GenericIncentiveProgram("planOverviewGenericIncentiveProgram");
    }

    private DataContainer getCompounds() {
        return new Compounds("widget--planDrugSettingsCompounds");
    }

}
