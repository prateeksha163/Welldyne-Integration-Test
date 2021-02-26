package scenarios.clientconfiguration;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.ClientConfigurationPage;
import pageobjects._common.containers.DataContainer;
import pageobjects._common.modal.BaseModal;
import pageobjects.clientconfiguration.overview.Contacts;
import pageobjects.clientconfiguration.overview.ContactsModal;
import scenarios.Context;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class Steps_ClientConfiguration {

    private final Context context;
    private final ClientConfigurationPage clientConfiguration;

    public Steps_ClientConfiguration(Context context){
        this.context = context;
        clientConfiguration = new ClientConfigurationPage(context);
    }

    @Given("The user is on the client configuration page for client with code (.+)")
    public void navigate_to_client_configuration_page(String clientCode) throws InterruptedException {
        Thread.sleep(5000);
        new ClientConfigurationPage(context, clientCode).get();
    }

    @Then("The page should show the name (.+) as client name on header form")
    public void user_should_find_client_name_on_header(String clientName) {
        assertThat(clientConfiguration.getClientNameHead()).isEqualTo(clientName);
    }

    @When("The user navigates to the (.+) tab in the configuration")
    public void user_navigates_to_configuration_tab(String configurationTab){
        clientConfiguration.clickOnTabHeader(configurationTab);
    }

    @And("^The user opts to add a new Contact in the (.+) section$")
    public void user_opts_to_create_a_new_contact(String section) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        DataContainer container = clientConfiguration.getClientOverviewPage().getDataContainer(section);
        Contacts contacts = (Contacts) container;
        ContactsModal modal = contacts.addNewContact();
        List<String> list = modal.getFormFields();
        System.out.println(list.get(0));
    }

}
