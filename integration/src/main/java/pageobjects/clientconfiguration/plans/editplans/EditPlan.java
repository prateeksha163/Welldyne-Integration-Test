package pageobjects.clientconfiguration.plans.editplans;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class EditPlan {

    private final SelenideElement loader = $(".ui-heart-beat-preloader");
    SelenideElement planListMode;
    SelenideElement heading;
    SelenideElement subHeading;
    SelenideElement sideContainer;
    ElementsCollection sideFields;
    ElementsCollection enabledFields;
    ElementsCollection disabledFields;

    public EditPlan() {
        planListMode = $(byAttribute("data-action-name", "togglePlanListMode"));
        heading = $("h1[class*='content__title']");
        subHeading = $("h1[class*='content__subtitle']");
        sideContainer = $("div[class*='ui-tabs-list-with-status--vertical']");
        sideFields = sideContainer.$$("h5");
        disabledFields = sideContainer.$$("div[class*='item--disabled']");
        enabledFields = sideContainer.$$("div[class*='item--item--selected']");
    }

    public boolean isFieldDisabled(String tabName) {
        return $(byText("" + tabName + "")).parent().getAttribute("class").contains("item--disabled");
    }

    public void navigateToTab(String tab) throws InterruptedException {
        if (!isFieldDisabled(tab))
            sideFields.find(Condition.text(tab)).click();
        loader.waitUntil(Condition.disappear, 5000);
        Thread.sleep(3000);
    }
}
