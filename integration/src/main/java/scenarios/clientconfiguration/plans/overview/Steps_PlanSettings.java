package scenarios.clientconfiguration.plans.overview;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageobjects.ClientConfigurationPage;
import pageobjects._common.containers.DataContainer;
import pageobjects.clientconfiguration.plans.editplans.overview.PlanSettings;
import scenarios.Context;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;

public class Steps_PlanSettings {

    private final ClientConfigurationPage configurationPage;
    Context context;

    public Steps_PlanSettings(Context context) {
        configurationPage = new ClientConfigurationPage(context);
        this.context = context;
    }

    private PlanSettings get_PlanSettings_container() {
        DataContainer container = configurationPage.getConfigurePlansPage().getDataContainer("Plan Settings");
        return (PlanSettings) container;
    }

    @Then("^The user opts to update the Plan Settings section in Plans overview Page$")
    public void user_updates_PlanSettings_section() {
        get_PlanSettings_container().updateContainerData();
    }

    @Then("^The user should validate the following form fields in Plan Settings Form$")
    public void user_should_validate_the_Plan_settings_form_fields(List<String> FormFields) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertThat(get_PlanSettings_container().updatePlanSettingsModal().getFormFields()).isEqualTo(FormFields);
    }

    @Then("^The user should validate the below heading coming in Plan Settings Form$")
    public void user_should_validate_the_Plan_settings_form_heading(List<String> FormFields) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertThat(get_PlanSettings_container().updatePlanSettingsModal().getFormFields()).isEqualTo(FormFields);
    }

    @Then("^The user opts to update the field (.+) with value (.+) in Plan settings form$")
    public void The_user_opts_the_following_Plan_in_the_Plan_Settings_form(String field,String value) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        get_PlanSettings_container().updatePlanSettingsModal().setValue(field, value);
    }

    @And("^The user should verify that the Field attribute (.+) in Plan Settings section is updated to (.+)$")
    public void user_verifies_field_attributes_values(String field, String value) {
        assertThat(get_PlanSettings_container().getAttributeValue(field)).isEqualTo(value);
    }

    @Then("^The user should validate side tab navigator (.+) should be now (.+) in Plan Edit Page$")
    public void user_validates_side_tabs_property(String fields, String Property) {
        boolean prop = false;
        if (Property.contentEquals("disabled")) prop = true;
        assertThat(get_PlanSettings_container().getEditPlanPageProperties().isFieldDisabled(fields)).isEqualTo(prop);
    }

    @Then("^The user should validate the below warning message on unchecking any plan$")
    public void get_warning_message(String WarningMessage) {
        assertThat(get_PlanSettings_container().updatePlanSettingsModal().getWarningMessage()).isEqualTo(WarningMessage);
    }

    @Then("^The user should validate the following attributes and values in Plan Settings section$")
    public void user_validates_attributes_and_values(Map<String,String> attributesValues) {
        Set<String> keySet = attributesValues.keySet();
        assertThat(get_PlanSettings_container().getAttributesWithValues(keySet)).isEqualTo(attributesValues);
    }

    @Then("^The user opts to update following fields in Plan Settings form$")
    public void user_opts_to_update_Plan_Settings_form(Map<String,String> fieldValue) {
        fieldValue.forEach((k, v) -> {
            try {
                get_PlanSettings_container().updatePlanSettingsModal().setValue(k, v);
            } catch (Exception e) {
                fail(e.getMessage());
            }
        }); }

    @Then("The following fields should be disabled on Plans settings form")
    public void verify_fields_are_disabled_in_Plan_settings(List<String> fields) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        HashMap<String, Boolean> expectedDisabledFields = new HashMap<>();
        fields.forEach((v) -> expectedDisabledFields.put(v, true));
        assertThat(get_PlanSettings_container().updatePlanSettingsModal().getDisabledFields(fields)).isEqualTo(expectedDisabledFields);
    }
}


