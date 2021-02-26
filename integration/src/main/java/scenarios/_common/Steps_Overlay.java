package scenarios._common;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageobjects._common.modal.BaseModal;
import pageobjects._common.modal.DeleteModal;
import scenarios.Context;

import static org.fest.assertions.api.Assertions.assertThat;

public class Steps_Overlay {

    public Steps_Overlay(Context context) {
    }

    @And("^The user clicks on the (.+) button on the modal overlay$")
    public void user_performs_action_on_modal(String action) {
        BaseModal modal = new BaseModal();
        modal.clickOnActionButton(action);
        modal.shouldNotExist();
    }

    @And("^The user clicks on the (.+) button on the delete modal$")
    public void user_clicks_on_Action_button_on_delete_modal_overlay(String action) {
        DeleteModal modal = new DeleteModal();
        modal.clickOnActionButton(action);
        modal.shouldNotExist();
    }

    @And("^The user clicks on the (.+) button on the modal overlay to verify error message$")
    public void user_performs_action_on_modal_overlay(String action) {
        BaseModal modal = new BaseModal();
        modal.clickOnActionButton(action);
    }

    @Then("^The user clicks on confirm button to verify errors$")
    public void user_clicks_on_confirm_button_to_verify_errors() {
        DeleteModal modal = new DeleteModal();
        modal.clickConfirmButton();
    }

    @Then("^The user should see the delete icon present in the delete modal$")
    public void check_icon_image_visibility() {
        DeleteModal modal = new DeleteModal();
        assertThat(modal.isImageVisible()).isTrue();
    }

    @Then("The user should see the (.+) with value as (.+) on the delete modal")
    public void verify_text_on_delete_modal(String section, String text) {
        switch (section.toUpperCase()) {
            case "TITLE":
                assertThat(new DeleteModal().getTitle()).isEqualTo(text);
                break;
            case "DESCRIPTION":
                assertThat(new DeleteModal().getDescription()).isEqualTo(text);
        }
    }
}
