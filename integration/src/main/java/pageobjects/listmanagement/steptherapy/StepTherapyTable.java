package pageobjects.listmanagement.steptherapy;

import com.codeborne.selenide.SelenideElement;
import pageobjects._common.annotations.ElementMeta;
import pageobjects._common.annotations.Primary;
import pageobjects._common.table.Table;

public class StepTherapyTable extends Table {
    @Primary
    @ElementMeta(locator = "stepTherapyName", fieldName = "Step Therapy Name")
    SelenideElement stepTherapyName;

    @ElementMeta(locator = "stepTherapyType", fieldName = "Step Therapy Type")
    SelenideElement stepTherapyType;

    @ElementMeta(locator = "criteria", fieldName = "Criteria")
    SelenideElement criteria;

    public StepTherapyTable(String tableName) {
        super(StepTherapyTable.class, tableName);
    }

    public void clickOnActionButtonOnTable(String action,String columnName, String columnValue) {
        SelenideElement tableRow = this.getRowWithColumnNameAndValue(columnName, columnValue).getElement();
        switch (action.toUpperCase()) {
            case "VIEW":
                tableRow.$("*[data-action-name='viewStepTherapy']").click();
                break;
            case "DELETE":
                tableRow.$("*[data-action-name='deleteStepTherapy']").click();
                break;
        }
    }
}

