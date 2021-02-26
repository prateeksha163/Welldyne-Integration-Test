package scenarios.clientconfiguration.services;

import com.codeborne.selenide.Condition;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageobjects.ClientConfigurationPage;
import pageobjects._common.containers.DataContainer;
import pageobjects._common.modal.DeleteModal;
import pageobjects.clientconfiguration.services.PharmacyNetwork;
import scenarios.Context;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;

public class Steps_PharmacyNetwork {
    private final ClientConfigurationPage configurationPage;

    public Steps_PharmacyNetwork(Context context) {
        configurationPage = new ClientConfigurationPage(context);
    }

    private PharmacyNetwork getPharmacyNetworkContainer() {
        DataContainer container = configurationPage.getServiceConfiguration().getDataContainer("Pharmacy Networks");
        return (PharmacyNetwork) container;
    }

    @And("^The user opts to add new Pharmacy Network container$")
    public void user_adds_new_network_container() {
        getPharmacyNetworkContainer().addNestedContainer();
    }

    @And("^The user should see the following fields on the (?:add|update) Pharmacy Network channel overlay$")
    public void user_should_find_fields_on_add_Pharmacy_Network_channel(List<String> fields) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertThat(getPharmacyNetworkContainer().addUpdatePharmacyNetwork().getFormFields()).isEqualTo(fields);
    }

    @Then("^The user updates the form fields with (?:invalid character|value) in Pharmacy network form$")
    public void user_updates_the_Form_fields_with_invalid_and_valid_values(Map<String, String> Attributes_Values) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        try {
            for (Map.Entry<String, String> entry : Attributes_Values.entrySet()) {
                String k = entry.getKey();
                String v = entry.getValue();
                getPharmacyNetworkContainer().addUpdatePharmacyNetwork().setValue(k, v);
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Then("^The user should get following errors (.+) on field (.+)$")
    public void The_User_will_get_following_error_message(String errorMessage, String field) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String actualErrors = getPharmacyNetworkContainer().addUpdatePharmacyNetwork().getErrorText(field);
        assertThat(actualErrors).isEqualTo(errorMessage);
    }

    @And("^Pharmacy network should be created with heading (.+) and with following attributes and values$")
    public void new_pharmacy_network_should_be_created(String containerName, Map<String, String> attributes) {
        assertThat(getPharmacyNetworkContainer().getNestedContainerByName(containerName).getAttributesWithValues()).isEqualTo(attributes);
    }

    @And("^The user opts to update existing Pharmacy Network container (.+)$")
    public void user_updates_Pharmacy_Network(String containerName) {
        getPharmacyNetworkContainer().getNestedContainerByName(containerName).updateContainerData();
    }

    @And("^The user tries to delete (.+) network container$")
    public void user_Tries_To_Delete_The_Pharmacy_Network_Container(String containerName) {
        getPharmacyNetworkContainer().getNestedContainerByName(containerName).deleteContainer();
    }

    @Then("^(.+) Pharmacy network should not be visible in the service page$")
    public void Pharmacy_network_should_not_be_visible(String containerToDelete) {
        getPharmacyNetworkContainer().getCreateButton().shouldBe(Condition.visible);
        List<String> names = getPharmacyNetworkContainer().getNestedContainerNames();
        assertThat(names).doesNotContain(containerToDelete);
    }

    @Then("^User should see following acknowledgement message on confirmation Page$")
    public void User_will_see_acknowledgement_message(String message) {
        DeleteModal modal = new DeleteModal();
        assertThat(modal.errorOnConfirmation()).isEqualTo(message);
    }

    @And("^The user clears the previous value in (.*) field$")
    public void user_update_value_in_NCPDP_ID(String field) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        getPharmacyNetworkContainer().addUpdatePharmacyNetwork().clearValue(field);
    }
}