package pageobjects.dashboard;

import com.codeborne.selenide.SelenideElement;


public class ClientCard {

    private final SelenideElement clientName;

    public ClientCard(SelenideElement clientCard) {
        this.clientName = clientCard.$(".ui-text-overflow-handler__innerContainer");
    }

    public String getClientName() {
        return clientName.getText();
    }

}
