@GenericIncentiveProgram @epic=Generic_Incentive_Program
Feature: Edit Generic Incentive Program section in Plans

  As an administrator
  I should be able to Edit and update fields in Generic Incentive Program section in Plans

  @event=Check_enable_Generic_fields @Automated @P1
  Scenario: The user validates on opting Generic Incentive Program in Plan Settings, its section should appear
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on Add New Plan button
    *     The user updates the form fields with following values in Plans form
      | Plan Name | automation plan          |
      | Plan Code | 999956                   |
      | Notes     | My first automation plan |
    *     The user clicks on the Next button on modal
    *     The user updates the form fields with following values in Plans form
      | Copy Existing Plan | No |
    *     The user updates the form fields with following values in Plans form
      | Plan Benefit Year | 0101 |
    *     The user clicks on the Submit button on the modal overlay
    Then  The user enters 999956 value in Plans table search bar
    Then  The user clicks on edit button for plan code with value 999956
    When  The user opts to update the Plan Settings section in Plans overview Page
    Then  The user opts to update following fields in Plan Settings form
      | Accumulators Derived      | Group |
      | CostShare Derived         | Plan  |
      | Generic Incentive Program | Yes   |
    *     The user clicks on the Save button on the modal overlay
    *     The user should verify that the Field attribute Does the plan have Generic Incentive Program?  in Plan Settings section is updated to Yes
    Then  The Generic Incentive Program section should be present on the Plans page

  @waitingFor=Check_enable_Generic_fields @Automated @P1
  Scenario: The user validates that the new Generic Incentive Program section is created with blank values
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    *     The user enters 999956 value in Plans table search bar
    *     The user clicks on edit button for Plan Code with value 999956
    Then  The user validates attributes with values coming in Generic incentive program Section
      | Included PSC/DAW Codes    | - |
      | Which copay should apply? | - |
      | Penalty Notes             | - |

  @Automated @P1
  Scenario: The user validates Title and Note box in Generic Incentive Program form
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on edit button for plan code with value 669843
    When  The user opts to update the Generic Incentive Program section Plans overview Page
    Then  The user should validate the below title and Notes coming in Generic Incentive Program Form
      | Included PSC/DAW Codes     |
      | Penalty Notes*             |
      | Which copay should apply?* |

  @waitingFor=Check_enable_Generic_fields @Automated @P1
  Scenario: The user validates the default text coming in Penalty Notes field for newly created Generic Incentive Program Section
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user enters 999956 value in Plans table search bar
    *     The user clicks on edit button for plan code with value 999956
    *     The user opts to update the Generic Incentive Program section Plans overview Page
    Then  The user validates the following default text coming in Penalty Notes field
     """
     Member Responsibility will include co pay amount plus the AWP difference between the branded and generic medication in an event the member chooses a branded drug which has a generic alternate available.
     """

  @Automated @P1 @event=validate_errors @waitingFor=Check_enable_Generic_fields
  Scenario Outline: The user should validate the error message on Mandatory fields in Generic Incentive Program form
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user enters 999956 value in Plans table search bar
    Then  The user clicks on edit button for plan code with value 999956
    When  The user opts to update the Generic Incentive Program section Plans overview Page
    *     User clears the Penalty Notes text Box
    *     The user clicks on the Save button on the modal overlay to verify error message
    Then  The user should get the following error message <Message> on <field> field
    Examples:
      | field         | Message                                    |
      | Penalty Notes | Please enter the penalty notes             |
      | Copay Applied | Please select the copay that should apply. |

  @Automated @P1
  Scenario: The user validates the form fields coming under title 'Included PSC/DAW Codes'
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on edit button for plan code with value 669843
    When  The user opts to update the Generic Incentive Program section Plans overview Page
    Then  The user should validate the following PSC DAW Codes fields in Generic Incentive Program Form
      | DAW 0 : No PSC Indicated                  |
      | DAW 1 : Substitution Not Allowed by MD    |
      | DAW 2 : Patient Selected Product          |
      | DAW 3 : Pharmacist Selected Product       |
      | DAW 4 : Product Not in Stock              |
      | DAW 5 : Brand Product Selected as Generic |
      | DAW 6 : Override                          |
      | DAW 7 : Product Mandated by Law           |
      | DAW 8 : Product Not Available             |
      | DAW 9 : Other                             |

  @Automated @P1
  Scenario: The user validates the form fields coming for title 'Which copay should apply'
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on edit button for plan code with value 669843
    When  The user opts to update the Generic Incentive Program section Plans overview Page
    Then  The user should validate the following Copay Applied fields in Generic Incentive Program Form
      | Applicable Formulary Copay(Standard) |
      | Generic                              |
      | Preferred Brand                      |
      | Non-Preferred Brand                  |

  @P1 @Automated
  Scenario Outline: The user should validate the cancel/close button in Generic Incentive Program form
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on edit button for plan code with value 669843
    When  The user opts to update the Generic Incentive Program section Plans overview Page
    *     The user clicks on the <Action> button on the modal overlay
    Examples:
      | Action |
      | Cancel |
      | Close  |

  @Automated @waitingFor=validate_errors @P1
  Scenario: The user updates the form fields and validate the attributes in Generic Incentive Program section
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user enters 999956 value in Plans table search bar
    Then  The user clicks on edit button for plan code with value 999956
    When  The user opts to update the Generic Incentive Program section Plans overview Page
    *     The user opts in the following PSC DAW Codes in the Generic Incentive Program Form
      | DAW 0 : No PSC Indicated                  |
      | DAW 1 : Substitution Not Allowed by MD    |
      | DAW 2 : Patient Selected Product          |
      | DAW 3 : Pharmacist Selected Product       |
      | DAW 4 : Product Not in Stock              |
      | DAW 5 : Brand Product Selected as Generic |
      | DAW 6 : Override                          |
      | DAW 7 : Product Mandated by Law           |
      | DAW 8 : Product Not Available             |
      | DAW 9 : Other                             |
    *     The user updates the fields with following values in Generic Incentive Program Form
      | Penalty Notes | Updated value in Penalty box |
      | Copay Applied | Preferred Brand              |
    *     The user clicks on the Submit button on the modal overlay
    Then  The user validates attributes with values coming in Generic incentive program Section
      | Included PSC/DAW Codes    | DAW 0 : No PSC Indicated DAW 1 : Substitution Not Allowed by MD DAW 2 : Patient Selected Product DAW 3 : Pharmacist Selected Product DAW 4 : Product Not in Stock DAW 5 : Brand Product Selected as Generic DAW 6 : Override DAW 7 : Product Mandated by Law DAW 8 : Product Not Available DAW 9 : Other |
      | Which copay should apply? | Preferred Brand                                                                                                                                                                                                                                                                                          |
      | Penalty Notes             | Updated value in Penalty box                                                                                                                                                                                                                                                                             |

  @Automated @teardown @event=checkVisibility @P1
  Scenario: Teardown method to updates Generic Incentive Program field in Plan Settings
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user enters 999956 value in Plans table search bar
    Then  The user clicks on edit button for plan code with value 999956
    When  The user opts to update the Plan Settings section in Plans overview Page
    Then  The user opts to update following fields in Plan Settings form
      | Generic Incentive Program | No |
    *     The user clicks on the Save button on the modal overlay
    *     The user should verify that the Field attribute Does the plan have Generic Incentive Program?  in Plan Settings section is updated to No
    *     The Generic Incentive Program section should not be present on the Plans page

  @teardown @waitingFor=checkVisibility @P1
  Scenario: The user should be able to delete an existing plan
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user enters 999956 value in Plans table search bar
    Then  The user clicks on delete button for plan code with value 999956
    *     The user clicks on the confirm button on the delete modal
    Then  The row with Plan Code 999956 should not be visible in Plans table