package pageobjects._common.elements;

import com.codeborne.selenide.SelenideElement;
import pageobjects._common.modal.BaseModal;

import java.io.File;

public class Logo implements BaseElement {

    SelenideElement title;
    SelenideElement placeholder;
    SelenideElement input;

    public Logo(String logo) {
        SelenideElement element = new BaseModal().getFormElement(logo);
        element.scrollIntoView(false);
        title = element.$("div[class*='title']");
        placeholder = element.$("p");
        input = element.$("input");
    }

    public String getName() {
        return title.innerText();
    }

    public String getPlaceholder() {
        return placeholder.innerText();
    }

    public void setValue(File file) {
        input.uploadFile(file);
    }
}
