package scenarios.listmanagement;

import io.cucumber.java.en.Given;
import pageobjects.ListManagementPage;
import scenarios.Context;

import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class Steps_ListManagement {

    private final Context context;
    private final ListManagementPage listManagement;
    public Steps_ListManagement(Context context){
        this.context = context;
        listManagement = new ListManagementPage(context);
    }

    @Given("The user is on List Management Page")
    public void navigate_to_client_configuration_page() throws InterruptedException {
        Thread.sleep(5000);
        new ListManagementPage(context).get();
    }

    @Given("The user should validate the following header tabs coming in List Management page")
    public void ger_header_tabs_in_list_management_page(List<String> headerTabs){
        assertThat(listManagement.get_header_tabs()).isEqualTo(headerTabs);
    }
}