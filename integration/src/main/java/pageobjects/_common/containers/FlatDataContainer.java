package pageobjects._common.containers;

import com.codeborne.selenide.Condition;
import pageobjects._common.modal.BaseModal;

import static com.codeborne.selenide.Selenide.$;

public class FlatDataContainer extends DataContainer {

    public FlatDataContainer(String name) {
        super($("div[class*='" + name + "']"));
    }

    public void updateContainerData() {
        getEditButton().waitUntil(Condition.visible,2000);
        getEditButton().click();
        new BaseModal().shouldBeVisible();
    }
}