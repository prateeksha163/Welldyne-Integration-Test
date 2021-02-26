package pageobjects._common.containers;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pageobjects._common.modal.BaseModal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.codeborne.selenide.Selenide.$;

public class ParentDataContainer extends DataContainer {

    private final SelenideElement scrollNext = $(".ui-force-scroller__nav-button--next");
    private final ElementsCollection containers;

    public ParentDataContainer(String name) {
        super($("div[class*='" + name + "']"));
        containers = $("div[class*='" + name + "']").$$(".ui-force-scroller__wrapper__container__item");
        SelenideElement scrollPrev = $(".ui-force-scroller__nav-button--prev");
        while (scrollPrev.isDisplayed()) {
            scrollPrev.click();
        }
    }

    public void addNestedContainer() {
        getCreateButton().click();
        new BaseModal().shouldBeVisible();
    }

    private void scrollToContainer(int i) {
        int visibleContainers = 2; //Index starts at 0. So put a value 1 less than the visible count.
        if (i > visibleContainers && scrollNext.isDisplayed()) scrollNext.click();
    }

    public NestedDataContainer getNestedContainerByAttribute(String attribute, String value) {
        return IntStream
                .range(0, containers.size())
                .peek(this::scrollToContainer)
                .mapToObj(i -> new NestedDataContainer(containers.get(i)))
                .filter(e -> e.containsAttribute(attribute) && e.getAttributeValue(attribute).equals(value))
                .findFirst()
                .orElse(null);
    }

    public NestedDataContainer getNestedContainerByName(String name) {
        return IntStream
                .range(0, containers.size())
                .peek(this::scrollToContainer)
                .mapToObj(i -> new NestedDataContainer(containers.get(i)))
                .filter(e -> e.getContainerHeading().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<String> getNestedContainerNames() {
        return IntStream
                .range(0, containers.size())
                .peek(this::scrollToContainer)
                .mapToObj(i -> new NestedDataContainer(containers.get(i)))
                .map(DataContainer::getContainerHeading)
                .collect(Collectors.toList());
    }

    public int getNestedContainersCount() {
        return containers.size();
    }
}
