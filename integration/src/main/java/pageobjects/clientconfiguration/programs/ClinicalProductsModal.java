package pageobjects.clientconfiguration.programs;

import pageobjects._common.annotations.ElementMeta;
import pageobjects._common.elements.Checkbox;
import pageobjects._common.elements.TextArea;
import pageobjects._common.modal.BaseModal;

public class ClinicalProductsModal extends BaseModal {

    @ElementMeta(fieldName = "Select Clinical Products", locator = "wellManagedProgramsList")
    Checkbox wellManagedProgramsList;

    @ElementMeta(fieldName = "Notes", locator = "wellManagedProgramsNotes")
    TextArea wellManagedProgramsNotes;

    public ClinicalProductsModal() {
        super(ClinicalProductsModal.class);
    }
}