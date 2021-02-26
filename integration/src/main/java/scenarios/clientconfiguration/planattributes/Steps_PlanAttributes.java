package scenarios.clientconfiguration.planattributes;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.ClientConfigurationPage;
import pageobjects._common.containers.DataContainer;
import pageobjects._common.elements.Popover;
import pageobjects._common.modal.BaseModal;
import pageobjects.clientconfiguration.planattributes.PlanAttributes;
import scenarios.Context;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static org.fest.assertions.api.Assertions.assertThat;
public class Steps_PlanAttributes {

    private final ClientConfigurationPage configurationPage;

    public Steps_PlanAttributes(Context context) {
        configurationPage = new ClientConfigurationPage(context);
    }

    private PlanAttributes getPlanAttributesContainer() {
        DataContainer container = configurationPage.getConfigurePlanAttributesPage().getDataContainer("PA Rule Responsibility");
        return (PlanAttributes) container;
    }

    @Then("The user should see the the following value against (.+) attribute on the PA Responsibility section")
    public void validate_all_attributes_PA_Responsibility_programs(String attribute, List<String> values) {
        assertThat(getPlanAttributesContainer().getAttributeListValues(attribute)).isEqualTo(values);
    }

    @When("The user opts to update the PA Responsibility configuration in the PA Responsibility tab")
    public void user_opts_to_modify_well_managed_programs() {
        getPlanAttributesContainer().updateContainerData();
    }

    @And("^The user selects the following attributes in the PA Responsibility section with value (.+)$")
    public void user_updates_pa_attributes(String value, List<String> programs) throws InvocationTargetException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        for (String program : programs)
            getPlanAttributesContainer().updatePlanAttributes().setValue(program, value);
    }

    @Then("^The user should see the updated values against the following programs updated on the PA Responsibility section$")
    public void validate_attributes_well_managed_programs(Map<String, String> expectedAttributesAndValues) {
        Set<String> keys = expectedAttributesAndValues.keySet();
        assertThat(getPlanAttributesContainer().getAttributesWithValues(keys)).isEqualTo(expectedAttributesAndValues);
    }

    @Then("^The PA Responsibility form should not be visible on the dashboard page$")
    public void client_modal_should_not_exist() throws InterruptedException {
        BaseModal modal = new BaseModal();
        modal.shouldNotExist();
    }

    @When("The user clicks on the information button")
    public void click_on_info_button() {
        getPlanAttributesContainer().getInfo();
    }

    @Then("The user validates the (.+) of the popover")
    public void validate_title_of_popover(String action, List<String> values) {
        switch (action.toUpperCase()){
            case "TITLE":
                assertThat(new Popover().getTitle()).isEqualTo(values);
                break;
            case "SECTION TITLE":
                assertThat(new Popover().getSectionTitle()).isEqualTo(values);
                break;
            case "INFORMATION":
                assertThat(new Popover().getListItems()).isEqualTo(values);
                break;
        }
    }
}