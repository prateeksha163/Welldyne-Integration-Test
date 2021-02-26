package pageobjects.listmanagement.steptherapy;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;

public class StepTherapyWithSearch extends StepTherapyTable {
    SelenideElement searchbar;
    SelenideElement addStepTherapy;
    SelenideElement errorMessage;
    SelenideElement loader;

    public StepTherapyWithSearch(String tableName) {
        super(tableName);
        searchbar = $("input[class*='__input--slim']");
        addStepTherapy = $(byAttribute("data-action-name", "addStepTherapy"));
        errorMessage = $("div[class*='ui-table__body__no-records-message']");
        loader = $(".ui-row-preloader");
    }

    public void setValueInSearchBar(String value) {
        searchbar.setValue(value);
        waitForLoadComplete();
    }

    public void addStepTherapy() {
        addStepTherapy.scrollIntoView(false);
        addStepTherapy.click();
    }

    private void waitForLoadComplete() {
        if (loader.should(Condition.appear).exists()) ;
        loader.should(Condition.disappear);
    }

    public StepTherapyModal getUpdateStepTherapy() {
        return new StepTherapyModal();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public String getSearchbarText() {
        return searchbar.getAttribute("placeholder");
    }
}
