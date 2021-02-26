@ConfigureMaintenanceDrug @DeleteMaintenanceDrug
Feature: Delete a Maintenance Drug

  @P1 @Automated @waitingFor=AddMaintenanceDrug
  Scenario: User should be able to delete a maintenance drug
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH004
    *     The user navigates to the Drug Settings tab in the plans page
    *     The user opts to delete a maintenance drug having name WellDyneRx Retail
    *     The user clicks on the confirm button on the delete modal
    Then  The user should not see the maintenance drug with name WellDyneRx Retail present

  @P1 @Automated
  Scenario Outline: User validates that the maintenance drug is not deleted when user opts to cancel the delete option
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH004
    *     The user navigates to the Drug Settings tab in the plans page
    *     The user opts to delete a maintenance drug having name WellDyneRx Mail Order
    *     The user clicks on the <Action> button on the delete modal
    Then  The user should see the maintenance drug with name WellDyneRx Mail Order present in the Maintenance Drug section
    Examples:
      | Action |
      | cancel |
      | close  |

  @P2 @Automated
  Scenario Outline: User validates the text of the delete contact modal
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH004
    *     The user navigates to the Drug Settings tab in the plans page
    *     The user opts to delete a maintenance drug having name WellDyneRx Mail Order
    Then  The user should see the <Section> with value as <Value> on the delete modal
    Examples:
      | Section     | Value                                                                                      |
      | title       | Are you sure?                                                                              |
      | Description | Deleting this will delete Maintenance Drug Settings for the WellDyneRx Mail Order channel. |


  @P2 @Automated
  Scenario: User validates if image is present in the delete contact modal
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH004
    *     The user navigates to the Drug Settings tab in the plans page
    *     The user opts to delete a maintenance drug having name WellDyneRx Mail Order
    Then  The user should see the delete icon present in the delete modal
