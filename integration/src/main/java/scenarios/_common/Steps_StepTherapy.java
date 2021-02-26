package scenarios._common;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageobjects.ListManagementPage;
import pageobjects._common.modal.BaseModal;
import pageobjects.listmanagement.steptherapy.StepTherapy;

import static org.fest.assertions.api.Assertions.assertThat;

public class Steps_StepTherapy {

    private final ListManagementPage listManagementPagePage;

    public Steps_StepTherapy(ListManagementPage listManagementPagePage) {
        this.listManagementPagePage = listManagementPagePage;
    }

    @And("^The user clicks on the (.+) button on Step Therapy form$")
    public void user_clicks_on_action_button_on_modal(String action) {
        BaseModal modal = new BaseModal();
        modal.clickOnActionButton(action);
    }

    @And("^The (.+) section should be present on the Step Therapy page$")
    public void user_validates_form_section_should_be_visible(String section) {
        StepTherapy getStepTherapy = listManagementPagePage.getConfigureStepTherapyPage().getStepTherapy("stepTherapyList");
        switch (section.toUpperCase()) {
            case "SEQUENCES":
                assertThat(getStepTherapy.getUpdateStepTherapy().get_visibility_of_form_section(section)).isTrue();
            case "RULES":
                assertThat(getStepTherapy.getUpdateStepTherapy().get_visibility_of_form_section(section)).isTrue(); }
    }

    @And("^The (.+) section should not be present on the Step Therapy page$")
    public void user_validates_form_section_should_be_not_visible(String section) {
        StepTherapy getStepTherapy = listManagementPagePage.getConfigureStepTherapyPage().getStepTherapy("stepTherapyList");
        switch (section.toUpperCase()) {
            case "SEQUENCES":
                assertThat(getStepTherapy.getUpdateStepTherapy().get_visibility_of_form_section(section)).isFalse();
            case "RULES":
                assertThat(getStepTherapy.getUpdateStepTherapy().get_visibility_of_form_section(section)).isFalse();}
    }

    @And("The user clicks on remove button present on the section (.+)")
    public void user_clicks_on_remove_button(String section) {
        BaseModal modal = new BaseModal();
        modal.removeSection(section);
    }

    @Then("The user clicks on (.+) button for column (.+) and value (.+) in Step Therapy Table")
    public void clicks_on_action_button_in_Step_therapy_table_to_perform_action(String action, String field, String planCode) throws InterruptedException {
        Thread.sleep(2000);
        StepTherapy getStepTherapy = listManagementPagePage.getConfigureStepTherapyPage().getStepTherapy("stepTherapyList");
        getStepTherapy.clickOnActionButtonOnTable(action, field, planCode);
    }
}

