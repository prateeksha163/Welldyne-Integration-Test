package pageobjects._common.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TypeAheadSearch extends InputBox {

    SelenideElement loader = $(".ui-row-preloader");
    SelenideElement dropdown;


    public TypeAheadSearch(String elementName) {
        super(elementName);
    }

    public void setValue(String value) {
        clearField();
        input.click();
        input.setValue(value);
        waitForLoadComplete();
        $$("span[class*='highlight']").findBy(Condition.text(value)).click();
    }

    private void waitForLoadComplete() {
        loader.should(Condition.disappear);
    }

    public List<String> getDropdownValues(String value) {
        input.click();
        input.setValue(value);
        waitForLoadComplete();
        dropdown = $("div[class*='ui-popover--no']").$("div[class='ui-action-list']");
        return dropdown.$$(By.className("ui-action-list__option__label")).texts();

    }

    public List<String> getDropdownValues() {
        input.click();
        waitForLoadComplete();
        dropdown = $("div[class*='ui-popover--no']").$("div[class='ui-action-list']");
        return dropdown.$$(By.className("ui-action-list__option__label")).texts();
    }

    public void clearField() {
        if (input.getAttribute("value").length()>0)
            element.$("svg").click();
    }
}