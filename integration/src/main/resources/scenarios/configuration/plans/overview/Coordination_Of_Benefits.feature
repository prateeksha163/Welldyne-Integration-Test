@CoordinationOfBenefits @epic=Coordination_Of_Benefits
Feature: Edit Coordination Of Benefits section in Plans

  As an administrator
  I should be able to Edit and update fields in Coordination Of Benefits section in Plans

  @Automated @event=Check_enable_coordination_of_benefits_fields @P1
  Scenario: The user validates on opting Co-ordination of Benefits in Plan Settings, its section should appear
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on Add New Plan button
    *     The user updates the form fields with following values in Plans form
      | Plan Name | automation plan          |
      | Plan Code | 679045                   |
      | Notes     | My first automation plan |
    *     The user clicks on the Next button on modal
    *     The user updates the form fields with following values in Plans form
      | Copy Existing Plan | No |
    *     The user updates the form fields with following values in Plans form
      | Plan Benefit Year | 0101 |
    *     The user clicks on the Submit button on the modal overlay
    Then  The user enters 679045 value in Plans table search bar
    Then  The user clicks on edit button for Plan Code with value 679045
    When  The user opts to update the Plan Settings section in Plans overview Page
    Then  The user opts to update following fields in Plan Settings form
      | Accumulators Derived      | Group |
      | CostShare Derived         | Plan  |
      | Generic Incentive Program | Yes   |
      | Coordination Of Benefit   | Yes   |
    *     The user clicks on the Save button on the modal overlay
    *     The user should verify that the Field attribute Does the plan have coordination of benefit?  in Plan Settings section is updated to Yes
    Then  The Co-ordination Of Benefits section should be present on the Plans page

  @waitingFor=Check_enable_coordination_of_benefits_fields @Automated @P1
  Scenario: The user validates that the new Coordination Of Benefits section is created with blank values
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    *     The user enters 679045 value in Plans table search bar
    *     The user clicks on edit button for plan code with value 679045
    Then  The user should validate that attribute values in Coordination of benefits are updated as below
      | COB Plan Type                                         | -               |
      | Processing Rules based on Submit Other Coverage Codes | -               |
      | Cost Share for Secondary Claim                        | Plan Cost Share |

  @waitingFor=Check_enable_coordination_of_benefits_fields @Automated @P1 @event=error_validation
  Scenario Outline:  The user validates error message on Custom Cost Share field on keeping it blank
    Given  The user is on the client configuration page for client with code BMW0002
    When   The user navigates to the Plans tab in the configuration
    Then   The user clicks on edit button for plan code with value 679045
    When   The user opts to update coordination of benefits section in Plans overview Page
    *      The user updates the following fields in the Co-ordination of benefits Form with below values
      | Cost Share for Secondary Claim | Custom |
    *      The user clicks on the Save button on the modal overlay to verify error message
    Then   The user should gets the error message as <Message> on field <field>
    Examples:
      | field                                | Message                                                             |
      | Custom Cost Share                    | Please enter cost share value                                       |
      | Plan Type                            | Please select COB Plan Type                                         |
      | Other Coverage Codes Allowed For COB | Please select Processing Rules based on Submit Other Coverage Codes |

  @P1 @Automated @P1
  Scenario Outline: The user should validate the cancel/close button in Coordination of Benefits form
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on edit button for plan code with value 669843
    When  The user opts to update coordination of benefits section in Plans overview Page
    *     The user clicks on the <Action> button on the modal overlay
    Examples:
      | Action |
      | Cancel |
      | Close  |

  @Automated @P1
  Scenario Outline: The user validates the visibility conditions of Cost Share text box
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on edit button for plan code with value 669843
    *     The user opts to update coordination of benefits section in Plans overview Page
    *     The user updates the field Cost Share for Secondary Claim in Co-ordination of benefits Form with <Value>
    *     The user validates Cost Share* Text box <visibility> in Co-ordination of benefits form
    Examples:
      | Value           | visibility  |
      | Custom          | appeared    |
      | Plan Cost Share | disappeared |

  @Automated @event=visible_cost_share @waitingFor=error_validation @P1
  Scenario: The user should edit the benefit values on selecting cost share field
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user enters 679045 value in Plans table search bar
    Then  The user clicks on edit button for plan code with value 679045
    When  The user opts to update coordination of benefits section in Plans overview Page
    *     The user updates the following fields in the Co-ordination of benefits Form with below values
      | Plan Type                      | Primary & Secondary |
      | Cost Share for Secondary Claim | Custom              |
      | Custom Cost Share              | 200                 |
    *     The user opts in following field Other Coverage Codes Allowed For COB in the coordination of benefits Form
      | OCC 0 : Not Specified                                      |
      | OCC 1 : No Other Coverage                                  |
      | OCC 2 : Exists – Payment Collected                         |
      | OCC 3 : Exists – Claim Not Covered                         |
      | OCC 4 : Exists – Paymt Not Covered                         |
      | OCC 5 : Managed Care Plan Denial                           |
      | OCC 6 : Other Coverage Deny - Not a participating Provider |
      | OCC 7 : Exists – Not in Effect                             |
      | OCC 8 : Claim Billing for Copay                            |
    *     The user clicks on the Submit button on the modal overlay
    Then  The user should validate that attribute values in Coordination of benefits are updated as below
      | Cost Share                                            | $200                                                                                                                                                                                                                                                                                                                |
      | COB Plan Type                                         | Primary & Secondary                                                                                                                                                                                                                                                                                                 |
      | Processing Rules based on Submit Other Coverage Codes | OCC 0 : Not Specified OCC 1 : No Other Coverage OCC 2 : Exists – Payment Collected OCC 3 : Exists – Claim Not Covered OCC 4 : Exists – Paymt Not Covered OCC 5 : Managed Care Plan Denial OCC 6 : Other Coverage Deny - Not a participating Provider OCC 7 : Exists – Not in Effect OCC 8 : Claim Billing for Copay |
      | Cost Share for Secondary Claim                        | Custom                                                                                                                                                                                                                                                                                                              |

  @waitingFor=visible_cost_share @P1
  Scenario: The user should be able to edit the benefit values in Co-ordination of benefits form without opting Cost share value
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user enters 679045 value in Plans table search bar
    Then  The user clicks on edit button for plan code with value 679045
    When  The user opts to update coordination of benefits section in Plans overview Page
    *     The user updates the following fields in the Co-ordination of benefits Form with below values
      | Plan Type                      | Secondary Only  |
      | Cost Share for Secondary Claim | Plan Cost Share |
    *      The user clicks on the Submit button on the modal overlay
    Then   The user should validate that attribute values in Coordination of benefits are updated as below
      | COB Plan Type                                         | Secondary Only                                                                                                                                                                                                                                                                                                      |
      | Processing Rules based on Submit Other Coverage Codes | OCC 0 : Not Specified OCC 1 : No Other Coverage OCC 2 : Exists – Payment Collected OCC 3 : Exists – Claim Not Covered OCC 4 : Exists – Paymt Not Covered OCC 5 : Managed Care Plan Denial OCC 6 : Other Coverage Deny - Not a participating Provider OCC 7 : Exists – Not in Effect OCC 8 : Claim Billing for Copay |
      | Cost Share for Secondary Claim                        | Plan Cost Share                                                                                                                                                                                                                                                                                                     |

  @Automated @P1
  Scenario Outline: The user validates that on selecting specific Plan types, Cost Share for Secondary Claim becomes mandatory field
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on edit button for plan code with value 669843
    When  The user opts to update coordination of benefits section in Plans overview Page
    *     The user updates the field <Field> in Co-ordination of benefits Form with <value>
    Then  The user validates the following titles coming in Co-ordination of Benefits Form
      | COB Plan Type*                                         |
      | Cost Share for Secondary Claim*                        |
      | Processing Rules based on Submit Other Coverage Codes* |
    Examples:
      | Field     | value                              |
      | PLan Type | Secondary Only for Flagged Members |
      | Plan Type | Secondary Only                     |

  @Automated @P1
  Scenario: The user validates the form fields coming under title 'Plan Type'
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on edit button for plan code with value 669843
    When  The user opts to update coordination of benefits section in Plans overview Page
    Then  The user should validate the following fields in Other Coverage Codes Allowed For COB title
      | OCC 0 : Not Specified                                      |
      | OCC 1 : No Other Coverage                                  |
      | OCC 2 : Exists – Payment Collected                         |
      | OCC 3 : Exists – Claim Not Covered                         |
      | OCC 4 : Exists – Paymt Not Covered                         |
      | OCC 5 : Managed Care Plan Denial                           |
      | OCC 6 : Other Coverage Deny - Not a participating Provider |
      | OCC 7 : Exists – Not in Effect                             |
      | OCC 8 : Claim Billing for Copay                            |

  @Automated @P1
  Scenario: The user validates the form fields coming under title 'Coverage Codes Allowed For COB'
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on edit button for plan code with value 669843
    When  The user opts to update coordination of benefits section in Plans overview Page
    Then  The user should validate the following fields in Plan Type title
      | Primary & Secondary                |
      | Secondary Only                     |
      | Secondary Only for Flagged Members |

  @Automated @P1
  Scenario: User validates the form fields coming under title 'Cost Share for Secondary Claim'
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on edit button for plan code with value 669843
    When  The user opts to update coordination of benefits section in Plans overview Page
    Then  The user should validate the following fields in Cost Share for Secondary Claim title
      | Plan Cost Share |
      | Custom          |

  @Automated @P1
  Scenario Outline: The user validates errors on Custom Cost Share field on entering invalid data
    Given  The user is on the client configuration page for client with code BMW0002
    When   The user navigates to the Plans tab in the configuration
    Then   The user clicks on edit button for plan code with value 669843
    When   The user opts to update coordination of benefits section in Plans overview Page
    *      The user updates the following fields in the Co-ordination of benefits Form with below values
      | Cost Share for Secondary Claim | Custom |
    *      The user updates the field Custom Cost Share in Co-ordination of benefits Form with <Value>
    *      The user clicks on the Save button on the modal overlay to verify error message
    Then   The user should gets the error message as <ErrorMessage> on field Custom Cost Share
    Examples:
      | Value | ErrorMessage             |
      | @     | Only numbers are allowed |
      | 67.9  | Only numbers are allowed |
      | abc   | Only numbers are allowed |

  @Automated @P1
  Scenario: The user validates the error message on opting out of mandatory field
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on edit button for plan code with value 669843
    When  The user opts to update coordination of benefits section in Plans overview Page
    *     The user opts out of following field Other Coverage Codes Allowed For COB in the coordination of benefits Form
      | OCC 0 : Not Specified                                      |
      | OCC 1 : No Other Coverage                                  |
      | OCC 2 : Exists – Payment Collected                         |
      | OCC 3 : Exists – Claim Not Covered                         |
      | OCC 4 : Exists – Paymt Not Covered                         |
      | OCC 5 : Managed Care Plan Denial                           |
      | OCC 6 : Other Coverage Deny - Not a participating Provider |
      | OCC 7 : Exists – Not in Effect                             |
      | OCC 8 : Claim Billing for Copay                            |
    *     The user clicks on the Save button on the modal overlay to verify error message
    Then  The user should gets the error message as Please select Processing Rules based on Submit Other Coverage Codes on field Other Coverage Codes Allowed For COB

  @Automated @teardown @event=checkVisibility @P1
  Scenario: The user updates Coordination of Benefits field in Plan Settings for cleanup purpose
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user enters 679045 value in Plans table search bar
    Then  The user clicks on edit button for plan code with value 679045
    When  The user opts to update the Plan Settings section in Plans overview Page
    Then  The user opts to update following fields in Plan Settings form
      | Coordination Of Benefit | No |
    *     The user clicks on the Save button on the modal overlay
    *     The user should verify that the Field attribute Does the plan have coordination of benefit?  in Plan Settings section is updated to No
    *     The Co-ordination Of Benefits section should not be present on the Plans page

  @Automated @teardown @waitingFor=checkVisibility @P1
  Scenario: The user should be able to delete an existing plan in Coordination of benefits section
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user enters 679045 value in Plans table search bar
    Then  The user clicks on delete button for Plan Code with value 679045
    *     The user clicks on the confirm button on the delete modal
    Then  The row with Plan Code 679045 should not be visible in Plans table