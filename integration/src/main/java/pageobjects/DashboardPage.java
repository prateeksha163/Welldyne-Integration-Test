package pageobjects;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$$;
import static org.fest.assertions.api.Assertions.*;
import com.codeborne.selenide.Condition;

import pageobjects.dashboard.AddNewClientModal;
import pageobjects.dashboard.DashboardFilter;
import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import scenarios.Context;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage extends BasePage {

    private final Context context;

    //Dashboard Add New Client
    SelenideElement addNewClientButton = $(".ui-button__content.ui-button__content--medium");
    SelenideElement addNewClientModal = $(".ui-overlay__content-pane");

    //Dashboard Search Items
    SelenideElement searchBar = $(By.xpath("//input[@placeholder='Search by Client name or Client code']"));
    SelenideElement errorTitle = $(".ui-dashboard-clients-list__cards__no-records__title");
    SelenideElement errorDescription = $(".ui-dashboard-clients-list__cards__no-records__subtitle");

    //Dashboard Client Cards
    SelenideElement clientSummaryWidget = $(byAttribute("data-widget-id", "clientsSummaryList"));
    ElementsCollection clientCards = $$(byAttribute("data-widget-item", "clientCard"));

    // HeaderTabs
    SelenideElement listManagementHeader = $(Selectors.byText("List Management"));

    public DashboardPage (Context context) {
        this.context = context;
    }

    @Override
    void load() {
        SingleSignOnPage singleSignOnPage = new SingleSignOnPage(context);
        singleSignOnPage.get();
        singleSignOnPage.selectAppIcon();
        waitForPageLoad();
    }

    @Override
    void isLoaded() {
        String url = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertThat(url).containsIgnoringCase("https://ppm.qa-welldyne.com/dashboard");
    }

    public String getSearchBoxPlaceholderText() {
        return searchBar.getAttribute("placeholder");
    }

    public void waitForPageLoad() {
        searchBar.waitUntil(clickable, 40000);
    }

    public void mainSearch(String searchInfo) throws InterruptedException {
        searchBar.waitUntil(clickable,10000).setValue(searchInfo);
        Thread.sleep(2500);
    }

    public String getNoResultErrorTitle(){
        return errorTitle.getText();
    }

    public String getNoResultErrorText(){
        return errorDescription.getText();
    }

    public ElementsCollection getClientCards() {
        return clientCards;
    }

    public void openAddNewClientModal() {
        addNewClientButton.click();
        addNewClientModal.shouldBe(clickable);
    }

    public AddNewClientModal getAddNewClientModal() {
        return new AddNewClientModal();
    }

    public boolean doesAddNewClientModalExists() {
        try {
            addNewClientModal.shouldNot(Condition.exist);
            return false;
        }
        catch (Exception e) { return true; }
    }

    public ElementsCollection getClientCardsByStage(String stage) {
        return clientCards.filter(Condition.attribute("data-client-stage-value",stage));
    }

    public DashboardFilter getDashboardFilter() {
        return new DashboardFilter();
    }
}
