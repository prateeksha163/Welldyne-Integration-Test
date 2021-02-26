package scenarios.clientconfiguration.services;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.ClientConfigurationPage;
import pageobjects._common.containers.DataContainer;
import pageobjects.clientconfiguration.services.ClaimSubmissionWindows;
import scenarios.Context;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;

public class Steps_Claim_Submission_Windows {

    private final ClientConfigurationPage configurationPage;

    public Steps_Claim_Submission_Windows(Context context) {
        configurationPage = new ClientConfigurationPage(context);
    }

    private ClaimSubmissionWindows getClaim_Submission_Windows_container() {
        DataContainer container = configurationPage.getServiceConfiguration().getDataContainer("claim Submission Windows");
        return (ClaimSubmissionWindows) container;
    }

    @When("^The user opts to update the claim submission window configuration in the services tab$")
    public void user_opts_to_modify_claim_submission_form()  {
        getClaim_Submission_Windows_container().updateContainerData();
    }

    @Then("^The user should see the following values against given attributes on the claim submission windows section$")
    public void user_should_see_values_against_attributes(Map<String, String> updatedValues) {
        Set<String> keySet = updatedValues.keySet();
        assertThat(getClaim_Submission_Windows_container().getAttributesWithValues(keySet))
                .isEqualTo(updatedValues);
    }

    @And("^The user must see the following form fields in claim submission window configuration$")
    public void user_must_see_all_the_form_fields(List<String> formFields) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertThat(getClaim_Submission_Windows_container().updateClaimSubmission().getFormFields()).isEqualTo(formFields);
    }

    @Then("^The user updates the following fields with their respective values on claim submission form$")
    public void user_updates_claim_submission_values(Map<String, String> fields) {
        fields.forEach((k, v) -> {
            try {
                getClaim_Submission_Windows_container().updateClaimSubmission().setValue(k, v);
            } catch (Exception e) {
                fail(e.getMessage());
            }
        }); }

    @Then("^The user should get the following errors (.+) on field (.+) in claim submission form$")
    public void validate_error_message(String error, String field) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertThat(getClaim_Submission_Windows_container().updateClaimSubmission().getErrorText(field)).isEqualTo(error);
    }

    @Then("^(.+) field should not be visible on claim submission form$")
    public void AllowE1Transaction_field_should_not_be_visible(String field) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertThat(getClaim_Submission_Windows_container().updateClaimSubmission()
                .getFormFields()).doesNotContain(field);
    }

    @Then("^The user updates the field (.+) with (?:invalid character|value) (.+) on claim submission form$")
    public void user_updates_field_with_value(String field, String value) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        getClaim_Submission_Windows_container().updateClaimSubmission().setValue(field, value);
    }

    @And("^The user clears all the form fields$")
    public void The_user_clears_all_the_form_fields(List<String> formFields) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        for (String s : formFields) getClaim_Submission_Windows_container().updateClaimSubmission().clearValue(s);
    }

    @And("The user should see the following fields as disabled in claim submission form")
    public void allowE1Transaction_field_should_be_disabled(List<String> field) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        HashMap<String, Boolean> expectedDisabledFields = new HashMap<>();
        field.forEach((v) -> expectedDisabledFields.put(v, true));
        assertThat(getClaim_Submission_Windows_container().updateClaimSubmission().getDisabledFields(field)).isEqualTo(expectedDisabledFields);
    }

    @And("The user validates value coming in (.+) should be by default (.+)")
    public void validate_default_value_coming_in_disabled_field(String field, String value) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertThat(getClaim_Submission_Windows_container().updateClaimSubmission().getValue(field)).isEqualTo(value);
    }
}