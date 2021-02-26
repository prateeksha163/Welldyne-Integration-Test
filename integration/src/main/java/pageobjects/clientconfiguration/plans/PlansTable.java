package pageobjects.clientconfiguration.plans;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;

public class PlansTable extends TemplatePlanTable {

    SelenideElement searchbar;
    SelenideElement addNewPlan;
    SelenideElement errorMessage;
    SelenideElement loader;

    public PlansTable(String tableName) {
        super(tableName);
        searchbar = $("input[class*='__input--slim']");
        addNewPlan = $(byAttribute("data-action-name", "addNewPlan"));
        errorMessage = $("div[class*='ui-table__body__no-records-message']");
        loader = $(".ui-row-preloader");
    }

    private void waitForLoadComplete() {
        if(loader.isDisplayed())
               loader.should(Condition.disappear);
    }

    public void setValueInSearchBar(String value) {
        searchbar.setValue(value);
        waitForLoadComplete();
    }

    public void addNewPlan() {
        addNewPlan.scrollIntoView(false);
        addNewPlan.click();
    }

    public PlansModal getUpdatePlansModal() {
        return new PlansModal();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
