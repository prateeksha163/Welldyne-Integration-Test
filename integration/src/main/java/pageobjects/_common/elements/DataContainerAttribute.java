package pageobjects._common.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DataContainerAttribute {

    private final SelenideElement name;
    private final SelenideElement attribute;

    private final SelenideElement value;
    private final ElementsCollection values;
    private SelenideElement plusButton = null;

    // private final ElementsCollection attributes;
    public DataContainerAttribute(SelenideElement attribute) {
        this.attribute = attribute;
        name = attribute.$(".ui-entity-attribute__item__title");
        if (attribute.$(".ui-entity-attribute__item__value").exists())
            value = attribute.$(".ui-entity-attribute__item__value");
        else
            value = attribute.$("span[class*='blank-symbol']");
        values = attribute.$$(".ui-entity-attribute__item__value");
        //Check Plus button
        if (name.parent().$("div[class*='click-action']").exists())
            plusButton = attribute.$("div[class*='click-action']");
        if ($(Selectors.byText(name.getText())).parent().parent().$$("path").size() > 1) {
            plusButton = value.$("svg");
        }
    }

    public String getAttributeName() {
        System.out.println("name.waitUntil(Condition.visible, 5000).getText().trim()" + name.waitUntil(Condition.visible, 5000).getText().trim());
        return name.waitUntil(Condition.visible, 5000).getText().trim();
    }

    public String getAttributeValue() {
        StringBuffer allValues = new StringBuffer();
        if (plusButton != null) {
            plusButton.click();
            ElementsCollection values = $$("div[class*='ui-text-overflow-view__item']");
            values.forEach(v -> allValues.append(v.getText()).append(" "));
            return allValues.toString().trim();
        } else {
            return value.waitUntil(Condition.visible, 4000).getText().trim();
        }
    }

    /*  public String getAttributeWithMultipleValues() {
          StringBuffer allValues = new StringBuffer();
          if (plusButton != null) {
              System.out.println("inside if");
              plusButton.click();
              ElementsCollection values = $$("div[class*='ui-text-overflow-view__item']");
              values.forEach(v -> allValues.append(v.getText()).append(" "));
              $("h2").click();
              System.out.println("allValues.toString().trim()"+allValues.toString().trim());
              return allValues.toString().trim();
          } else {
              System.out.println("inside else");
              $("h2").click();
              System.out.println("name.parent()"+name.parent().$$("div[class*='ui-entity-attribute__item__value']").texts().toString().replaceAll("]", "").replace("[", "").replaceAll("\n", " "));
              return name.parent().$$("div[class*='ui-entity-attribute__item__value']").texts().toString().replaceAll("]", "").replace("[", "").replaceAll("\n", " ");
          }
      }

     */
    public String getAttributeWithMultipleValues() {
        StringBuffer allValues = new StringBuffer();
        if (plusButton != null) {
            System.out.println("inside if");
            plusButton.click();
            ElementsCollection values = $$("div[class*='ui-text-overflow-view__item']");
            values.forEach(v -> allValues.append(v.getText()).append(" "));
            $("h2").click();
            System.out.println("allValues.toString().trim()" + allValues.toString().trim());
            return allValues.toString().trim();
        } else if (!(attribute.$("h5").exists()))
            return attribute.$$("div[class*='ui-entity-attribute__item__value']").texts().toString().replaceAll("]", "").replace("[", "").replaceAll("\n", " ");
  return null;
    }

    public List<String> getAttributeValues() {
        return values.texts();
    }
}