package scenarios.listmanagement.steptherapy;

import database_connection.database_connection_function;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageobjects.ListManagementPage;
import pageobjects._common.containers.DataContainer;
import pageobjects.listmanagement.steptherapy.editStepTherapy.StepTherapySchedule;
import scenarios.Context;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;

public class Steps_Edit_Step_Therapy {
    private final ListManagementPage listManagementPage;
    private final Context context;

    public Steps_Edit_Step_Therapy(ListManagementPage listManagementPage, Context context) {
        this.listManagementPage = listManagementPage;
        this.context = context;
    }

    private StepTherapySchedule get_Step_Therapy_Schedule_container() {
        DataContainer container = listManagementPage.getConfigureStepTherapyPage().getDataContainer("step Therapy schedule");
        return (StepTherapySchedule) container;
    }

    @Then("The user should validate the below attributes and values in Step Therapy Schedule section")
    public void user_validates_attributes_and_values_Step_Therapy_detail_page(DataTable attributesValues) {
        List<String> keyvalue = new ArrayList<>();
        get_Step_Therapy_Schedule_container().getAttributesWithMultipleValues().forEach(
                (key , value) -> {
                    keyvalue.add(key+", "+value);
                });
       //List<List<String>> data = Arrays.asList(new ArrayList<>(get_Step_Therapy_Schedule_container().getAttributesWithMultipleValues().keySet()) , new ArrayList<>(get_Step_Therapy_Schedule_container().getAttributesWithMultipleValues().values()));
      //  get_Step_Therapy_Schedule_container().getAttributesWithMultipleValues().entrySet().stream()
        //        .map(x -> x.getKey() + ": " + x.getValue()).
                                //.map(Object::toString));

        assertThat(keyvalue).isEqualTo(attributesValues.asList());
    }

    @Then("The user opts to update Step Therapy Schedule Modal")
    public void The_user_opts_to_update_Step_Therapy_Schedule_Modal() {
        get_Step_Therapy_Schedule_container().updateContainerData();
    }

    @And("^The user updates the following form fields and values in Step Therapy Schedule Form$")
    public void user_updates_form_fields_step_therapy_schedule(Map<String, String> fieldValue) {
        fieldValue.forEach((k, v) -> {
            try {
                get_Step_Therapy_Schedule_container().getUpdateStepTherapyScheduleModal().setValue(k, v);
            } catch (Exception e) {
                fail(e.getMessage());
            }
        });
    }

    @Then("^The user updates the (.+) age field with value (.+) in Step Therapy Schedule form$")
    public void set_value_in_age_dropbox_step_therapy_schedule_form(String section, String value) {
        get_Step_Therapy_Schedule_container().getUpdateStepTherapyScheduleModal().set_value_in_age_dropbox(value, section);
    }

    @Then("^The user should validate the static values coming in (.+) dropdown in Step Therapy Schedule form$")
    public void validate_static_dropDown_values_in_Step_Therapy_schedule_form(String field, List<String> dropDownValues) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertThat(get_Step_Therapy_Schedule_container().getUpdateStepTherapyScheduleModal().getDropdownValues(field)).isEqualTo(dropDownValues);
    }

    @Then("^The user should validate the following errors (.+) on field (.+) in Step Therapy schedule form$")
    public void user_validates_error_in_Step_Therapy_Schedule_Form(String errorMessage, String field) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (field.contentEquals("Age"))
            assertThat(get_Step_Therapy_Schedule_container().getUpdateStepTherapyScheduleModal().get_error_message_in_age_field()).isEqualTo(errorMessage);
        else
            assertThat(get_Step_Therapy_Schedule_container().getUpdateStepTherapyScheduleModal().getErrorText(field)).isEqualTo(errorMessage);
    }

    @Then("^The user validates the dropdown values coming in (.+) dropdown from database table (.+) in Step Therapy Schedule Modal$")
    public void the_user_validates_step_therapy_type_dropdown_values_from_database_in_Step_therapy_schedule(String fieldName, String tableName) throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<String> stepTherapy_DB = new ArrayList<>();
        String query = "SELECT concat(code,' - ',name) as result from configdata." + tableName + "";
        database_connection_function c = new database_connection_function(context);
        ResultSet obj_rs = c.database_connection(query);
        while (obj_rs.next())
            stepTherapy_DB.add(obj_rs.getString("result"));
        c.closeConnectionString();
        assertThat(get_Step_Therapy_Schedule_container().getUpdateStepTherapyScheduleModal().getDropdownValues(fieldName)).containsAll(stepTherapy_DB);
    }

    @Then("^The user should validate the dropdown values coming in (.+) dropdown should not contain (.+) in step therapy schedule Modal on searching (.+)$")
    public void validate_dropdown_values_should_not_contain_already_added_value_in_step_therapy_schedule(String field, String value,String searchedText) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        assertThat(get_Step_Therapy_Schedule_container().getUpdateStepTherapyScheduleModal().getDropdownValues(field, searchedText)).doesNotContain(value);
    }

    @Then("The user validates the dropdown values coming in (.+) dropdown for values (.+) from database table (.+) in Step Therapy Schedule Modal")
    public void the_user_validates_dropdown_values_for_gpi_in_step_therapy_table(String field, String MatchingString, String table) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, SQLException, ClassNotFoundException {
        List<String> GPI_Database = new ArrayList<>();
        String pCode, pName;
        String query = "SELECT p.code,p.name FROM configdata." + table + " p where concat(p.code,' - ',p.name) iLIKE '%" + MatchingString + "%'";
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
        assertThat(GPI_Database).containsAll(get_Step_Therapy_Schedule_container().getUpdateStepTherapyScheduleModal().getDropdownValues(field, MatchingString));
        c.closeConnectionString();
    }
}
