package scenarios.clientconfiguration.plans.overview;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.ClientConfigurationPage;
import pageobjects._common.containers.DataContainer;
import pageobjects.clientconfiguration.plans.editplans.overview.CoordinationOfBenefits;
import scenarios.Context;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;

public class Steps_Coordination_Of_Benefits {

    private final ClientConfigurationPage configurationPage;
    Context context;

    public Steps_Coordination_Of_Benefits(Context context) {
        configurationPage = new ClientConfigurationPage(context);
        this.context = context;
    }

    private CoordinationOfBenefits get_Coordination_Of_Benefits_container() {
        DataContainer container = configurationPage.getConfigurePlansPage().getDataContainer("Co-ordination of benefits");
        return (CoordinationOfBenefits) container;
    }

    @When("The user opts to update coordination of benefits section in Plans overview Page")
    public void user_updates_coordination_of_benefits_section() {
        get_Coordination_Of_Benefits_container().updateContainerData();
    }

    @Then("The user validates the following titles coming in Co-ordination of Benefits Form")
    public void user_validates_titles_in_Coordination_Of_Benefits(List<String> formFields) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertThat(get_Coordination_Of_Benefits_container().updateCoordinationOfBenefits().getFormFields()).isEqualTo(formFields);
    }

    @Then("The user validates (.+) Text box (appeared|disappeared) in Co-ordination of benefits form")
    public void user_validates_Custom_Cost_Share_field_Coordination_Of_Benefits(String field, String visibility) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if(visibility.matches("appeared"))
        assertThat(get_Coordination_Of_Benefits_container().updateCoordinationOfBenefits().getFormFields()).contains(field);
       else
            assertThat(get_Coordination_Of_Benefits_container().updateCoordinationOfBenefits().getFormFields()).doesNotContain(field);
    }

    @Then("The user should validate the following fields in (.+) title")
    public void user_should_validate_the_fields_in_respective_title(String field, List<String> formFields) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertThat(get_Coordination_Of_Benefits_container().updateCoordinationOfBenefits().getValues(field)).isEqualTo(formFields);
    }

    @Then("The user updates the following fields in the Co-ordination of benefits Form with below values")
    public void The_user_selects_following_values_in_the_coordination_of_benefits_Form(Map<String, String> fields) {
        fields.forEach((k, v) -> {
            try {
                get_Coordination_Of_Benefits_container().updateCoordinationOfBenefits().setValue(k, v);
            } catch (Exception e) {
                fail(e.getMessage());
            }
        });
    }

    @Then("^The user opts (in|out of) following field (.+) in the coordination of benefits Form$")
    public void The_user_opts_following_fields_in_the_coordination_of_benefits_Form(String option, String field, List<String> values) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        boolean opt_in = option.matches("in");
        for (String value : values) {
            get_Coordination_Of_Benefits_container().updateCoordinationOfBenefits().setValue(field, value, opt_in);
        }
    }

    @Then("The user should validate that attribute values in Coordination of benefits are updated as below")
    public void The_user_validates_attributes_And_their_values(Map<String, String> attributesValues) {
        assertThat(get_Coordination_Of_Benefits_container().getAttributesWithValues()).isEqualTo(attributesValues);
    }

    @Then("The user should gets the error message as (.+) on field (.+)")
    public void user_gets_error_message_on_field(String errorMessage, String field) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertThat(get_Coordination_Of_Benefits_container().updateCoordinationOfBenefits().getErrorText(field)).isEqualTo(errorMessage);
    }

    @Then("The user updates the field (.+) in Co-ordination of benefits Form with (.+)")
    public void user_updates_form_fields(String field, String value) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        get_Coordination_Of_Benefits_container().updateCoordinationOfBenefits().setValue(field, value);
    }
}