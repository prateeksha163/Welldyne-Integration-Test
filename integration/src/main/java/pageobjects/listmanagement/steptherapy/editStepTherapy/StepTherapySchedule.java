package pageobjects.listmanagement.steptherapy.editStepTherapy;

import pageobjects._common.containers.FlatDataContainer;
import pageobjects.listmanagement.steptherapy.StepTherapyModal;

import static com.codeborne.selenide.Selenide.$;

public class StepTherapySchedule extends FlatDataContainer {

    public StepTherapySchedule(String name) {
        super(name);
    }

    public StepTherapyModal getUpdateStepTherapyScheduleModal() {
        return new StepTherapyModal();
    }

    public String getStepTherapyScheduleTitle() {
        return $("h2").text();
    }
}
