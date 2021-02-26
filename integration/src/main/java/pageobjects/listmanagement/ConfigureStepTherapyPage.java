package pageobjects.listmanagement;

import pageobjects._common.containers.DataContainer;
import pageobjects.listmanagement.steptherapy.StepTherapy;

public class ConfigureStepTherapyPage {

    public StepTherapy getStepTherapy(String tableName) {
        return new StepTherapy(tableName);
    }

    public DataContainer getDataContainer(String container) {
        switch (container.toUpperCase()) {
            case "STEP THERAPY SCHEDULE":
                return getStepTherapySchedule();
        }return null;
    }

    private DataContainer getStepTherapySchedule() {
        return new pageobjects.listmanagement.steptherapy.editStepTherapy.StepTherapySchedule("customizer--stepTherapyDetail");
    }
}
