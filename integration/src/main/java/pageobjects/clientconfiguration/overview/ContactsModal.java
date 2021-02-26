package pageobjects.clientconfiguration.overview;

import pageobjects._common.annotations.ElementMeta;
import pageobjects._common.elements.InputBox;
import pageobjects._common.elements.SingleSelect;
import pageobjects._common.modal.BaseModal;

public class ContactsModal extends BaseModal {

    @ElementMeta(fieldName = "Contact Type", locator = "contactType")
    SingleSelect contactType;

    @ElementMeta(fieldName = "Contact Name", locator = "contactName")
    InputBox contactName;

    @ElementMeta(fieldName = "Contact Organization", locator = "contactOrganization")
    SingleSelect contactOrganization;

    @ElementMeta(fieldName = "Contact Structure", locator = "contactStructure")
    SingleSelect contactStructure;

    @ElementMeta(fieldName = "Method Of Communication", locator = "methodOfCommunication")
    SingleSelect methodOfCommunication;

    @ElementMeta(fieldName = "Phone Number", locator = "phone")
    InputBox phone;

    @ElementMeta(fieldName = "Email", locator = "email")
    InputBox email;

    @ElementMeta(fieldName = "Address Line 1", locator = "line1")
    InputBox line1;

    @ElementMeta(fieldName = "Address Line 2", locator = "line2")
    InputBox line2;

    @ElementMeta(fieldName = "City", locator = "city")
    InputBox city;

    @ElementMeta(fieldName = "State", locator = "state")
    SingleSelect state;

    @ElementMeta(fieldName = "Zip", locator = "zip")
    InputBox zip;

    public ContactsModal() {
        super(ContactsModal.class);
    }
}