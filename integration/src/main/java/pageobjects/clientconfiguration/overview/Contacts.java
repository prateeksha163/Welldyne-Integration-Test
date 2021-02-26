package pageobjects.clientconfiguration.overview;
import pageobjects._common.containers.ParentDataContainer;
import pageobjects._common.modal.DeleteModal;

public class Contacts extends ParentDataContainer {

    public Contacts(String name) {
        super(name);
    }

    public ContactsModal addNewContact() {
        this.addNestedContainer();
        return new ContactsModal();
    }

    public ContactsModal getNewContactModal() {
        return new ContactsModal();
    }

    public DeleteModal getDeleteContactModal() {
        return new DeleteModal();
    }
}
