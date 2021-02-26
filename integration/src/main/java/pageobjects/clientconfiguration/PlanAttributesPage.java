package pageobjects.clientconfiguration;
import pageobjects._common.containers.DataContainer;
import pageobjects.clientconfiguration.planattributes.PlanAttributes;

public class PlanAttributesPage {
    public DataContainer getDataContainer(String container) {
        switch (container.toUpperCase()) {
            case "PA RULE RESPONSIBILITY":
                return getPlanAttributes();
        } return null;
    }

    private DataContainer getPlanAttributes() {
        return new PlanAttributes("paResponsibility");
    }


}