@ConfigureAccumulators @EditDeleteAccumSetting
Feature: Edit or Delete an Accumulator Setting

  @P1 @Automated @event=EditAccumSetting
  Scenario: User should be able to Edit an Accumulator Setting
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH003
    *     The user navigates to the Accumulators tab in the plans page
    *     The user opts to edit an Accumulator setting with name Benefit Maximum
    *     The user enters the following values in the Accumulator Settings modal
      | Select Mode | Non - Embedded |
    *     The user clicks on the save button on modal
    Then  The user should see Accumulator Setting Benefit Maximum updated with field Mode and value Non - Embedded


  @P1 @Automated @waitingFor=AddAccumSetting
  Scenario: User should be able to Delete an Accumulator Setting
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH001
    *     The user navigates to the Accumulators tab in the plans page
    *     The user opts to delete an Accumulator setting with name Out Of Pocket Maximum
    *     The user clicks on the confirm button on the delete modal
    Then  The user should not see the Accumulator setting with name Out Of Pocket Maximum present

  @P2 @Automated
  Scenario Outline: User validates that the Accumulator Settings field should not be updated when user cancels the edit option
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH001
    *     The user navigates to the Accumulators tab in the plans page
    *     The user opts to edit an Accumulator setting with name Deductible
    *     The user enters the following values in the Accumulator Settings modal
      | Select Mode | Non - Embedded |
    *     The user clicks on the <Action> button on the modal overlay
    Then  The user should see Accumulator Setting Deductible updated with field Mode and value Embedded
    Examples:
      | Action |
      | cancel |
      | close  |

  @P2 @Automated
  Scenario Outline: User validates that the Accumulator property is not deleted when user opts to cancel the delete option
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH001
    *     The user navigates to the Accumulators tab in the plans page
    *     The user opts to delete an Accumulator setting with name Deductible
    *     The user clicks on the <Action> button on the delete modal
    Then  The user should see the accumulator setting with name Deductible present in the Accumulator Settings section
    Examples:
      | Action |
      | cancel |
      | close  |

  @P2 @Automated
  Scenario Outline: User validates the text of the delete contact modal
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH001
    *     The user navigates to the Accumulators tab in the plans page
    *     The user opts to delete an Accumulator setting with name Deductible
    Then  The user should see the <Section> with value as <Value> on the delete modal
    Examples:
      | Section     | Value                                                                                   |
      | title       | Are you sure?                                                                           |
      | Description | If you delete Deductible, accumulator settings and data will be removed from this plan. |


  @P2 @Automated
  Scenario: User validates if image is present in the delete contact modal
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH001
    *     The user navigates to the Accumulators tab in the plans page
    *     The user opts to delete an Accumulator setting with name Deductible
    Then  The user should see the delete icon present in the delete modal

  @P1 @Automated @Teardown @waitingFor=EditAccumSetting
  Scenario: Resetting edited details
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH003
    *     The user navigates to the Accumulators tab in the plans page
    *     The user opts to edit an Accumulator setting with name Benefit Maximum
    *     The user enters the following values in the Accumulator Settings modal
      | Select Mode | Embedded |
    *     The user clicks on the save button on modal
    Then  The user should see Accumulator Setting Benefit Maximum updated with field Mode and value Embedded
