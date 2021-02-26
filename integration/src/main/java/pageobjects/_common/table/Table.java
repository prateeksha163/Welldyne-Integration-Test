package pageobjects._common.table;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pageobjects._common.annotations.ElementMeta;
import pageobjects._common.annotations.Primary;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;

public class Table {

    private final SelenideElement self;
    private final Class<?> columns;

    public Table(Class<?> columns, String table) {
        this.columns = columns;
        self = $("*[data-element-name='" + table + "'][data-element-type='table-layout']");
        self.should(Condition.appear);
        self.scrollIntoView(false);
    }

    private ElementsCollection getTableRows() {
        return self.$$(".ui-table__body__row");
    }

    public TableRow getRow(String value) {
        String cellName = Arrays.stream(columns.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Primary.class))
                .findFirst()
                .map(field -> field.getAnnotation(ElementMeta.class).fieldName())
                .orElseThrow();
        return getRowWithColumnNameAndValue(cellName, value);
    }

    public TableRow getRowWithColumnNameAndValue(String cellName, String cellValue) {
        ElementsCollection tableRows = getTableRows();
        return tableRows.stream()
                .map(element -> new TableRow(element, columns))
                .filter(row -> row.getValueForCell(cellName).toLowerCase().equals(cellValue.toLowerCase()))
                .findFirst()
                .orElse(null);
    }

    public TableHead getTableHead() {
        SelenideElement tableHead = self.$(".ui-table__head");
        return new TableHead(tableHead, columns);
    }

    public List<String> getDataColumns() {
        return Arrays.stream(columns.getDeclaredFields())
                .map(field -> field.getAnnotation(ElementMeta.class).fieldName())
                .collect(Collectors.toList());
    }

    public List<Map<String, String>> getTableData() {
        ElementsCollection tableRows = getTableRows();
        return tableRows.stream()
                .map(element -> new TableRow(element, columns))
                .map(TableRow::getRowFieldsWithValues)
                .collect(Collectors.toList());
    }

    public List<String> getColumnValues(String column) {
        return getTableData()
                .stream()
                .map(map -> map.get(column))
                .collect(Collectors.toList()); }
}
