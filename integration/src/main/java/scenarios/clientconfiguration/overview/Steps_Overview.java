package scenarios.clientconfiguration.overview;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.ClientConfigurationPage;
import pageobjects._common.containers.DataContainer;
import pageobjects.clientconfiguration.overview.About;
import pageobjects.clientconfiguration.overview.Contacts;
import scenarios.Context;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;

public class Steps_Overview {

    private final ClientConfigurationPage configurationPage;
    private DataContainer container;

    public Steps_Overview(Context context) {
        configurationPage = new ClientConfigurationPage(context);
    }

    private About getAboutContainer() {
        container = configurationPage.getClientOverviewPage().getDataContainer("About");
        return (About) container;
    }

    private Contacts getContactsContainer() {
        container = configurationPage.getClientOverviewPage().getDataContainer("Contacts");
        return (Contacts) container;
    }


    @Then("The user should see the (.+) sub-section on the Overview Page")
    public void theUserShouldSeeTheAboutSubSectionOnTheOverviewPage(String section) {
        switch (section.toUpperCase()) {
            case "ABOUT":
                assertThat(getAboutContainer().getContainerHeading()).containsIgnoringCase(section);
                break;
            case "CONTACTS":
                assertThat(getContactsContainer().getContainerHeading()).containsIgnoringCase(section);
                break;
        }

    }

    @Then("The user should see the following fields with values in the About section")
    public void theUserShouldSeeTheFollowingFieldsInTheAboutSection(Map<String, String> expectedAttributesAndValues) {
        assertThat(getAboutContainer().getAttributesWithValues()).isEqualTo(expectedAttributesAndValues);
    }

    @When("^The user opts to update the client information in the Edit Client overlay$")
    public void theUserOptsToUpdateTheClientInformationInTheAboutSection() {
        getAboutContainer().updateContainerData();
    }

    /*@And("^The user updates the following fields with values on (.+) section$")
    public void theUserUpdatesTheFollowingFieldsWithValuesOnModalOverlay(String section, Map<String, String> expectedFieldsAndValues) {
        expectedFieldsAndValues.forEach((key, value) -> {
            try {
                getAboutContainer().getEditClientModal().setValue(key, value);
            } catch (Exception e) {
                fail(e.getMessage());
            }
        });

    }*/

    /*@Then("The user should see the following fields in the About section")
    public void theUserShouldSeeTheFollowingFieldsInTheAboutSection(List<String> expectedFields) {
        assertThat(getAboutContainer().getAttributesWithValues()).isEqualTo((Map<String, String>) expectedFields);
    }*/

    @And("^The user updates the field (.+) with value (.+) in the Edit Client overlay$")
    public void theUserUpdatesTheFieldNumberOfLivesWithValueInTheEditClientOverlay(String field, String value) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        getAboutContainer().getEditClientModal().setValue(field, value);
    }

    @Then("^The user should find the field (.+) updated with value (.+) in about section$")
    public void theUserShouldFindTheFieldNumberOfLivesUpdatedWithValueInAboutSection(String field, String value) {
        assertThat(getAboutContainer().getAttributeValue(field)).isEqualTo(value);
    }

    @Then("^The user should see the following fields on the edit client overlay$")
    public void theUserShouldSeeTheFollowingFieldsOnTheEditClientOverlay(List<String> expectedFields) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertThat(getAboutContainer().getEditClientModal().getFormFields()).isEqualTo(expectedFields);
    }

    @And("^The user clears the field (.+) in the edit client overlay$")
    public void theUserClearsTheFieldInTheEditClientOverlay(String field) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        getAboutContainer().getEditClientModal().clearValue(field);
    }

    @Then("^The user should see the following error below the (.+) field$")
    public void theUserShouldSeeTheFollowingErrorOnTheEditClientForm(String field, String error) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertThat(getAboutContainer().getEditClientModal().getErrorText(field)).isEqualTo(error);
    }
}
