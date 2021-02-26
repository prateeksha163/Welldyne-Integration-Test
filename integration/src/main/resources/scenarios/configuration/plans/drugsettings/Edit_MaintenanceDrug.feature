@ConfigureMaintenanceDrug @EditMaintenanceDrug
Feature: Edit a Maintenance Drug

  @P1 @Automated
  Scenario: User should be able to edit a maintenance drug
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH004
    *     The user navigates to the Drug Settings tab in the plans page
    *     The user opts to edit a maintenance drug having name Automation Channel
    *     The user clears the following form fields on maintenance drug form
      | Min Day Supply |
    *     The user enters the following values in maintenance drug modal
      | Min Day Supply | 15 |
    *     The user clicks on the save button on the modal overlay
    Then  The user should see the maintenance drug with attribute Min Day Supply and value 15 present

  @P2 @Automated
  Scenario Outline: User validates that the maintenance drug is not deleted when user opts to cancel the edit option
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH004
    *     The user navigates to the Drug Settings tab in the plans page
    *     The user opts to edit a maintenance drug having name WellDyneRx Mail Order
    *     The user clears the following form fields on maintenance drug form
      | Min Day Supply |
    *     The user enters the following values in maintenance drug modal
      | Min Day Supply | 10 |
    *     The user clicks on the <Action> button on the delete modal
    Then  The user should see the maintenance drug with attribute Min Day Supply and value 100 present
    Examples:
      | Action |
      | cancel |
      | close  |

  @P1 @Automated
  Scenario: User validates error messages on mandatory fields
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH004
    *     The user navigates to the Drug Settings tab in the plans page
    *     The user opts to edit a maintenance drug having name Automation Channel
    *     The user clears the following form fields on maintenance drug form
      | Min Day Supply   |
      | Max Day Supply   |
      | Refill Threshold |
    *     The user clicks on the save button on the modal overlay to verify error message
    Then  The user validates the following error messages on the Maintenance Drug Modal
      | Min Day Supply   | Please enter the min day supply   |
      | Max Day Supply   | Please enter the max day supply   |
      | Refill Threshold | Please enter the refill threshold |

  @P1 @Automated
  Scenario: User validates field validations on the form fields
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH004
    *     The user navigates to the Drug Settings tab in the plans page
    *     The user opts to edit a maintenance drug having name Automation Channel
    *     The user clears the following form fields on maintenance drug form
      | Min Day Supply          |
      | Max Day Supply          |
      | Refill Threshold        |
      | Number of Fills Allowed |
    *     The user enters the following values in maintenance drug modal
      | Min Day Supply          | #         |
      | Max Day Supply          | @         |
      | Refill Threshold        | test      |
      | Number of Fills Allowed | "test123" |
    *     The user clicks on the save button on the modal overlay to verify error message
    Then  The user validates the following error messages on the Maintenance Drug Modal
      | Min Day Supply          | Only numbers are allowed |
      | Max Day Supply          | Only numbers are allowed |
      | Refill Threshold        | Only numbers are allowed |
      | Number of Fills Allowed | Only numbers are allowed |

  @P1 @Automated
  Scenario: User validates the min max validation on the form fields
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH004
    *     The user navigates to the Drug Settings tab in the plans page
    *     The user opts to edit a maintenance drug having name Automation Channel
    *     The user clears the following form fields on maintenance drug form
      | Min Day Supply   |
      | Max Day Supply   |
      | Refill Threshold |
    *     The user enters the following values in maintenance drug modal
      | Min Day Supply   | 20 |
      | Max Day Supply   | 10 |
      | Refill Threshold | 20 |
    *     The user clicks on the save button on the modal overlay to verify error message
    Then  The user validates the following error messages on the Maintenance Drug Modal
      | Min Day Supply | Please enter a value lesser than max day supply  |
      | Max Day Supply | Please enter a value greater than min day supply |