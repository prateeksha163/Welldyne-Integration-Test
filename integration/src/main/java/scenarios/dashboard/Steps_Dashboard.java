package scenarios.dashboard;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.DashboardPage;
import scenarios.Context;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;

public class Steps_Dashboard {

    DashboardPage dashboardPage;

    public Steps_Dashboard(Context context) {
        dashboardPage = new DashboardPage(context);
    }

    @Given("^The user is on Benefits Configurator Dashboard Page$")
    public void The_user_is_on_Benefits_Configurator_Dashboard_Page() {
        dashboardPage.get();
    }

    @When("^The user search the dashboard using (?:correct|incorrect) client code or name (.+)$")
    public void user_searches_client_code_or_name(String clientData) throws InterruptedException {
        dashboardPage.mainSearch(clientData);
    }

    @Then("^The user should see exactly (.+) client cards on the dashboard page$")
    public void client_entries_on_dashboard(int entries) {
        assertThat(dashboardPage.getClientCards().size()).isEqualTo(entries);
    }

    @Then("^The user should find client listing with names (.+) on the dashboard page$")
    public void client_displayed_on_dashboard_page(String clientNames) {
        dashboardPage.getClientCards()
                .shouldHave(CollectionCondition.textsInAnyOrder(clientNames.split(",")));
    }

    @Then("^An error title should be displayed as \"(.+)\"$")
    public void error_message_dashboard_search(String errorMsg) {
        assertThat(dashboardPage.getNoResultErrorTitle()).isEqualTo(errorMsg);
    }

    @And("^An error description should be displayed as \"(.+)\"$")
    public void error_description_dashboard_search(String errorText) {
        assertThat(dashboardPage.getNoResultErrorText()).isEqualTo(errorText);
    }

    @When("^The user opts to add a new client on the dashboard page$")
    public void opts_to_add_new_client() {
        dashboardPage.openAddNewClientModal();
    }

    @Then("^The user should see the following fields and buttons on the add new client form$")
    public void user_validates_elements_on_client_modal(List<String> requiredFields) {
        List<String> availableFields = dashboardPage.getAddNewClientModal().getAvailableFormFields();
        assertThat(requiredFields).containsAll(availableFields);
    }

    @And("^The user opts to click on the (.+) button on the add a new client form$")
    public void user_performs_action_on_client_modal(String actionName) {
        dashboardPage.getAddNewClientModal().clickOnActionButton(actionName);
    }

    @Then("^The Add New Client form should not be visible on the dashboard page$")
    public void client_modal_should_not_exist() {
        assertThat(dashboardPage.doesAddNewClientModalExists()).isEqualTo(false);
    }

    @Then("^The user should see the following error set on the add a new client form$")
    public void user_should_see_errors_on_add_new_client_form(List<String> expectedErrors) {
        ElementsCollection errors = dashboardPage.getAddNewClientModal().getAllErrors();
        errors.shouldHave(CollectionCondition.exactTexts(expectedErrors));
    }

    @Then("^The form (?:should open|opens) with first of next month selected as the effective date$")
    public void first_of_next_month_selected_by_default() {
        //Calculate first day of next month
        LocalDate nextMonth = LocalDate.now().plusMonths(1).withDayOfMonth(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String firstOfNextMonth = nextMonth.format(formatter);
        assertThat(dashboardPage.getAddNewClientModal().getEffectiveDate()).isEqualTo(firstOfNextMonth);
    }

    @And("The user updates the following fields with values$")
    public void update_form_field_with_value(Map<String, String> fieldValues) {
        fieldValues.forEach((key, value) -> dashboardPage.getAddNewClientModal().updateFieldValue(key, value));
    }

    @Then("The user should see the error for the incorrect image on the add a new client form")
    public void user_should_see_image_error(String expectedError) {
        String actualError = dashboardPage.getAddNewClientModal().getImageError();
        assertThat(actualError).isEqualTo(expectedError);
    }

    @Then("^The user clears the effective date field on the add a new client form$")
    public void clear_effective_date() {
        dashboardPage.getAddNewClientModal().clearEffectiveDate();
    }

    @Then("^User uploads an incorrect image file \"(.+)\" on the add a new client form$")
    public void upload_logo_on_add_new_client(String file) {
        dashboardPage.getAddNewClientModal().updateFieldValue("LOGO", file);
    }

    @Then("^The user should be able to see all the following filters$")
    public void user_should_see_all_filters(List<String> filterNames) {
        assertThat(dashboardPage.getDashboardFilter().getAllFilterHeaders().containsAll(filterNames));
    }

    @When("^The user selects the filter value (.+) from the filter with name (.+)$")
    public void user_selects_value_in_filter(String filterValue, String filterName) {
        dashboardPage.getDashboardFilter().applyFilter(filterValue, filterName);
    }

    @Then("^The user should find the client listing as per the stage filter criteria (.+)$")
    public void users_finds_the_client_listing_filtered_by_stage(String stage) {
        switch (stage.toUpperCase()) {
            case "IN PROGRESS":
                assertThat(dashboardPage.getClientCards().size())
                        .isEqualTo(dashboardPage.getClientCardsByStage("edit").size());
                break;
            case "SIGN OFF":
                assertThat(dashboardPage.getClientCards().size())
                        .isEqualTo(dashboardPage.getClientCardsByStage("sign-off").size());
                break;
            case "PUBLISH":
                assertThat(dashboardPage.getClientCards().size())
                        .isEqualTo(dashboardPage.getClientCardsByStage("publish").size());
                break;
            case "PUBLISHED":
                assertThat(dashboardPage.getClientCards().size())
                        .isEqualTo(dashboardPage.getClientCardsByStage("published").size());
                break;
        }
    }

    @And("^The user clears the filter with name (.+)$")
    public void theUserClearsTheFilterWithNameFilter_name(String filterName) {
        dashboardPage.getDashboardFilter().clearFilter(filterName);
    }

    @Then("^The clear button should be removed from the filter (.+)$")
    public void theClearButtonShouldBeRemovedFromTheFilterFilter_name(String filterName) {
        assertThat(dashboardPage.getDashboardFilter().getAttributeValue(filterName)).isNullOrEmpty();
    }

    @Then("^They should see the placeholder text in the search box as$")
    public void validate_search_box_placeholder_text(String expectedText) {
        assertThat(dashboardPage.getSearchBoxPlaceholderText()).isEqualTo(expectedText);
    }

}
