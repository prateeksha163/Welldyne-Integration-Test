package scenarios.listmanagement.steptherapy;

import database_connection.database_connection_function;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.ListManagementPage;
import pageobjects._common.containers.DataContainer;
import pageobjects.listmanagement.steptherapy.StepTherapy;
import pageobjects.listmanagement.steptherapy.editStepTherapy.StepTherapySchedule;
import scenarios.Context;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;

public class Steps_ConfigureStepTherapy {
    protected final Context context;
    private final ListManagementPage listManagementPage;

    public Steps_ConfigureStepTherapy(Context context, ListManagementPage listManagementPage) {
        this.context = context;
        this.listManagementPage = listManagementPage;
    }

    private StepTherapySchedule get_Step_Therapy_Schedule_container() {
        DataContainer container = listManagementPage.getConfigureStepTherapyPage().getDataContainer("step Therapy schedule");
        return (StepTherapySchedule) container;
    }

    private StepTherapy get_ConfigureStepTherapyPage() {
        return listManagementPage.getConfigureStepTherapyPage().getStepTherapy("stepTherapyList");
    }

    @When("^The user searches the Step Therapy Table by entering value (.+) in search bar$")
    public void user_enters_value_in_search_bar(String value) {
        get_ConfigureStepTherapyPage().setValueInSearchBar(value);
    }

    @Then("^The user should validate the following row attributes and values for column (.+) and value (.+)$")
    public void get_step_Therapy_table_attributes_values(String column, String value, Map<String, String> keyValues) {
        assertThat(get_ConfigureStepTherapyPage().getRowWithColumnNameAndValue(column, value).getRowFieldsWithValues()).isEqualTo(keyValues);
    }

    @Then("^The user should validate all the Step Therapy names coming against column (.+) from database$")
    public void validate_step_Therapy_name(String columnName) throws SQLException, ClassNotFoundException {
        List<String> stepTherapyNames_DB = new ArrayList<>();
        String query = "SELECT name from configdata.customer_step_therapy ORDER BY name ASC";
        database_connection_function c = new database_connection_function(context);
        ResultSet obj_rs = c.database_connection(query);
        while (obj_rs.next())
            stepTherapyNames_DB.add(obj_rs.getString("name"));
        c.closeConnectionString();
        assertThat(get_ConfigureStepTherapyPage().getColumnValues(columnName)).containsAll(stepTherapyNames_DB);
    }

    @Then("^The user validates the rows coming on searching Step Therapy table on column (.+) with value (.+)$")
    public void user_validates_step_Therapy_name_on_searching_with_text(String columnName, String searchedText) throws SQLException, ClassNotFoundException {
        List<String> stepTherapyNames_DB = new ArrayList<>();
        String query = "SELECT name as name from configdata.customer_step_therapy where name like '%" + searchedText + "%'";
        database_connection_function c = new database_connection_function(context);
        ResultSet obj_rs = c.database_connection(query);
        while (obj_rs.next())
            stepTherapyNames_DB.add(obj_rs.getString("name"));
        c.closeConnectionString();
        assertThat(get_ConfigureStepTherapyPage().getColumnValues(columnName)).containsAll(stepTherapyNames_DB);
    }

    @Then("^The user should validate the below error message in Step therapy table$")
    public void validate_error_message_on_search_bar(String errorMessage) {
        assertThat(get_ConfigureStepTherapyPage().getErrorMessage()).isEqualTo(errorMessage);
    }

    @Then("^The user should validate following text coming in Step therapy page search bar$")
    public void user_validates_searchbar_text(String searchbarText) {
        assertThat(get_ConfigureStepTherapyPage().getSearchbarText()).isEqualTo(searchbarText);
    }

    @Then("^The (.+) section should be present in the Step Therapy Page$")
    public void verify_section_is_present_in_Step_Therapy(String section) {
        assertThat(get_Step_Therapy_Schedule_container().getContainerHeading()).isEqualTo(section);
    }

    @Then("^The user validates the title (.+) on Step Therapy Page$")
    public void user_validates_title_step_therapy_schedule(String title) {
        assertThat(get_Step_Therapy_Schedule_container().getStepTherapyScheduleTitle()).isEqualTo(title);
    }

    @Then("^The user should validate below table headers in Step Therapy table$")
    public void validate_table_headers_in_step_therapy(List<String> headers) {
        assertThat(get_ConfigureStepTherapyPage().getDataColumns()).isEqualTo(headers);
    }

    @When("^The user clicks on Add Step Therapy Button$")
    public void user_clicks_on_add_step_therapy() {
        get_ConfigureStepTherapyPage().addStepTherapy();
    }

    @Then("^The user should validate the following form fields$")
    public void user_validates_below_form_fields(List<String> formFields) throws  InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertThat(get_ConfigureStepTherapyPage().getUpdateStepTherapy().getFormFields()).isEqualTo(formFields);
    }

    @And("^The user updates the following form fields and values in Step Therapy form$")
    public void user_updates_form_fields_step_therapy(Map<String, String> fieldValue) {
        fieldValue.forEach((k, v) -> {
            try {
                get_ConfigureStepTherapyPage().getUpdateStepTherapy().setValue(k, v);
            } catch (Exception e) {
                fail(e.getMessage());
            }
        });
    }

    @Then("^The user updates the (.+) age field with value (.+) in Step Therapy form$")
    public void set_value_in_age_dropbox_in_step_therapy_form(String section, String value) {
        get_ConfigureStepTherapyPage().getUpdateStepTherapy().set_value_in_age_dropbox(value, section);
    }

    @Then("^The user updates the (.+) field with min value (.+) and max value (.+) in Step Therapy schedule form$")
    public void set_value_in_range_age_dropbox_in_step_therapy_form(String range, String min, String max)  {
        get_ConfigureStepTherapyPage().getUpdateStepTherapy().set_range_values_in_age_dropbox(min,max,range);
    }

    @And("^The user should validate the new row created with Step Therapy Name (.+) and having below attributes$")
    public void user_validates_attributes_with_values_in_step_therapy_page(String value, Map<String, String> attributeValues) throws InterruptedException {
        Thread.sleep(5000);
        assertThat(get_ConfigureStepTherapyPage().getRow(value).getRowFieldsWithValues()).isEqualTo(attributeValues);
    }

    @Then("^The user should validate the following errors (.+) on field (.+) in Step Therapy form$")
    public void user_validates_error_in_Step_Therapy_Form(String errorMessage, String field) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (field.contentEquals("Age"))
            assertThat(get_ConfigureStepTherapyPage().getUpdateStepTherapy().get_error_message_in_age_field()).isEqualTo(errorMessage);
        else
            assertThat(get_ConfigureStepTherapyPage().getUpdateStepTherapy().getErrorText(field)).isEqualTo(errorMessage);
    }

    @Then("^The user should validate the dropdown values coming in (.+) dropdown from database table (.+)$")
    public void user_validates_step_therapy_type_dropdown_values_from_database(String fieldName, String tableName) throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<String> stepTherapy_DB = new ArrayList<>();
        String query = "SELECT concat(code,' - ',name) as result from configdata." + tableName + "";
        database_connection_function c = new database_connection_function(context);
        ResultSet obj_rs = c.database_connection(query);
        while (obj_rs.next())
            stepTherapy_DB.add(obj_rs.getString("result"));
        c.closeConnectionString();
        assertThat(get_ConfigureStepTherapyPage().getUpdateStepTherapy().getDropdownValues(fieldName)).containsAll(stepTherapy_DB);
    }

    @Then("^The user should validate the static values coming in (.+) dropdown$")
    public void validate_static_dropDown_values_in_Step_Therapy_form(String field,List<String> dropDownValues) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertThat(get_ConfigureStepTherapyPage().getUpdateStepTherapy().getDropdownValues(field)).isEqualTo(dropDownValues);
    }

    @Then("^The user should validate the dropdown values coming in (.+) dropdown should not contain (.+) in Step Therapy Form on searching (.+)$")
    public void validate_dropdown_values_should_not_contain_already_added_value(String field, String value, String searchedText) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertThat(get_ConfigureStepTherapyPage().getUpdateStepTherapy().getDropdownValues(field, searchedText)).doesNotContain(value);
    }

    @Then("^The row with column name (.+) and value (.+) should not be visible in Step Therapy table$")
   public void deleted_step_therapy_should_not_be_visible(String field, String value) throws InterruptedException {
        Thread.sleep(2000);
        List<String> columnValues = get_ConfigureStepTherapyPage().getColumnValues(field);
        assertThat(columnValues).doesNotContain(value);
    }

    @Then ("The user validates the dropdown values coming in (.+) dropdown for values (.+) from database table (.+) in Step Therapy form")
    public void user_validates_dropdown_values_for_gpi(String field, String MatchingString, String table) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException, ClassNotFoundException {
        List<String>GPI_Database =  new ArrayList<>();
        String pCode,pName;
        String query = "SELECT p.code,p.name FROM configdata."+table+" p where concat(p.code,' - ',p.name) iLIKE '%" + MatchingString + "%'";
        database_connection_function c = new database_connection_function(context);
        ResultSet obj_rs = c.database_connection(query);
        while (obj_rs.next()) {
            pCode = obj_rs.getString("code");
            pName = obj_rs.getString("name");
            if (pCode.length() <= 13) {
                GPI_Database.add(pCode + "* - " + pName);
            } else
                GPI_Database.add(pCode + " - " + pName);
        }
        assertThat(GPI_Database).containsAll(get_ConfigureStepTherapyPage().getUpdateStepTherapy().getDropdownValues(field,MatchingString));
        c.closeConnectionString(); }
}