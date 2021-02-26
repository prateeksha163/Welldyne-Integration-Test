package scenarios.clientconfiguration.plans.drugsettings;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.ClientConfigurationPage;
import pageobjects.clientconfiguration.plans.editplans.drugsettings.Compounds;
import scenarios.Context;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.testng.Assert.fail;

public class Steps_Compounds {

    final private Compounds container;

    public Steps_Compounds(Context context) {
        ClientConfigurationPage configurationPage = new ClientConfigurationPage(context);
        container = (Compounds) configurationPage.getConfigurePlansPage().getDataContainer("Compounds");
    }

    @When("^The user opts to edit a compound$")
    public void edit_compound(){
        container.updateContainerData();
    }

    @When("^The user enters the following values in Compounds Modal$")
    public void enter_form_values(Map<String, String> fieldValues){
        fieldValues.forEach((k, v) -> {
            try {
                container.getCompoundsModal().setValue(k, v);
            } catch (Exception e) {
                fail(e.getMessage());
            }
        });
    }


    @Then("^The user should se the following values in the compounds section$")
    public void verify_values_in_section(Map<String, String> fieldValues) throws InterruptedException {
        Thread.sleep(3000);
        assertThat(container.getAttributesWithValues()).isEqualTo(fieldValues);
    }

    @When("^The user clears the form field (.+)$")
    public void clear_form_field(String field) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        container.getCompoundsModal().clearValue(field);
    }

    @When("^The user clears the following form fields$")
    public void clear_form_fields(List<String> fields) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
         container.getCompoundsModal().clearValue(fields);
    }

    @Then("^The user should see the following error message on field (.+) in compounds modal$")
    public void verify_error_message_in_compounds(String field, String errorMsg) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertThat(container.getCompoundsModal().getErrorText(field)).isEqualTo(errorMsg);
    }

    @Then("^The user validates the following error messages on the Compounds Modal form$")
    public void verify_all_error_messages(Map<String,String> errorMsgs) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<String> fields = new ArrayList<>();
        errorMsgs.forEach((k, v) -> fields.add(k));
        assertThat(container.getCompoundsModal().getErrorText(fields)).isEqualTo(errorMsgs);
    }
}
