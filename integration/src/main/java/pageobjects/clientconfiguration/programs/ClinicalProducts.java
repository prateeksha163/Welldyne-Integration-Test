package pageobjects.clientconfiguration.programs;

import pageobjects._common.containers.FlatDataContainer;

public class ClinicalProducts extends FlatDataContainer {

    public ClinicalProducts(String container) {
        super(container);
    }

    public ClinicalProductsModal getUpdateModal() {
        return new ClinicalProductsModal();
    }
}