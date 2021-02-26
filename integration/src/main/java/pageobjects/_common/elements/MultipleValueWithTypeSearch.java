package pageobjects._common.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MultipleValueWithTypeSearch extends MultipleValueWithSelect {

    protected SelenideElement loader;

    public MultipleValueWithTypeSearch(String elementName) {
        super(elementName);
        loader = $(".ui-row-preloader");
    }

    public void setValue(String value) {
        inputBox.click();
        inputBox.setValue(value);
        waitForLoadComplete();
        $$("span[class*='highlight']").findBy(Condition.text(value)).click();
        addButton.click();
    }

    public List<String> getDropdownValues(String value) {
        inputBox.click();
        inputBox.setValue(value);
        waitForLoadComplete();
        return valueList.texts();
    }

    public List<String> getDropdownValues() {
        inputBox.click();
        waitForLoadComplete();
        return valueList.texts();
    }

    private void waitForLoadComplete() {
        loader.should(Condition.disappear);
    }
}