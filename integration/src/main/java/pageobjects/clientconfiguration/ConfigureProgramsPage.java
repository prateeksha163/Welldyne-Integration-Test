package pageobjects.clientconfiguration;
import pageobjects.clientconfiguration.programs.ClinicalProducts;

import pageobjects._common.containers.DataContainer;

public class ConfigureProgramsPage {

    public DataContainer getDataContainer(String container) {

        switch (container.toUpperCase()) {
            case "WELL MANAGED PROGRAMS":
                return getClinicalProducts();
        }
        return null;
    }

    private DataContainer getClinicalProducts() {
        return new ClinicalProducts("wellManagedPrograms");
    }

}