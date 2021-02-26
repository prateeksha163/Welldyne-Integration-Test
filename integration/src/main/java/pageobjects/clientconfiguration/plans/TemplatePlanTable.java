package pageobjects.clientconfiguration.plans;

import com.codeborne.selenide.SelenideElement;
import pageobjects._common.annotations.ElementMeta;
import pageobjects._common.annotations.Primary;
import pageobjects._common.table.Table;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;

public class TemplatePlanTable extends Table {

    @ElementMeta(locator = "planName", fieldName = "Plan Name")
    SelenideElement planName;

    @Primary
    @ElementMeta(locator = "planCode", fieldName = "Plan Code")
    SelenideElement planCode;

    @ElementMeta(locator = "effectiveDate", fieldName = "Effective Date")
    SelenideElement effectiveDate;

    @ElementMeta(locator = "status", fieldName = "Status")
    SelenideElement status;

    @ElementMeta(locator = "termedDate", fieldName = "Term Date")
    SelenideElement termDate;

    @ElementMeta(locator = "lastUpdatedDate", fieldName = "Last Updated Date")
    SelenideElement lastUpdated;

    @ElementMeta(locator = "state", fieldName = "State")
    SelenideElement state;

    @ElementMeta(locator = "description", fieldName = "Notes")
    SelenideElement description;

    public TemplatePlanTable(String tableName) {
        super(TemplatePlanTable.class, tableName);
    }

    public void clickOnActionButtonOnTable(String action,String columnName, String columnValue) {
        SelenideElement tableRow = this.getRowWithColumnNameAndValue(columnName, columnValue).getElement();
        switch (action.toUpperCase()) {
            case "EDIT":
                tableRow.$("*[data-action-name='editPlan']").click();
                break;
            case "DELETE":
                tableRow.$("*[data-action-name='deletePlan']").click();
                break;
        }
    }
    public void addNewTemplate() {
        $(byAttribute("data-action-name", "addNewTemplatePlan")).click();
    }
}

