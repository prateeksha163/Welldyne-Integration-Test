package pageobjects.clientconfiguration.overview;

import pageobjects._common.annotations.ElementMeta;
import pageobjects._common.elements.InputBox;
import pageobjects._common.elements.Logo;
import pageobjects._common.elements.SingleSelect;
import pageobjects._common.modal.BaseModal;

public class EditClientModal extends BaseModal {

    @ElementMeta(fieldName = "Company Logo", locator = "clientLogo")
    Logo logo;

    @ElementMeta(fieldName = "Client Name", locator = "clientName")
    InputBox clientName;

    @ElementMeta(fieldName = "Carrier Id", locator = "clientCode")
    InputBox clientCode;

    @ElementMeta(fieldName = "Effective Date", locator = "effectiveDate")
    InputBox effectiveDate;

    @ElementMeta(fieldName = "Term Date", locator = "termDate")
    InputBox termDate;

    @ElementMeta(fieldName = "External ID", locator = "externalId")
    InputBox externalId;

    @ElementMeta(fieldName = "Number of Lives", locator = "numberOfLives")
    InputBox numberOfLives;

    @ElementMeta(fieldName = "ACA Grandfathered", locator = "acaGrandfathered")
    SingleSelect acaGrandfathered;

    public EditClientModal() {
        super(EditClientModal.class);
    }
}
