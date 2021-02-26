package scenarios.clientconfiguration.programs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.ClientConfigurationPage;
import pageobjects.clientconfiguration.programs.ClinicalProducts;
import pageobjects._common.containers.DataContainer;
import scenarios.Context;

import static org.fest.assertions.api.Assertions.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Steps_ClinicalProducts {

    private final ClientConfigurationPage configurationPage;

    public Steps_ClinicalProducts(Context context) {
        configurationPage = new ClientConfigurationPage(context);
    }

    private ClinicalProducts getClinicalProductsContainer() {
        DataContainer container = configurationPage.getProgramConfigurations().getDataContainer("Well Managed Programs");
        return (ClinicalProducts) container;
    }

    @When("^The user opts to update the Clinical Products Programs configuration in the Programs tab")
    public void user_opts_to_modify_well_managed_programs() throws InterruptedException {
        getClinicalProductsContainer().updateContainerData();
    }

    @And("^The user opts (in|out of) the following programs in the (.+) section$")
    public void user_updates_well_managed_programs(String option, String Section,List<String> programs) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        boolean opt_in = option.matches("in");
        for (String program : programs)
            getClinicalProductsContainer().getUpdateModal().setValue(Section, program, opt_in);
    }

    @Then("^The user should see the the following value against given programs on the Clinical Products Programs section$")
    public void validate_all_attributes_well_managed_programs(Map<String, String> expectedAttributesAndValues) {
        assertThat(getClinicalProductsContainer().getAttributesWithValues()).isEqualTo(expectedAttributesAndValues);
    }

    @Then("^The user should see the value against the following programs updated to (?:Yes|No) on the Clinical Products Programs section$")
    public void validate_attributes_well_managed_programs(Map<String, String> expectedAttributesAndValues) {
        Set<String> keys = expectedAttributesAndValues.keySet();
        assertThat(getClinicalProductsContainer().getAttributesWithValues(keys)).isEqualTo(expectedAttributesAndValues);
    }

    @Then("^The user should see the following checkbox fields in (.+) section$")
    public void validate_form_fields_in_Clinical_Products_Section(String Section,List <String> expectedFormFields) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertThat(getClinicalProductsContainer().getUpdateModal().getValues(Section)).isEqualTo(expectedFormFields);
    }

    @Then("^The user should see the following sections in Clinical Products form$")
    public void user_validate_form_sections_in_Clinical_Products_form(List <String> expectedFormFields) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertThat(getClinicalProductsContainer().getUpdateModal().getFormFields()).isEqualTo(expectedFormFields);
    }

    @Then("The user updates the (.+) field with following description in Clinical Products Form")
    public void user_updates_notes_field_in_clinical_product_form(String field,String Notes) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        getClinicalProductsContainer().getUpdateModal().setValue(field,Notes);
    }

    @Then("The (.+) section should not be visible in Clinical Programs Section")
    public void Notes_section_should_not_be_visible_in_Clinical_Programs_Section(String section) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertThat(getClinicalProductsContainer().getUpdateModal().getFormFields()).doesNotContain(section);
    }
}