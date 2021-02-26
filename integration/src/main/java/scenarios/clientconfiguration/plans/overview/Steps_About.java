package scenarios.clientconfiguration.plans.overview;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageobjects.ClientConfigurationPage;
import pageobjects._common.containers.DataContainer;
import pageobjects.clientconfiguration.plans.editplans.overview.PlansAbout;
import scenarios.Context;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;

public class Steps_About {
    private final ClientConfigurationPage configurationPage;
    Context context;

    public Steps_About(Context context) {
        configurationPage = new ClientConfigurationPage(context);
        this.context = context;
    }

    private PlansAbout get_About_container() {
        DataContainer container = configurationPage.getConfigurePlansPage().getDataContainer("About");
        return (PlansAbout) container;
    }

    @Then("The user opts to update the About section Plans overview Page")
    public void user_updates_about_section() {
        get_About_container().updateContainerData();
    }

    @Then("^The user should see the following form fields in About form$")
    public void user_validates_the_form_fields_in_about_section(List<String> formFields) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertThat(get_About_container().updateAboutModal().getFormFields()).isEqualTo(formFields);
    }

    @And("^The user updates the form fields with following values in Plans overview form$")
    public void user_updates_the_form_fields_in_plans_overview_page(Map<String, String> fieldValue) {
        fieldValue.forEach((k, v) -> {
            try {
                get_About_container().updateAboutModal().setValue(k, v);
            } catch (Exception e) {
                fail(e.getMessage());
            } }); }

    @And("^The user updates the form fields (.+) with value (.+) in Plans overview form$")
    public void user_updates_the_form_field_to_validate_error(String field, String value) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        get_About_container().updateAboutModal().setValue(field, value);
    }

    @Then("^The user should validate the following errors (.+) on field (.+) in Plans overview about form$")
    public void The_user_should_get_following_errors_in_Plans_overview_about_form(String errorMessage, String field) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String actualErrors = get_About_container().updateAboutModal().getErrorText(field);
        assertThat(actualErrors).isEqualTo(errorMessage);
    }

    @Then("The user should see the following values against given attributes on the About section in Plans overview")
    public void user_should_validate_the_attributes_and_values_in_About_section(Map<String, String> updatedValues) {
        Set<String> keySet = updatedValues.keySet();
        assertThat(get_About_container().getAttributesWithValues(keySet))
                .isEqualTo(updatedValues);
    }
}
