package scenarios.clientconfiguration.plans;

import database_connection.database_connection_function;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageobjects.ClientConfigurationPage;
import pageobjects.clientconfiguration.plans.PlansTable;
import pageobjects.clientconfiguration.plans.editplans.EditPlan;
import scenarios.Context;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;

public class Steps_ConfigurationPlans {

    private final Context context;
    private final ClientConfigurationPage configurationPage;

    public Steps_ConfigurationPlans(Context context) {
        this.context = context;
        configurationPage = new ClientConfigurationPage(context);
    }

    private PlansTable get_Plans_container() {
        return configurationPage.getConfigurePlansPage().getPlans("plans");
    }

    @Then("^The user clicks on Add New Plan button$")
    public void User_clicks_on_add_new_plan_button() {
        get_Plans_container().addNewPlan();
    }

    @And("^The user updates the form fields with following values in Plans form$")
    public void user_updates_the_form_fields_in_plans(Map<String, String> fieldValue) {
        fieldValue.forEach((k, v) -> {
            try {
                get_Plans_container().getUpdatePlansModal().setValue(k, v);
            } catch (Exception e) {
                fail(e.getMessage());
            }
        }); }

    @And("^The user updates the (.+) with value (.+)  in form fields$")
    public void user_updates_the_form_fields_in_plans(String field, String fieldValue) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        get_Plans_container().getUpdatePlansModal().setValue(field, fieldValue);
    }

    @Then("^The user should validate the following table headers present in Plan table$")
    public void The_user_should_see_the_following_table_header(List<String> PlanTableHeaders) {
        assertThat(get_Plans_container().getDataColumns()).isEqualTo(PlanTableHeaders);
    }

    @Then("^The user should see the following form fields$")
    public void The_user_should_see_the_following_form_fields(List<String> formFields) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertThat(get_Plans_container().getUpdatePlansModal().getFormFields()).isEqualTo(formFields);
    }

    @Then("^The user should validate the following row values having (.+) and (.+) on searching Plan Table$")
    public void user_should_see_the_table_with_values(String field, String value,Map<String, String> keyValues) throws ClassNotFoundException, SQLException {
        String modifiedDate = null;
        String query;
        if(field.matches("Plan Code"))
         query = "select to_char(p.modified_date, 'mm-dd-yyyy hh24:mi:ss') AS modified_date FROM configdata.plan p where code='" + value + "'";
        else
            query = "select to_char(p.modified_date, 'mm-dd-yyyy hh24:mi:ss') AS modified_date FROM configdata.plan p where name='" + value + "'";
        database_connection_function c = new database_connection_function(context);
        ResultSet obj_rs = c.database_connection(query);
        while (obj_rs.next()) modifiedDate = obj_rs.getString("modified_date");
        c.closeConnectionString();
        Map<String, String> map1 = new HashMap<>();
        keyValues.forEach(map1::put);
        map1.put("Last Updated Date", modifiedDate);
        assertThat(get_Plans_container().getRowWithColumnNameAndValue(field,value).getRowFieldsWithValues()).isEqualTo(map1);
    }

    @Then("^The user should validate the following errors (.+) on field (.+) in Plans form$")
    public void The_user_should_get_following_errors_in_Plans(String errorMessage, String field) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String actualErrors = get_Plans_container().getUpdatePlansModal().getErrorText(field);
        assertThat(actualErrors).isEqualTo(errorMessage);
    }

    @Then("^The user clears the following fields$")
    public void user_clears_the_following_fields(List<String> fields) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        for (String s : fields)
            get_Plans_container().getUpdatePlansModal().clearValue(s);
    }

    @And("^The user enters (.+) value in Plans table search bar$")
    public void user_enter_text_in_plan_search_bar(String Plan_Name_code) throws InterruptedException {
        get_Plans_container().setValueInSearchBar(Plan_Name_code);
        Thread.sleep(2000);
    }

    @Then("^The user will get below error message on Plan Table$")
    public void error_Message_on_searchbar(String errorMessage) {
        assertThat(get_Plans_container().getErrorMessage()).isEqualTo(errorMessage);
    }

    @Then("^The user filters the (.+) in Plans Table with value (.+)$")
    public void User_clicks_on_filter_button(String attribute, String value) throws InterruptedException {
        get_Plans_container().getTableHead().applyFilter(attribute, Collections.singletonList(value));
        Thread.sleep(2000);
    }

    @Then("^The user sorts the (.+) column in Plans Table and validate the results$")
    public void User_clicks_on_sort_button(String attribute) throws InterruptedException {
        List<String> expectedSortResults = get_Plans_container().getColumnValues(attribute);
        Collections.sort(expectedSortResults);
        get_Plans_container().getTableHead().sortColumn(attribute);
        Thread.sleep(2000);
        List<String> actualSortResults = get_Plans_container().getColumnValues(attribute);
        assertThat(actualSortResults).isEqualTo(expectedSortResults);
    }

    @Then("^The row with (.+) (.+) should not be visible in Plans table$")
    public void Plan_should_not_be_visible(String fieldName, String value) throws InterruptedException {
        Thread.sleep(2000);
        List<String> columnValues = get_Plans_container().getColumnValues(fieldName);
        assertThat(columnValues).doesNotContain(value);
    }

    @Then("^On entering valid text in (.+) user should validate the matching Plans (.+) coming against client id (.+)$")
    public void user_validates_searchbar_values(String field, String MatchingString, String code) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException, ClassNotFoundException {
        List<String> PlanName_PlanCode =  new ArrayList<>();
        String query = "\tSELECT concat(p.code,'-', p.name) as PlanCode_PlanValue FROM configdata.plan p where  p.client_configuration_id = '" + code + "' and (concat(p.code,'-', p.name)) iLIKE '%" + MatchingString + "%'";
        database_connection_function c = new database_connection_function(context);
        ResultSet obj_rs = c.database_connection(query);
        while (obj_rs.next())
            PlanName_PlanCode.add(obj_rs.getString("PlanCode_PlanValue"));
        c.closeConnectionString();
        assertThat(get_Plans_container().getUpdatePlansModal().getDropdownValues(field,MatchingString)).containsAll(PlanName_PlanCode);
    }

    @Then("^The user validates the following message coming on searching the (.+) with invalid character (.+)$")
    public void user_gets_message_on_searching_the_searchbar_with_invalid_data(String field, String text,List<String> message) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertThat(get_Plans_container().getUpdatePlansModal().getDropdownValues(field,text)).isEqualTo(message);
    }

    @Then("The user should only see (.+) values against column (.+)")
    public void validate_filter_results(String value, String column) {
        assertThat(get_Plans_container().getColumnValues(column)).containsOnly(value);
    }

    @Then("^The user navigates to the (.+) tab in the plans page$")
    public void navigate_to_tab(String tab) throws InterruptedException {
        new EditPlan().navigateToTab(tab);
    }
}