@ConfigureAccumulators @AddAccumulatorSetting
Feature: Add Accumulator Settings

  @P1 @Automated
  Scenario: User should see the Accumulator Settings section present
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH001
    *     The user navigates to the Accumulators tab in the plans page
    Then  The Accumulator Settings section should be present on the Plans page

  @P1 @Automated @event=AddAccumSetting
  Scenario: User should be able to add a new Accumulator Setting
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH001
    *     The user navigates to the Accumulators tab in the plans page
    *     The user opts to add a new Accumulator setting
    *     The user opts to select Out Of Pocket Maximum value in Select Accumulator field
    *     The user enters the following values in the Accumulator Settings modal
      | Select Mode                        | Embedded                    |
      | Select Category                    | Medical Only                |
      | Select Period                      | Plan year                   |
      | Select Tracking                    | Vendor Tracks Independently |
      | Fourth Quarter Carry Over          | Yes                         |
      | PSC/DAW Penalties Applies          | No                          |
      | Penalty Continues after OOP Is met | No                          |
      | Accumulator is Near Real Time      | Yes                         |
    *     The user clicks on the save button on modal
    Then  The user should see the accumulator setting with name Out Of Pocket Maximum present in the Accumulator Settings section

  @P1 @Automated
  Scenario: User validates that all the form fields are present in accumulator settings modal
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH003
    *     The user navigates to the Accumulators tab in the plans page
    *     The user opts to add a new Accumulator setting
    *     The user opts to select Out Of Pocket Maximum value in Select Accumulator field
    Then  The user validates if the following fields are present in accumulator settings modal
      | Select Accumulator*                 |
      | Select Mode*                        |
      | Select Category*                    |
      | Select Period*                      |
      | Select Tracking*                    |
      | Fourth Quarter Carry Over           |
      | PSC/DAW Penalties Applies           |
      | Penalty Continues after OOP Is met* |
      | Accumulator is Near Real Time*      |
      | Bypass Category                    |

  @P1 @Automated
  Scenario: User validates all the error messages on mandatory fields
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH001
    *     The user navigates to the Accumulators tab in the plans page
    *     The user opts to add a new Accumulator setting
    *     The user opts to select Benefit Maximum value in Select Accumulator field
    *     The user opts to select Custom value in Select Period field
    *     The user clicks on the save button on the modal overlay to verify error message
    Then  The user validates the error messages on the following mandatory fields
      | Select Mode                                     | Please select a mode                                          |
      | Select Category                                 | Please select the category                                    |
      | Select Tracking                                 | Please select the tracking                                    |
      | Accumulator is Near Real Time                   | Please select if accumulator is near real time.               |
      | Period Start Date From                          | Please enter start date                                       |
      | Claim Adjudication after Benefit Maximum is met | Please select Claim Adjudication after Benefit Maximum is met |
      | Period End Date To                              | Please enter end date                                         |

  @P1 @Automated
  Scenario: User validates error message on Select Accumulator field
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH001
    *     The user navigates to the Accumulators tab in the plans page
    *     The user opts to add a new Accumulator setting
    *     The user clicks on the save button on the modal overlay to verify error message
    Then  The user should see the following error message on Select Accumulator field on Accumulator Modal
    """
    Please select an accumulator
    """

  @P1 @Automated @event=CheckAddButton
  Scenario: User validates that on adding all four accumulators, add button is disabled
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH002
    *     The user navigates to the Accumulators tab in the plans page
    *     The user opts to add a new Accumulator setting
    *     The user opts to select Lifetime Benefit Maximum value in Select Accumulator field
    *     The user enters the following values in the Accumulator Settings modal
      | Select Mode                                     | Embedded                    |
      | Select Category                                 | Medical Only                |
      | Effective From Date                             | 10-01-2020                  |
      | Select Tracking                                 | Vendor Tracks Independently |
      | Fourth Quarter Carry Over                       | Yes                         |
      | PSC/DAW Penalties Applies                       | No                          |
      | Claim Adjudication after Benefit Maximum is met | Reject                      |
      | Accumulator is Near Real Time                   | Yes                         |
    *     The user clicks on the save button on modal
    Then  The user validates if the add button is disabled

  @P1 @Automated @TearDown @waitingFor=CheckAddButton
  Scenario: Teardown scenario to delete Accumulator property
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH002
    *     The user navigates to the Accumulators tab in the plans page
    *     The user opts to delete an Accumulator setting with name Lifetime Benefit Maximum
    *     The user clicks on the confirm button on the delete modal
    Then  The user should not see the Accumulator setting with name Lifetime Benefit Maximum present