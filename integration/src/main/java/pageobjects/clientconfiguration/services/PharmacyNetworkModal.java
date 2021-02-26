package pageobjects.clientconfiguration.services;

import pageobjects._common.annotations.ElementMeta;
import pageobjects._common.elements.InputBox;
import pageobjects._common.elements.MultipleValueBox;
import pageobjects._common.elements.TextArea;
import pageobjects._common.modal.BaseModal;

public class PharmacyNetworkModal extends BaseModal {

    @ElementMeta(fieldName = "Network Name", locator = "name")
    InputBox name;

    @ElementMeta(fieldName = "Network Code", locator = "code")
    InputBox code;

    @ElementMeta(fieldName = "Description", locator = "description")
    TextArea description;

    @ElementMeta(fieldName = "NCPDP ID", locator = "ncpdp_id")
    MultipleValueBox ncpdp_id;

    public PharmacyNetworkModal() {
        super(PharmacyNetworkModal.class);
    }
}