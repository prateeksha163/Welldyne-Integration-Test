package scenarios.clientconfiguration.overview;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.ClientConfigurationPage;
import pageobjects._common.containers.NestedDataContainer;
import pageobjects.clientconfiguration.overview.Contacts;
import scenarios.Context;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;

public class Steps_Contacts {

    private final ClientConfigurationPage configurationPage;
    private final Contacts container;

    public Steps_Contacts(Context context) {
        configurationPage = new ClientConfigurationPage(context);
        container = (Contacts) configurationPage.getClientOverviewPage().getDataContainer("Contacts");
    }

    private Contacts getNewContactsReference() {
        return (Contacts) configurationPage.getClientOverviewPage().getDataContainer("Contacts");
    }

    @When("^The user opts to add a new Contact$")
    public void add_a_new_contact() {
        container.addNewContact();
    }

    @And("^The user updates the following fields with values on contacts overlay$")
    public void user_opts_to_update_contacts_form(Map<String, String> fieldValues) {
        fieldValues.forEach((key, value) -> {
            try {
                container.getNewContactModal().setValue(key, value);
            } catch (Exception e) {
                fail(e.getMessage());
            }
        });
    }

    @Then("^The user should be able to see the contact with field (.+) and value (.+) in contacts section$")
    public void new_contact_should_be_visible(String attribute, String value) throws InterruptedException {
        assertThat(container.getNestedContainerByAttribute(attribute, value)).isNotNull();
    }

    @Then("^The user should see the following error message on (.+) field$")
    public void verify_error_message_on_provided_field(String field, String errorMsg) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        assertThat(container.getNewContactModal().getErrorText(field)).isEqualTo(errorMsg);
    }

    @Then("^A contact with field (.+) and value (.+) should not be present in the contacts section$")
    public void verify_contact_is_not_present(String field, String value) {
        assertThat(container.getNestedContainerByAttribute(field, value)).isNull();
    }

    @When("^The user opts to (delete|edit) an existing Contact with field (.+) and value (.+)$")
    public void delete_or_edit_an_existing_contact(String action, String attribute, String value) {
        NestedDataContainer n = container.getNestedContainerByAttribute(attribute, value);
        assertThat(n).isNotNull();
        switch (action.toUpperCase()) {
            case "DELETE":
                n.deleteContainer();
                break;
            case "EDIT":
                n.updateContainerData();
                break;
        }

    }

    @And("^The user clears the field (.+) in the edit contact overlay$")
    public void clear_field_in_edit_contact_overlay(String field) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        container.getNewContactModal().clearValue(field);
    }

    @And("^The user updates the field (.+) with value (.+) on contacts overlay$")
    public void update_field_value(String field, String value) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        container.getNewContactModal().setValue(field, value);
    }

    @Then("^The user should see the following error message on field (.+) : (.+) in contacts modal$")
    public void verify_error_messages_in_contacts(String field, String errorMsg) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        assertThat(container.getNewContactModal().getErrorText(field)).isEqualTo(errorMsg);
    }
}
