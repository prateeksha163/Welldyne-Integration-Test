package pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import pageobjects.clientconfiguration.*;
import scenarios.Context;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.fest.assertions.api.Assertions.assertThat;

public class ClientConfigurationPage extends BasePage {

    final Context context;
    //Configuration Header
    final SelenideElement clientNameHead =
            $(".ui-title.ui-client-header__content__left__title.ui-title--bold");
    final SelenideElement clientVersionHead =
            $(".ui-client-header__content__left__dropdown-title");
    //Configuration Tabs
    final SelenideElement overviewTab = $("#overview");
    final SelenideElement servicesTab = $("#services");
    final SelenideElement formularyTab = $("#formulary");
    final SelenideElement drugsListTab = $("#drugsList");
    final SelenideElement businessHierarchyTab = $("#businessHierarchy");
    final SelenideElement planAttributeTab = $("#planAttribute");
    final SelenideElement plansTab = $("#plans");
    final SelenideElement programsTab = $("#programs");
    final SelenideElement pricingTab = $("#pricing");
    final ElementsCollection tabHeaders = $$(".ui-tabs-list-with-status");
    private final SelenideElement loader = $(".ui-heart-beat-preloader");
    String clientCode;

    public ClientConfigurationPage(Context context, String clientCode) {
        this.context = context;
        this.clientCode = clientCode;
    }

    public ClientConfigurationPage(Context context) {
        this.context = context;
    }

    @Override
    void load() {
        DashboardPage dashboardPage = new DashboardPage(context);
        dashboardPage.get();
        try {
            dashboardPage.mainSearch(clientCode);
        } catch (Exception e) {
            System.out.println("Sleep Interrupted");
        }
        SelenideElement client = dashboardPage.getClientCards().get(0);
        client.$(".ui-text-overflow-handler__innerContainer").shouldBe(clickable).click();
        waitForPageLoad();
    }

    @Override
    void isLoaded() {
        String url = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertThat(url).containsIgnoringCase("https://ppm.qa-welldyne.com/client-configuration?clientConfigurationId=");
    }

    public void waitForPageLoad() {
        clientNameHead.waitUntil(clickable, 40000);
    }

    public void clickOnTabHeader(String tabName) {

        switch (tabName.toUpperCase()) {

            case "OVERVIEW":
                overviewTab.click();
                break;
            case "SERVICES":
                servicesTab.click();
                loader.waitUntil(Condition.disappear, 10000);
                break;
            case "FORMULARY":
                formularyTab.click();
                break;
            case "DRUG LIST":
                drugsListTab.click();
                break;
            case "BUSINESS HIERARCHY":
                businessHierarchyTab.click();
                break;
            case "PLAN ATTRIBUTE":
                planAttributeTab.click();
                break;
            case "PLANS":
                plansTab.scrollIntoView(true);
                plansTab.click();
                loader.waitUntil(Condition.disappear, 10000);
                break;
            case "PROGRAMS":
                programsTab.scrollIntoView(true);
                programsTab.click();
                loader.waitUntil(Condition.disappear, 10000);
                break;
            case "PRICING":
                pricingTab.click();
                break;
        }
    }

    public String getClientNameHead() {
        return clientNameHead.getText();
    }

    public ConfigureProgramsPage getProgramConfigurations() {
        return new ConfigureProgramsPage();
    }

    public ConfigureServicesPage getServiceConfiguration() {
        return new ConfigureServicesPage();
    }

    public PlanAttributesPage getConfigurePlanAttributesPage() {
        return new PlanAttributesPage();
    }

    public ClientOverviewPage getClientOverviewPage() {
        return new ClientOverviewPage();
    }

    public ConfigurePlansPage getConfigurePlansPage() {
        return new ConfigurePlansPage();
    }
}