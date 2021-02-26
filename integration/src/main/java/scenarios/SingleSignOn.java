package scenarios;

import pageobjects.DashboardPage;
import pageobjects.SingleSignOnPage;
import pageobjects.UnauthenticatedPage;
import com.codeborne.selenide.Selenide;
import static org.fest.assertions.api.Assertions.assertThat;

import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SingleSignOn {

    SingleSignOnPage singleSignOnPage;
    DashboardPage dashboardPage;
    UnauthenticatedPage unauthenticatedPage;

    public SingleSignOn(Context context) {
        singleSignOnPage = new SingleSignOnPage(context);
        dashboardPage = new DashboardPage(context);
        unauthenticatedPage = new UnauthenticatedPage();
    }

    @Given("^The user is on Microsoft MyApps Page$")
    public void user_is_on_Microsoft_Apps_Page(String url) {
        Selenide.open(url);
    }

    @When("^The user signs in using authorized SSO credentials$")
    public void user_logs_in_Microsoft_Account() {
        singleSignOnPage.loginAuthorizedUser();
        singleSignOnPage.selectAppIcon();
    }

    @Then("user will not be asked for credentials and will be navigated to Benefits Configurator Dashboard page")
    public void user_Will_Not_Be_Asked_For_Credentials_And_Will_Be_Navigated_To_Dashboard_Page(String url) {
        dashboardPage.waitForPageLoad();
        assertThat(WebDriverRunner.url()).isEqualTo(url);
    }

    @Given("An unauthorized user navigates to the Dashboard url")
    public void the_User_Navigates_To_The_Dashboard_Url(String url) {
        Selenide.open(url);
    }

    @Then("The user will be redirected to the Error Landing Page")
    public void the_User_Will_Be_Redirected_To_The_Error_Landing_Page(String url) {
        unauthenticatedPage.waitForPageLoad();
        assertThat(WebDriverRunner.url()).containsIgnoringCase(url);
    }

    @And("^The user should see the error title as \"(.+)\"$")
    public void the_User_Should_See_The_Error_Title_As(String errorTitle) {
        assertThat(unauthenticatedPage.getErrorTitle()).isEqualTo(errorTitle);
    }

    @And("^The error text as \"(.+)\"$")
    public void the_Error_Text_As(String errorText) {
        assertThat(unauthenticatedPage.getErrorText()).isEqualTo(errorText);
    }
}
