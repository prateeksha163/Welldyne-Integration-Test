@ConfigureMaintenanceDrug @AddMaintenanceDrug
Feature: Add a Maintenance Drug

  @P1 @Automated
  Scenario: User should see the Maintenance Drug section present
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH003
    *     The user navigates to the Drug Settings tab in the plans page
    Then  The Maintenance Drug section should be present on the Plans page

  @P1 @Automated @event=AddMaintenanceDrug
  Scenario: User should be able to add a maintenance drug
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH004
    *     The user navigates to the Drug Settings tab in the plans page
    *     The user opts to add a maintenance drug
    *     The user enters the following values in maintenance drug modal
      | Channels                | WellDyneRx Retail |
      | Min Day Supply          | 10                |
      | Max Day Supply          | 20                |
      | Refill Threshold        | 20                |
      | Number of Fills Allowed | 100               |
    *     The user clicks on the save button on the modal overlay
    Then  The user should see the following Maintenance Drug present WellDyneRx Retail

  @P1 @Automated  @event=CheckAddButton
  Scenario: User validates when all Maintenance Drugs are added then add button is removed
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH003
    *     The user navigates to the Drug Settings tab in the plans page
    *     The user opts to add a maintenance drug
    *     The user enters the following values in maintenance drug modal
      | Channels                | Test Network details |
      | Min Day Supply          | 10                   |
      | Max Day Supply          | 20                   |
      | Refill Threshold        | 20                   |
      | Number of Fills Allowed | 100                  |
    *     The user clicks on the save button on the modal overlay
    Then  User validates that the add button is removed

  @P1 @Automated
  Scenario: User validates error messages on mandatory fields
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH004
    *     The user navigates to the Drug Settings tab in the plans page
    *     The user opts to add a maintenance drug
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
    *     The user opts to add a maintenance drug
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
    *     The user opts to add a maintenance drug
    *     The user enters the following values in maintenance drug modal
      | Min Day Supply   | 20 |
      | Max Day Supply   | 10 |
      | Refill Threshold | 20 |
    *     The user clicks on the save button on the modal overlay to verify error message
    Then  The user validates the following error messages on the Maintenance Drug Modal
      | Min Day Supply | Please enter a value lesser than max day supply  |
      | Max Day Supply | Please enter a value greater than min day supply |

  @P1 @Automated
  Scenario Outline: User validates that on closing the form maintenance drug is not created
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH004
    *     The user navigates to the Drug Settings tab in the plans page
    *     The user opts to add a maintenance drug
    *     The user enters the following values in maintenance drug modal
      | Channels                | Test Network details |
      | Min Day Supply          | 10                   |
      | Max Day Supply          | 20                   |
      | Refill Threshold        | 20                   |
      | Number of Fills Allowed | 100                  |
    *     The user clicks on the <Action> button on the modal overlay
    Then  The user should not see the following maintenance drug present Test Network details
    Examples:
      | Action |
      | cancel |
      | close  |

  @TearDown @waitingFor=CheckAddButton @P1 @Automated
  Scenario: teardown scenario to delete maintenance drug
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH003
    *     The user navigates to the Drug Settings tab in the plans page
    *     The user opts to delete a maintenance drug having name Test Network details
    *     The user clicks on the confirm button on the delete modal
    Then  The user should not see the maintenance drug with name Test Network details present