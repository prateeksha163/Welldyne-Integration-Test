package pageobjects._common.elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class Popover {

    ElementsCollection title;
    ElementsCollection sectionTitle;
    ElementsCollection listItems;
    SelenideElement closeButton;

    public Popover(){
        SelenideElement element = $(".ui-popover");
        title = element.$$("h4");
        sectionTitle = element.$$("h5");
        listItems = element.$$("li[class*='content__items__item']");
        closeButton = element.$(".ui-description-wrapper__close");
    }

    public void closePopover(){
        closeButton.click();
    }

    public List<String> getTitle(){
        return title.texts();
    }

    public List<String> getSectionTitle(){
        return sectionTitle.texts();
    }

    public List<String> getListItems(){
        return listItems.texts();
    }
}
