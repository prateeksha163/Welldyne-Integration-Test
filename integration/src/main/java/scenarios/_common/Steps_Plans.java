package scenarios._common;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageobjects.ClientConfigurationPage;
import pageobjects._common.modal.BaseModal;
import pageobjects.clientconfiguration.plans.editplans.drugsettings.Compounds;
import pageobjects.clientconfiguration.plans.editplans.drugsettings.MaintenanceDrug;
import pageobjects.clientconfiguration.plans.editplans.overview.CoordinationOfBenefits;
import pageobjects.clientconfiguration.plans.editplans.overview.GenericIncentiveProgram;
import pageobjects.clientconfiguration.plans.editplans.overview.PlansAbout;
import pageobjects.clientconfiguration.plans.PlansTable;
import scenarios.Context;

import static org.fest.assertions.api.Assertions.assertThat;

public class Steps_Plans {

    private final ClientConfigurationPage configurationPage;

    public Steps_Plans(Context context) {
        configurationPage = new ClientConfigurationPage(context);
    }

    private PlansTable get_Plans_container() {
        return configurationPage.getConfigurePlansPage().getPlans("plans");
    }

    @And("^The user clicks on the (.+) button on modal$")
    public void user_clicks_on_action_button_on_modal(String action) {
        BaseModal modal = new BaseModal();
        modal.clickOnActionButton(action);
    }

    @Then("^The (.+) section should be present on the Plans page$")
    public void verify_section_is_present(String section) {
        switch(section.toUpperCase()){
            case "ABOUT":
                PlansAbout aboutContainer = (PlansAbout) configurationPage.getConfigurePlansPage().getDataContainer("About");
                assertThat(aboutContainer.getContainerHeading()).isEqualTo(section);
                break;
            case "CO-ORDINATION OF BENEFITS":
                CoordinationOfBenefits CoordinationOfBenefitsContainer = (CoordinationOfBenefits) configurationPage.getConfigurePlansPage().getDataContainer("Co-ordination Of Benefits");
                assertThat(CoordinationOfBenefitsContainer.getContainerHeading()).isEqualTo(section);
                break;
            case "GENERIC INCENTIVE PROGRAM":
                GenericIncentiveProgram GenericIncentiveProgramContainer = (GenericIncentiveProgram) configurationPage.getConfigurePlansPage().getDataContainer("Generic Incentive Program");
                assertThat(GenericIncentiveProgramContainer.getContainerHeading()).isEqualTo(section);
                break;
            case "COMPOUNDS":
                Compounds compoundsContainer = (Compounds) configurationPage.getConfigurePlansPage().getDataContainer("Compounds");
                assertThat(compoundsContainer.getContainerHeading()).isEqualTo(section);
                break;
            case "MAINTENANCE DRUG":
                MaintenanceDrug maintenanceDrugContainer = (MaintenanceDrug) configurationPage.getConfigurePlansPage().getDataContainer("Maintenance Drug");
                assertThat(maintenanceDrugContainer.getContainerHeading()).isEqualTo(section);
        }
    }

    @Then("^The (.+) section should not be present on the Plans page$")
    public void verify_section_is_not_present(String section) {
        switch (section.toUpperCase()) {
            case "GENERIC INCENTIVE PROGRAM":
                try {
                    GenericIncentiveProgram GenericIncentiveProgramContainer = (GenericIncentiveProgram) configurationPage.getConfigurePlansPage().getDataContainer("Generic Incentive Program");
                    GenericIncentiveProgramContainer.getContainerHeading().contentEquals(section);
                } catch (com.codeborne.selenide.ex.ElementNotFound e) {
                    assertThat(e.getMessage().contains("NoSuchElementException"));
                }

            case "CO-ORDINATION OF BENEFITS":
                try {
                    CoordinationOfBenefits CoordinationOfBenefitsContainer = (CoordinationOfBenefits) configurationPage.getConfigurePlansPage().getDataContainer("Co-ordination Of Benefits");
                    CoordinationOfBenefitsContainer.getContainerHeading().contentEquals(section);
                } catch (com.codeborne.selenide.ex.ElementNotFound e) {
                    assertThat(e.getMessage().contains("NoSuchElementException"));
                }
        }
    }

    @And("^The user clicks on (.+) button for (.+) with value (.+)$")
    public void user_clicks_on_action_button_in_table(String action, String field, String value) throws InterruptedException {
        Thread.sleep(5000);
        get_Plans_container().clickOnActionButtonOnTable(action, field, value);
    }
}
