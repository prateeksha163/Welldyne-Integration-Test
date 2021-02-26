package pageobjects.clientconfiguration;

import pageobjects._common.containers.DataContainer;
import pageobjects.clientconfiguration.overview.About;
import pageobjects.clientconfiguration.overview.Contacts;

public class ClientOverviewPage {

    public DataContainer getDataContainer(String container) {
        switch (container.toUpperCase()) {
            case "ABOUT":
                return getAbout();
            case "CONTACTS":
                return getContacts();
        }
        return null;
    }

    private DataContainer getContacts() {
        return new Contacts("clientConfigurationContacts");
    }

    private DataContainer getAbout() {
        return new About("clientConfigurationAbout");
    }
}

