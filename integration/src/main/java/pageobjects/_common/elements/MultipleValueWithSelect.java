package pageobjects._common.elements;

import com.codeborne.selenide.ElementsCollection;

import java.util.List;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MultipleValueWithSelect extends MultipleValueBox {

    protected ElementsCollection valueList;

    public MultipleValueWithSelect(String elementName) {
        super(elementName);
        valueList = $$(".ui-action-list__option__label");
    }

    public void setValue(String value) {
        inputBox.click();
        $(byText(value)).click();
        addButton.click();
    }

    public void setValue(List<String> values) {
        inputBox.click();
        for (String value : values) $(byText(value)).click();
        addButton.click();
    }

    public List<String> getValues() {
        inputBox.click();
        return valueList.texts();
    }
}
