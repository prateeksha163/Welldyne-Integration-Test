package pageobjects._common.elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class InputBoxWithSelect extends InputBox {

    SelenideElement dropdown;

    public InputBoxWithSelect(String elementName) {
        super(elementName);
    }

    public void setValue(String value) {
        input.click();
        input.setValue(value);
        input.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
    }

    public List<String> getDropdownValues() {
        input.click();
        dropdown = $("div[class*='ui-popover--no']").$("div[class='ui-action-list']");
        ElementsCollection text = dropdown.$$(By.className("ui-action-list__option__label"));
        return text.texts(); }
}
