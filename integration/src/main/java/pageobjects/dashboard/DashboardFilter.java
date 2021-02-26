package pageobjects.dashboard;

import org.openqa.selenium.By;
import pageobjects._common.elements.MultiSelect;
import pageobjects._common.elements.SingleSelect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class DashboardFilter {

    MultiSelect stage;
    SingleSelect effectiveDate;

    public DashboardFilter() {
        stage = new MultiSelect($(By.id("stage")));
        effectiveDate = new SingleSelect($(By.id("effectiveDate")));
    }

    public List<String> getAllFilterHeaders() {
        stage.shouldBeVisible();
        ArrayList<String> headers = new ArrayList<>();
        headers.add(stage.getName());
        headers.add(effectiveDate.getName());
        return headers;
    }

    public void applyFilter(String filterVal, String filterName) {
        String[] options = filterVal.split(",");
        ArrayList<String> filterVals = new ArrayList<>();
        Collections.addAll(filterVals, options);
        switch (filterName.toUpperCase()) {
            case "STAGE":
                stage.selectElement();
                stage.setValue(filterVals);
                break;

            case "EFFECTIVE DATE":
                effectiveDate.setValue(filterVal);
                break;
        }
    }

    public void clearFilter(String filterName) {
        switch (filterName.toUpperCase()) {
            case "STAGE":
                stage.clearFilter();
                break;
            case "EFFECTIVE DATE":
                effectiveDate.clearFilter();
                break;
        }
    }

    public String getAttributeValue(String filterName) {
        switch (filterName.toUpperCase()) {
            case "STAGE":
                return stage.getValue();
            case "EFFECTIVE DATE":
                return effectiveDate.getValue();
        }
        return null;
    }
}
