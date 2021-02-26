package pageobjects.clientconfiguration.plans.editplans.drugsettings;

import pageobjects._common.annotations.ElementMeta;
import pageobjects._common.elements.InputBox;
import pageobjects._common.elements.SingleSelect;
import pageobjects._common.modal.BaseModal;

public class MaintenanceDrugModal extends BaseModal {

    @ElementMeta(fieldName = "Channels", locator = "channelType")
    SingleSelect channelType;

    @ElementMeta(fieldName = "Min Day Supply", locator = "minDaySupply")
    InputBox minDaySupply;

    @ElementMeta(fieldName = "Max Day Supply", locator = "maxDaySupply")
    InputBox maxDaySupply;

    @ElementMeta(fieldName = "Refill Threshold", locator = "refillThreshold")
    InputBox refillThreshold;

    @ElementMeta(fieldName = "Number of Fills Allowed", locator = "numberOfFillsAllowed")
    InputBox numberOfFillsAllowed;

    public MaintenanceDrugModal() {
        super(MaintenanceDrugModal.class);
    }
}

