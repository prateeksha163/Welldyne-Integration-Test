@ConfigureAbout @epic=Configure_About
Feature: Edit about section in Plans

  As an administrator
  I should be able to Edit About section in Plans

  @event=Check_AboutSection @Automated
  Scenario: The user should edit and validate the form fields
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on edit button for plan code with value Plan002
    When  The user opts to update the About section Plans overview Page
    *     The user updates the form fields with following values in Plans overview form
      | Plan Name         | Automation Plan 0022        |
      | Plan Code         | Plan0022                    |
      | Term Date         | 12312030                    |
      | Effective Date    | 01012020                    |
      | Plan Benefit Year | 0202                        |
      | Notes             | Automation Plan 002 updated |
    *     The user clicks on the Submit button on the modal overlay
    *     The user should see the following values against given attributes on the About section in Plans overview
      | Plan Name         | Automation Plan 0022        |
      | Plan Code         | Plan0022                    |
      | Term Date         | 12-31-2030                  |
      | Effective Date    | 01-01-2020                  |
      | Plan Benefit Year | 02-02 to 02-01              |
      | Notes             | Automation Plan 002 updated |

  @P1 @Automated
  Scenario: The user should validate the form fields in About section form
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on edit button for plan code with value Auto005
    When  The user opts to update the About section Plans overview Page
    Then  The user should see the following form fields in About form
      | Plan Name*         |
      | Plan Code*         |
      | Notes              |
      | Term Date*         |
      | Effective Date*    |
      | Plan Benefit Year* |

  @P1 @Automated
  Scenario: The user should validate that About section is present in Plans overview page
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on edit button for plan code with value Auto005
    Then  The About section should be present on the Plans page

  @P1  @Automated
  Scenario Outline: The user validates field validation error messages when effective date value is greater than termination date value in About Form
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on edit button for plan code with value Auto005
    When  The user opts to update the About section Plans overview Page
    *     The user updates the form fields with following values in Plans overview form
      | Term Date      | 01012019 |
      | Effective Date | 01012020 |
    *     The user clicks on the submit button on the modal overlay to verify error message
    Then  The user should validate the following errors <Error Message> on field <Field> in Plans overview about form
    Examples:
      | Field          | Error Message                                    |
      | Term Date      | Please enter a value greater than effective date |
      | Effective Date | Please enter a value lesser than term date       |

  @P1  @Automated
  Scenario Outline: The user should validate all the errors coming in Plan Code field in About form
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on edit button for plan code with value Auto005
    When  The user opts to update the About section Plans overview Page
    *     The user updates the form fields <Field> with value <Value> in Plans overview form
    *     The user clicks on the submit button on the modal overlay to verify error message
    Then  The user should validate the following errors <Error Message> on field <Field> in Plans overview about form
    Examples:
      | Field     | Value                                 | Error Message                                  |
      | Plan Code | Automation005                         | Plan Code cannot be greater than 10 characters |
      | Plan Code | Automation 005                        | The value cannot contain spaces                |
      | Plan Code | 110063                                | Plan Code already exists                       |
      | Plan Name | Automation Testcase to validate error | Plan Name cannot be greater than 25 characters |

  @P1 @Automated
  Scenario Outline: The user should validate the error messages coming on entering special characters on date fields
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on edit button for plan code with value Auto005
    When  The user opts to update the About section Plans overview Page
    *     The user updates the form fields with following values in Plans overview form
      | Term Date         | # |
      | Effective Date    | * |
      | Plan Benefit Year | % |
    *     The user clicks on the submit button on the modal overlay to verify error message
    Then  The user should validate the following errors <Error Message> on field <Field> in Plans overview about form
    Examples:
      | Field             | Error Message                                    |
      | Term Date         | Please enter correct date format                 |
      | Effective Date    | Please enter correct date format                 |
      | Plan Benefit Year | Please enter correct Plan Benefit start date     |

  @P1 @Automated
  Scenario Outline: The user should validate the cancel/close button in About section
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on edit button for plan code with value Auto005
    When  The user opts to update the About section Plans overview Page
    *     The user clicks on the <Action> button on the modal overlay
    Examples:
      | Action |
      | Cancel |
      | Close  |

  @teardown  @Automated
  Scenario: The user should edit and validate the form fields in About section for cleanup purpose
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on edit button for plan code with value Plan0022
    When  The user opts to update the About section Plans overview Page
    *     The user updates the form fields with following values in Plans overview form
      | Plan Name         | My Automation 002      |
      | Plan Code         | Plan002                |
      | Term Date         | 01012022               |
      | Effective Date    | 01012020               |
      | Plan Benefit Year | 0101                   |
      | Notes             | My automation Plan 002 |
    *     The user clicks on the Submit button on the modal overlay
    *     The user should see the following values against given attributes on the About section in Plans overview
      | Plan Name         | My Automation 002      |
      | Plan Code         | Plan002                |
      | Term Date         | 01-01-2022             |
      | Effective Date    | 01-01-2020             |
      | Plan Benefit Year | 01-01 to 12-31         |
      | Notes             | My automation Plan 002 |