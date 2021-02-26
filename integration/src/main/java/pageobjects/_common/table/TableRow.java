package pageobjects._common.table;

import com.codeborne.selenide.SelenideElement;
import pageobjects._common.annotations.ElementMeta;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selectors.byAttribute;
import static pageobjects._common.annotations.ElementMetaProcessor.getFieldFromVisibleName;

public class TableRow {

    private final SelenideElement self;
    private final Class<?> columns;

    protected TableRow(SelenideElement row, Class<?> definition) {
        self = row;
        this.columns = definition;
    }

    private DataCell getTableCell(String cellName) {
        Field field = getFieldFromVisibleName(cellName, columns);
        return new DataCell(self, field);
    }

    public SelenideElement getElement() {
        return self;
    }

    public String getValueForCell(String cellName) {
        return getTableCell(cellName).getValue();
    }

    public Map<String, String> getRowFieldsWithValues() {
        return Arrays.stream(columns.getDeclaredFields())
                .map(field -> new DataCell(self, field))
                .collect(Collectors.toMap(DataCell::getName, DataCell::getValue));
    }

    private static class DataCell {

        private final String name;
        private final String value;

        public DataCell(SelenideElement row, Field field) {
            name = field.getAnnotation(ElementMeta.class).fieldName();
            String locator = field.getAnnotation(ElementMeta.class).locator();
            value = row.$(byAttribute("data-cell-id", locator)).getText().replaceAll("\n", " ");
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }
    }
}
