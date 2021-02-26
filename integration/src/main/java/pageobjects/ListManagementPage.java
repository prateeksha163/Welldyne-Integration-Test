package pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import pageobjects.listmanagement.ConfigureStepTherapyPage;
import scenarios.Context;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import static org.fest.assertions.api.Assertions.assertThat;

public class ListManagementPage extends BasePage {
    final Context context;
    //Configuration Header
    final SelenideElement clientNameHead =$("div[class*='ui-generic-table-with-search__body']");
    final ElementsCollection headerTabs=$$("h5");

    //Configuration Tabs
    final SelenideElement stepTherapyTab = $("#listManagementStepTherapy");
    final SelenideElement formularyTab = $("#formulary");
    final SelenideElement drugsListTab = $("#drugsList");

    public ListManagementPage(Context context) {
        this.context = context;
    }

    @Override
    void load() {
        DashboardPage dashboardPage = new DashboardPage(context);
        dashboardPage.get();
        try {
            dashboardPage.listManagementHeader.click();
        } catch (Exception e) {
            System.out.println("Sleep Interrupted");
        }
        waitForPageLoad();
    }

    @Override
    void isLoaded() {
        String url = WebDriverRunner.getWebDriver().getCurrentUrl();
       assertThat(url).containsIgnoringCase("https://ppm.qa-welldyne.com/list-management");

    }

    public void clickOnTabHeader(String tabName) {

        switch (tabName.toUpperCase()) {

            case "STEP THERAPY":
                stepTherapyTab.click();
                break;
            case "FORMULARY":
                formularyTab.click();
                break;
            case "DRUG LIST":
                drugsListTab.click();
                break;
        }
    }

    public void waitForPageLoad() {
        clientNameHead.waitUntil(clickable, 40000);
    }

    public List<String> get_header_tabs()
    {
        return headerTabs.texts();
    }

    public ConfigureStepTherapyPage getConfigureStepTherapyPage() {
        return new ConfigureStepTherapyPage();
    }

}
