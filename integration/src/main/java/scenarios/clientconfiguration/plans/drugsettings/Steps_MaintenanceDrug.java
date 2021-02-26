package scenarios.clientconfiguration.plans.drugsettings;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.ClientConfigurationPage;
import pageobjects.clientconfiguration.plans.editplans.drugsettings.MaintenanceDrug;
import scenarios.Context;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.testng.Assert.fail;

public class Steps_MaintenanceDrug {

    private final MaintenanceDrug container;

    public Steps_MaintenanceDrug(Context context) {
        ClientConfigurationPage configurationPage = new ClientConfigurationPage(context);
        container = (MaintenanceDrug) configurationPage.getConfigurePlansPage().getDataContainer("Maintenance Drug");
    }

    @When("^The user opts to add a maintenance drug$")
    public void add_maintenance_drug() {
        container.addNestedContainer();
    }

    @When("^The user enters the following values in maintenance drug modal$")
    public void enter_form_details(Map<String, String> fieldValues) {
        fieldValues.forEach((k, v) -> {
            try {
                container.getMaintenanceDrugModal().setValue(k, v);
            } catch (Exception e) {
                fail(e.getMessage());
            }
        });
    }


    @Then("The user should see the following Maintenance Drug present (.+)")
    public void verify_maintenance_drug_present(String name) throws InterruptedException {
        Thread.sleep(4000);
        assertThat(container.getNestedContainerByName(name)).isNotNull();
    }

    @Then("User validates that the add button is removed")
    public void validate_add_button_is_removed() throws InterruptedException {
        Thread.sleep(4000);
        assertThat(container.getCreateButton().exists()).isFalse();
    }

    @Then("The user validates the following error messages on the Maintenance Drug Modal")
    public void verify_error_messages(Map<String, String> errorMsgs) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<String> fields = new ArrayList<>();
        errorMsgs.forEach((k, v) -> fields.add(k));
        assertThat(container.getMaintenanceDrugModal().getErrorText(fields)).isEqualTo(errorMsgs);
    }

    @Then("The user should not see the following maintenance drug present (.+)")
    public void verify_maintenance_drug_not_present(String name) throws InterruptedException {
        Thread.sleep(4000);
        assertThat(container.getNestedContainerByName(name)).isNull();
    }

    @When("^The user opts to (edit|delete) a maintenance drug having name (.+)$")
    public void edit_delete_a_drug(String option, String name) {
        switch (option.toUpperCase()) {
            case "EDIT":
                container.getNestedContainerByName(name).updateContainerData();
                break;
            case "DELETE":
                container.getNestedContainerByName(name).deleteContainer();
                break;
        }
    }

    @Then("The user should not see the maintenance drug with name (.+) present")
    public void verify_drug_not_present(String name) throws InterruptedException {
        Thread.sleep(4000);
        assertThat(container.getNestedContainerByName(name)).isNull();
    }

    @Then("The user should see the maintenance drug with name (.+) present in the Maintenance Drug section")
    public void verify_drug_is_present(String name) throws InterruptedException {
        Thread.sleep(4000);
        assertThat(container.getNestedContainerByName(name)).isNotNull();
    }

    @When("^The user clears the following form fields on maintenance drug form$")
    public void clear_fields(List<String> fields) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        container.getMaintenanceDrugModal().clearValue(fields);
    }

    @Then("The user should see the maintenance drug with attribute (.+) and value (.+) present")
    public void verify_drug_with_attributes_is_present(String name, String value) throws InterruptedException {
        Thread.sleep(4000);
        assertThat(container.getNestedContainerByAttribute(name, value)).isNotNull();
    }
}
