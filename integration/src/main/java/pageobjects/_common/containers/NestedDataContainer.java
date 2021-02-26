package pageobjects._common.containers;

import com.codeborne.selenide.SelenideElement;
import pageobjects._common.modal.BaseModal;
import pageobjects._common.modal.DeleteModal;

public class NestedDataContainer extends DataContainer {

    public NestedDataContainer(SelenideElement dataContainer) {
        super(dataContainer);
    }

    public void updateContainerData() {
        getEditButton().click();
        new BaseModal().shouldBeVisible();
    }

    public void viewContainerData(){
        getViewButton().click();
        new BaseModal().shouldBeVisible();
    }

    public void deleteContainer() {
        getDeleteButton().click();
        new DeleteModal().shouldBeVisible();
    }
}