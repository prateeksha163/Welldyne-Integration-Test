package pageobjects._common.containers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pageobjects._common.elements.DataContainerAttribute;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;

public class DataContainer {

    private final SelenideElement dataContainer;
    private final SelenideElement title;
    private final SelenideElement createButton;
    private final SelenideElement editButton;
    private final SelenideElement deleteButton;
    private final ElementsCollection attributes;
    private final SelenideElement viewButton;
    private final SelenideElement infoButton;

    DataContainer(SelenideElement dataContainer) {
        this.dataContainer = dataContainer;
        dataContainer.scrollIntoView(false);
        dataContainer.waitUntil(visible, 30000);
        title = dataContainer.$(".ui-card-with-action-header__head__title");
        title.waitUntil(visible, 15000);
        editButton = dataContainer.$("button[data-action-name*=edit]");
        deleteButton = dataContainer.$("button[data-action-name*=delete]");
        attributes = dataContainer.$$(".ui-entity-attribute__item");
        createButton = dataContainer.$("button[data-action-name*=add]");
        viewButton = dataContainer.$("button[data-action-name*=viewHomogeneousFieldsGroup]");
        infoButton = dataContainer.$("button[data-action-name*=widgetInfoComplex]");
    }

    public void shouldBeVisible() {
        dataContainer.waitUntil(visible, 30000);
    }

    public Map<String, String> getAttributesWithValues() {
        return attributes.stream()
                .map(DataContainerAttribute::new)
                .collect(Collectors.toMap(DataContainerAttribute::getAttributeName,
                        DataContainerAttribute::getAttributeValue));
    }

    public Map<String, String> getAttributesWithValues(Set<String> names) {
        return attributes.stream()
                .map(DataContainerAttribute::new)
                .filter(d -> names.contains(d.getAttributeName()))
                .collect(Collectors.toMap(DataContainerAttribute::getAttributeName,
                        DataContainerAttribute::getAttributeValue));
    }

    public List<String> getAttributeListValues(String attribute){
        return attributes.stream()
                .map(DataContainerAttribute::new)
                .filter(d-> attribute.equals(d.getAttributeName())).findFirst()
                .map(DataContainerAttribute::getAttributeValues).orElse(null);
    }
    /* public Map<String, String> getAttributesWithMultipleValues() {
        return attributes.stream()
                .map(DataContainerAttribute::new)
                .collect(Collectors.toMap(DataContainerAttribute::getAttributeName,
                        DataContainerAttribute::getAttributeWithMultipleValues,(a1, a2) -> a1));
    }
*/
    public Map<String, String> getAttributesWithMultipleValues() {
        return attributes.stream()
                .map(DataContainerAttribute::new)
                .collect(Collectors.toMap(DataContainerAttribute::getAttributeName,
                        DataContainerAttribute::getAttributeWithMultipleValues,(a1, a2) -> a1));
    }

    public boolean containsAttribute(String name) {
        return attributes.filter(Condition.text(name)).size() > 0;
    }

    public String getAttributeValue(String attributeName) {
        DataContainerAttribute d = new DataContainerAttribute(attributes.find(Condition.text(attributeName)));
        return d.getAttributeValue();
    }

    public String getContainerHeading() {
        return title.innerText().trim();
    }

    protected SelenideElement getDataContainer() {
        return dataContainer;
    }

    protected SelenideElement getDeleteButton() {
        return deleteButton;
    }

    protected SelenideElement getEditButton() {
        editButton.shouldBe(visible);
        return editButton;
    }

    protected SelenideElement getViewButton() {
        return viewButton;
    }

    public SelenideElement getCreateButton() {
        return createButton;
    }

    protected SelenideElement getInfoButton() { return infoButton; }
}