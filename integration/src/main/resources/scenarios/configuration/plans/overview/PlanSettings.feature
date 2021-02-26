@ConfigurePlanSettings @epic=Plan_Settings
Feature: Edit Plan Settings section in Plans

  As an administrator
  I should be able to opt in and opt out Plan settings in Plans

  @P1 @event=new_plan @Automated
  Scenario: The user should validate the attributes and values of a new plan in Plan Settings
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on Add New Plan button
    *     The user updates the form fields with following values in Plans form
      | Plan Name | automation plan          |
      | Plan Code | 99995                    |
      | Notes     | My first automation plan |
    *     The user clicks on the Next button on modal
    *     The user updates the form fields with following values in Plans form
      | Copy Existing Plan | No |
    *     The user updates the form fields with following values in Plans form
      | Plan Benefit Year | 0101 |
    *     The user clicks on the Submit button on the modal overlay
    Then  The user enters 99995 value in Plans table search bar
    Then  The user clicks on edit button for plan code with value 99995
    Then  The user should validate the following attributes and values in Plan Settings section
      | Is this an HDHP or HSA plan?                    | No |
      | How are accumulators derived?                   | -  |
      | How is cost share derived?                      | -  |
      | Does the plan have Maintenance Program?         | No |
      | Are Multi Ingredient Compounds Covered?         | No |
      | Does the plan have coordination of benefit?     | No |
      | Does the plan have Generic Incentive Program?   | -  |
      | Does the plan have In-House Pharmacy Network?   | No |
      | Does the plan have In-House Prescriber Network? | No |

  @Automated @P1
  Scenario: The user validates the form fields in Plan Settings form
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user enters 110063 value in Plans table search bar
    Then  The user clicks on edit button for Plan Code with value 110063
    When  The user opts to update the Plan Settings section in Plans overview Page
    Then  The user should validate the following form fields in Plan Settings Form
      | Is this an HDHP or HSA plan?*                    |
      | How are accumulators derived?*                   |
      | How is cost share derived?*                      |
      | Does the plan have Maintenance Program?*         |
      | Are Multi Ingredient Compounds Covered?*         |
      | Does the plan have coordination of benefit?*     |
      | Does the plan have Generic Incentive Program?*   |
      | Does the plan have In-House Prescriber Network?* |
      | Does the plan have In-House Pharmacy Network?*   |

  @Automated @waitingFor=new_plan @P1
  Scenario: The user should validate attributes and values in Plan Settings section after updating form fields
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user enters 99995 value in Plans table search bar
    Then  The user clicks on edit button for plan code with value 99995
    Then  The user opts to update the Plan Settings section in Plans overview Page
    Then  The user opts to update following fields in Plan Settings form
      | Accumulators Derived      | Group |
      | CostShare Derived         | Plan  |
      | Generic Incentive Program | Yes   |
    *     The user clicks on the Save button on the modal overlay
    Then  The user should validate the following attributes and values in Plan Settings section
      | How are accumulators derived?                 | Group |
      | How is cost share derived?                    | Plan  |
      | Does the plan have Generic Incentive Program? | Yes   |

  @Automated @event=Check_disabled_fields @waitingFor=new_plan @P1
  Scenario: The user should validate all the default disabled side tabs in Plans overview
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user enters 99995 value in Plans table search bar
    Then  The user clicks on edit button for plan code with value 99995
    *     The user should validate side tab navigator Drug Settings should be now disabled in Plan Edit Page

  @Automated @P1
  Scenario: The user should validate all the disabled fields in Plan Settings Form
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user enters 110063 value in Plans table search bar
    Then  The user clicks on edit button for plan code with value 110063
    Then  The user opts to update the Plan Settings section in Plans overview Page
    *     The following fields should be disabled on Plans settings form
      | Accumulators Derived |
      | CostShare Derived    |

  @Automated  @waitingFor=Check_disabled_fields @P1
  Scenario Outline: The user validates drug settings tab visibility
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user enters 99995 value in Plans table search bar
    Then  The user clicks on edit button for plan code with value 99995
    Then  The user opts to update the Plan Settings section in Plans overview Page
    When  The user opts to update the field <Field> with value Yes in Plan settings form
    *     The user clicks on the Save button on the modal overlay
    *     The user should verify that the Field attribute <Attribute>  in Plan Settings section is updated to Yes
    *     The user should validate side tab navigator Drug Settings should be now enabled in Plan Edit Page
    Examples:
      | Field              | Attribute                               |
      | Supports Compounds | Are Multi Ingredient Compounds Covered? |
      | Maintenance Drugs  | Does the plan have Maintenance Program? |

  @Automated @P1
  Scenario Outline: The user validates the warning messages appearing on unchecking specific Plan settings
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user enters 110063 value in Plans table search bar
    Then  The user clicks on edit button for plan code with value 110063
    When  The user opts to update the Plan Settings section in Plans overview Page
    And   The user opts to update the field <field> with value No in Plan settings form
    And   The user should validate the below warning message on unchecking any plan
    """
    If you change any plan settings, data of that particular entity will be deleted. Do you still want to proceed?
    """
    Examples:
      | field                       |
      | Plan Type                   |
      | Coordination Of Benefit     |
      | Generic Incentive Program   |
      | Maintenance Drugs           |
      | Supports Compounds          |
      | In House Prescriber Network |
      | In House Pharmacy Network   |

  @teardown @Automated
  Scenario: The user should be able to delete an existing plan
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user enters 99995 value in Plans table search bar
    Then  The user clicks on delete button for plan code with value 99995
    *     The user clicks on the confirm button on the delete modal
    Then  The row with Plan Code 99995 should not be visible in Plans table