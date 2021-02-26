package scenarios.clientconfiguration.plans.accumulators;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.ClientConfigurationPage;
import pageobjects.clientconfiguration.plans.editplans.accumulators.AccumulatorSettings;
import scenarios.Context;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.testng.Assert.fail;

public class Steps_Accumulators {

    final private AccumulatorSettings container;

    public Steps_Accumulators(Context context) {
        ClientConfigurationPage configurationPage = new ClientConfigurationPage(context);
        container = (AccumulatorSettings) configurationPage.getConfigurePlansPage().getDataContainer("Accumulator Settings");
    }

    @When("^The user opts to add a new Accumulator setting$")
    public void add_new_accum_setting() {
        container.addNestedContainer();
    }

    @When("^The user opts to select (.+) value in (.+) field$")
    public void select_accumulator_type(String value, String field) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        container.getAccumSettingsModal().setValue(field, value);
    }

    @When("^The user enters the following values in the Accumulator Settings modal$")
    public void enter_form_details(Map<String, String> fieldValues) {
        fieldValues.forEach((k, v) -> {
            try {
                container.getAccumSettingsModal().setValue(k, v);
            } catch (Exception e) {
                fail(e.getMessage());
            }
        });
    }

    @Then("^The user should see the accumulator setting with name (.+) present in the Accumulator Settings section$")
    public void verify_accumulator_setting_is_present(String accumName) throws InterruptedException {
        Thread.sleep(5000);
        assertThat(container.getNestedContainerByName(accumName)).isNotNull();
    }

    @Then("The user validates if the following fields are present in accumulator settings modal")
    public void validate_fields_are_present_in_accumulator_settings_modal(List<String> fields) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertThat(container.getAccumSettingsModal().getFormFields()).isEqualTo(fields);
    }

    @Then("The user validates the error messages on the following mandatory fields")
    public void validate_error_messages(Map<String, String> errorMsgs) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<String> fields = new ArrayList<>();
        errorMsgs.forEach((k, v) -> fields.add(k));
        assertThat(container.getAccumSettingsModal().getErrorText(fields)).isEqualTo(errorMsgs);
    }

    @Then("The user should see the following error message on (.+) field on Accumulator Modal")
    public void validate_error_message_on_field(String field, String errorMsg) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertThat(container.getAccumSettingsModal().getErrorText(field)).isEqualTo(errorMsg);
    }

    @Then("The user validates if the add button is disabled")
    public void validate_add_button_disabled() throws InterruptedException {
        Thread.sleep(4000);
        assertThat(container.getCreateButton().exists()).isFalse();
    }

    @Then("^The user opts to (delete|edit) an Accumulator setting with name (.+)$")
    public void delete_or_view_accum_property(String action, String name) {
        switch (action.toUpperCase()) {
            case "EDIT":
                container.getNestedContainerByName(name).updateContainerData();
                break;
            case "DELETE":
                container.getNestedContainerByName(name).deleteContainer();
                break;
        }
    }

    @Then("^The user should not see the Accumulator setting with name (.+) present$")
    public void verify_property_not_present(String name) throws InterruptedException {
        Thread.sleep(4000);
        assertThat(container.getNestedContainerByName(name)).isNull();

    }

    @Then("^The user should see Accumulator Setting (.+) updated with field (.+) and value (.+)$")
    public void verify_field_value_updated(String name, String field, String value) throws InterruptedException {
        Thread.sleep(4000);
        assertThat(container.getNestedContainerByName(name).getAttributeValue(field)).isEqualTo(value);
    }
}