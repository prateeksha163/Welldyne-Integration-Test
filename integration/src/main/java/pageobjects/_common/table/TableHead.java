package pageobjects._common.table;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pageobjects._common.annotations.ElementMeta;

import java.lang.reflect.Field;
import java.util.List;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static pageobjects._common.annotations.ElementMetaProcessor.getFieldFromVisibleName;

public class TableHead {

    private final SelenideElement head;
    private final Class<?> columns;
    private SelenideElement loader= $("div[class*='table__body__row']");
    protected TableHead(SelenideElement head, Class<?> definition) {
        this.head = head;
        this.columns = definition;
    }

    private Column getColumnHead(String columnName) {
        Field field = getFieldFromVisibleName(columnName, columns);
        return new TableHead.Column(head, field);
    }

    public void sortColumn(String columnName) {
        head.scrollIntoView(false);
        getColumnHead(columnName).sortColumn();
    }

    public void applyFilter(String columnName, List<String> filters)  {
        head.scrollIntoView(false);
        getColumnHead(columnName).setFilter(filters);
    }

    public void clearFilter(String columnName) {
        getColumnHead(columnName).clearFilter();
    }

    private static class Column {

        private SelenideElement sort;
        private SelenideElement filter;
        private final SelenideElement loader = $(".ui-row-preloader");

        public Column(SelenideElement heading, Field field) {
            String locator = field.getAnnotation(ElementMeta.class).locator();
            SelenideElement cell = heading.$(byAttribute("data-cell-id", locator));
            if (cell.$("div[class*=sort-icon] svg").exists()) sort = cell.$("div[class*=sort-icon] svg");
            if (cell.$("div[class*=filter-icon] svg").exists()) filter = cell.$("div[class*=filter-icon] svg");
        }

        private void waitForLoadComplete() {
           if(loader.exists())
            loader.should(Condition.disappear);
        }

        public void setFilter(List<String> values) {
            filter.click();
            SelenideElement filterList = $("div[class*='filter-option-list']");
            values.forEach(v -> filterList.$(byAttribute("data-option", v)).scrollIntoView(true).click());
            waitForLoadComplete();
        }

        public void clearFilter() {
            filter.click();
            SelenideElement clear = $("div[class*='filter-option-list'] .ui-choice-list__clear-option");
            clear.click();
            waitForLoadComplete();
        }

        public void sortColumn() {
            sort.click();
            waitForLoadComplete(); }
    }
}
