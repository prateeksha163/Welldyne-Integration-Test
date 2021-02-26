package scenarios.clientconfiguration.plans.overview;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageobjects.ClientConfigurationPage;
import pageobjects._common.containers.DataContainer;
import pageobjects.clientconfiguration.plans.editplans.overview.GenericIncentiveProgram;
import scenarios.Context;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;

public class Steps_Generic_Incentive_Programs {

    private final ClientConfigurationPage configurationPage;
    Context context;

    public Steps_Generic_Incentive_Programs(Context context) {
        configurationPage = new ClientConfigurationPage(context);
        this.context = context;
    }

    private GenericIncentiveProgram get_GenericIncentiveProgram_container() {
        DataContainer container = configurationPage.getConfigurePlansPage().getDataContainer("Generic Incentive Program");
        return (GenericIncentiveProgram) container;
    }

    @Then("^The user opts to update the Generic Incentive Program section Plans overview Page$")
    public void update_Generic_Incentive_Program_Section() {
        get_GenericIncentiveProgram_container().updateContainerData();
    }

    @Then("^The user should validate the below title and Notes coming in Generic Incentive Program Form$")
    public void User_gets_below_Title_and_Notes(List<String> Heading_Description) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertThat(get_GenericIncentiveProgram_container().updateGenericIncentiveProgram().getFormFields()).isEqualTo(Heading_Description);
    }

    @Then("^The user should validate the following (.+) fields in Generic Incentive Program Form$")
    public void user_gets_following_form_fields(String field,List<String> formFields) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertThat(get_GenericIncentiveProgram_container().updateGenericIncentiveProgram().getValues(field)).isEqualTo(formFields);
    }

    @And("^The user opts (in|out of) the following (.+) in the Generic Incentive Program Form$")
    public void The_user_opts_the_following_Fields(String opt, String field, List<String> PSC_DAW_Codes ) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        boolean opt_in = opt.matches("in");
        for(String s:PSC_DAW_Codes)
            get_GenericIncentiveProgram_container().updateGenericIncentiveProgram().setValue(field, s, opt_in);
    }

    @Then("^The user updates the fields with following values in Generic Incentive Program Form$")
    public void user_updates_form_field(Map<String,String> fieldValue)  {
        fieldValue.forEach((k, v) -> {
            try {
                get_GenericIncentiveProgram_container().updateGenericIncentiveProgram().setValue(k, v);
            } catch (Exception e) {
                fail(e.getMessage());
            }
        });
    }

    @Then("^The user validates attributes with values coming in Generic incentive program Section$")
    public void user_validates_attributes_with_values(Map<String,String> attributesValues) {
        assertThat(get_GenericIncentiveProgram_container().getAttributesWithValues()).isEqualTo(attributesValues);
    }

    @And("^User clears the (.+) text Box$")
    public void user_clears_Penalty_notes_field(String field) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        get_GenericIncentiveProgram_container().updateGenericIncentiveProgram().clearValue(field);
    }

    @Then("^The user should get the following error message (.+) on (.+) field$")
    public void get_error_on_mandatory_fields(String errorMessage,String field) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertThat(get_GenericIncentiveProgram_container().updateGenericIncentiveProgram().getErrorText(field)).isEqualTo(errorMessage);
    }

    @Then("^The user validates the following default text coming in (.+) field$")
    public void get_default_text_in_Penalty_notes_field(String field,String PenaltyCodeText) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertThat(get_GenericIncentiveProgram_container().updateGenericIncentiveProgram().getValue(field)).isEqualTo(PenaltyCodeText);
    }
}
