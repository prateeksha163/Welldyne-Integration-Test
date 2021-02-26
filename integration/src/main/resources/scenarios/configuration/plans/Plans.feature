@ConfigurePlans @epic=Configure_Plans
Feature: Manage Plans tables

  As an administrator
  I should be able to add , delete and search the Plans in Plans table

  @P1 @event=create_new_plan_without_copy @Automated
  Scenario: The user should be able to create a new plan without copying existing plan
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on Add New Plan button
    *     The user updates the form fields with following values in Plans form
      | Plan Name | My first automation plan |
      | Plan Code | 9097646                  |
      | Notes     | My first automation plan |
    *     The user clicks on the Next button on modal
    *     The user updates the form fields with following values in Plans form
      | Copy Existing Plan | No |
    *     The user updates the form fields with following values in Plans form
      | Plan Benefit Year | 0907 |
    *     The user clicks on the Submit button on the modal overlay
    Then  The user enters 9097646 value in Plans table search bar
    Then  The user should validate the following row values having Plan Code and 9097646 on searching Plan Table
      | Term Date      | 12-31-2039               |
      | Plan Name      | My first automation plan |
      | State          | In Progress              |
      | Notes          | My first automation plan |
      | Plan Code      | 9097646                  |
      | Effective Date | 01-01-2001               |
      | Status         | Active                   |

  @P1 @Automated
  Scenario Outline: The user should be able search the Plan table by Plan Name or Plan Code
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user enters <value> value in Plans table search bar
    Then  The user should validate the following row values having <Field> and <value> on searching Plan Table
      | Status         | Active                    |
      | Plan Code      | Auto005                   |
      | Notes          | My automation Plan 202068 |
      | State          | In Progress               |
      | Term Date      | 12-31-2030                |
      | Plan Name      | My Automation 005         |
      | Effective Date | 01-01-2020                |
    Examples:
      | Field     | value             |
      | Plan Name | My Automation 005 |
      | Plan Code | Auto005           |

  @P1 @Automated  @event=create_new_plan_with_copy
  Scenario: The user should be able to create a new plan copying existing plan
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on Add New Plan button
    *     The user updates the form fields with following values in Plans form
      | Plan Name | My first automation plan         |
      | Plan Code | 1245378                          |
      | Notes     | My first automation plan 1245378 |
    *     The user clicks on the Next button on modal
    *     The user updates the form fields with following values in Plans form
      | Copy Existing Plan | Yes |
    *     The user updates the form fields with following values in Plans form
      | search Plan | Auto005 |
    *     The user clicks on the Submit button on the modal overlay
    Then  The user should validate the following row values having Plan Code and 1245378 on searching Plan Table
      | Status         | Active                           |
      | Plan Code      | 1245378                          |
      | State          | In Progress                      |
      | Term Date      | 12-31-2030                       |
      | Plan Name      | My first automation plan         |
      | Notes          | My first automation plan 1245378 |
      | Effective Date | 01-01-2020                       |

  @P1 @Automated
  Scenario: The user should get below message on searching the Plans table with special characters
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user enters @ value in Plans table search bar
    Then  The user will get below error message on Plan Table
    """
    No records found.
    """

  @P1 @Automated @waitingFor=create_new_plan_with_copy @event=create_new_plan_without_copy
  Scenario Outline: The user should be able to delete an existing plan
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on delete button for plan code with value <PlanCode>
    *     The user clicks on the confirm button on the delete modal
    Then  The row with Plan Code <PlanCode> should not be visible in Plans table
    Examples:
      | PlanCode |
      | 9097646  |
      | 1245378  |
      | 90976467 |


  @P1 @Automated
  Scenario Outline: The user validates the title and description of the delete Plans modal
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user enters Auto005 value in Plans table search bar
    Then  The user clicks on delete button for plan code with value Auto005
    *     The user should see the delete icon present in the delete modal
    Then  The user should see the <Section> with value as <Value> on the delete modal
    Examples:
      | Section     | Value                                                                                         |
      | title       | Are you sure?                                                                                 |
      | Description | Are you sure that you want to delete the plan My Automation 005? Click on confirm to proceed. |

  @P1 @Automated
  Scenario: The user validates table headers in Plans Table
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user should validate the following table headers present in Plan table
      | Plan Name         |
      | Plan Code         |
      | Effective Date    |
      | Status            |
      | Term Date         |
      | Last Updated Date |
      | State             |
      | Notes             |

  @P1 @Automated
  Scenario: The user should validate the form fields in Base Information Page
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on Add New Plan button
    Then  The user should see the following form fields
      | Plan Name* |
      | Plan Code* |
      | Notes      |

  @P1 @Automated
  Scenario Outline: The user should be able to validate the error messages on Plan code and Plan Name Fields
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on Add New Plan button
    *     The user updates the <Field> with value <Values>  in form fields
    *     The user clicks on the Next button on modal
    Then  The user should validate the following errors <Error Message> on field <Field> in Plans form
    Examples:
      | Field     | Values                                | Error Message                                  |
      | Plan code | Automation005                         | Plan Code cannot be greater than 10 characters |
      | Plan code | Auto 005                              | The value cannot contain spaces                |
      | Plan code | Auto005                               | Plan Code already exists                       |
      | Plan Name | Automation Testcase to validate error | Plan Name cannot be greater than 25 characters |

  @P1 @Automated
  Scenario Outline: The user should validate all the errors coming in Base Information Page on keeping fields blank
    Given The user is on the client configuration page for client with code Auto_Apple
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on Add New Plan button
    *     The user clicks on the Next button on modal
    Then  The user should validate the following errors <Error Message> on field <Field> in Plans form
    Examples:
      | Field     | Error Message          |
      | Plan Name | Please enter Plan Name |
      | Plan Code | Please enter Plan Code |

  @P1  @Automated
  Scenario Outline: The user should validate all the errors coming in Copy a Plan Page on keeping fields blank
    Given The user is on the client configuration page for client with code Auto_Apple
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on Add New Plan button
    *     The user updates the form fields with following values in Plans form
      | Plan Name | My first automation plan             |
      | Plan Code | 67678886                             |
      | Notes     | My first automation plan description |
    *     The user opts to click on the next button on the add a new client form
    *     The user updates the form fields with following values in Plans form
      | Copy Existing Plan | No |
    Then  The user clears the following fields
      | Effective Date   |
      | Termination Date |
    *     The user clicks on the Submit button on the modal overlay to verify error message
    Then  The user should validate the following errors <Error Message> on field <Field> in Plans form
    Examples:
      | Field             | Error Message                      |
      | Effective Date    | Please enter the effective date    |
      | Termination Date  | Please enter the Term date         |
      | Plan Benefit Year | Please enter the Plan Benefit Year |

  @P1 @Automated
  Scenario Outline: The user validates field validation error messages when effective date value is greater than termination date value
    Given The user is on the client configuration page for client with code Auto_Apple
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on Add New Plan button
    *     The user updates the form fields with following values in Plans form
      | Plan Name | My first automation plan             |
      | Plan Code | 67678886                             |
      | Notes     | My first automation plan description |
    *     The user clicks on the Next button on modal
    *     The user updates the form fields with following values in Plans form
      | Copy Existing Plan | No |
    *     The user updates the form fields with following values in Plans form
      | Plan Benefit Year | 0907     |
      | Effective Date    | 01012020 |
      | Termination Date  | 01012019 |
    *     The user clicks on the Submit button on the modal overlay to verify error message
    Then  The user should validate the following errors <Error Message> on field <Field> in Plans form
    Examples:
      | Field            | Error Message                                    |
      | Effective Date   | Please enter a value lesser than term date       |
      | Termination Date | Please enter a value greater than effective date |

  @P1  @Automated #has known issue -->related to wildcard search
  Scenario Outline: The user validates all the matching plans coming in dropdown on searching the Plan with Plan Name or Plan code
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on Add New Plan button
    *     The user updates the form fields with following values in Plans form
      | Plan Name | My first automation plan             |
      | Plan Code | 67678886                             |
      | Notes     | My first automation plan description |
    *     The user clicks on the Next button on modal
    *     The user updates the form fields with following values in Plans form
      | Copy Existing Plan | Yes |
    *     On entering valid text in Search Plan user should validate the matching Plans <Text> coming against client id 100
    Examples:
      | Text    |
      | 110063  |
      | Auto009 |
      | mation  |

  @P1  @Automated
  Scenario Outline: The user should get the below message on searching the Plan form with special characters
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on Add New Plan button
    *     The user updates the form fields with following values in Plans form
      | Plan Name | My first automation plan             |
      | Plan Code | 67678886                             |
      | Notes     | My first automation plan description |
    *     The user clicks on the Next button on modal
    *     The user updates the form fields with following values in Plans form
      | Copy Existing Plan | Yes |
    *     The user validates the following message coming on searching the Search Plan with invalid character <character>
    """
    No Data Available
    """
    Examples:
      | character |
      | @         |
      | #         |

  @P1 @Automated
  Scenario Outline: The user should validate the sort functionality in Plans table
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user sorts the <field> column in Plans Table and validate the results
    Examples:
      | field     |
      | Plan Code |
      | Plan Name |

  @P1 @Automated
  Scenario Outline: The user should validate the filters in Plans table
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user filters the <field> in Plans Table with value <Value>
    Then  The user should only see <Value> values against column <field>
    Examples:
      | field          | Value      |
      | Term Date      | 12-31-2039 |
      | Effective Date | 01-01-2020 |

  @P1 @Automated
  Scenario: The user should validate search bar error message on keeping it blank in Plan form
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plans tab in the configuration
    Then  The user clicks on Add New Plan button
    *     The user updates the form fields with following values in Plans form
      | Plan Name | My first automation plan |
      | Plan Code | abcdef                   |
      | Notes     | My first automation plan |
    *     The user clicks on the Next button on modal
    *     The user updates the form fields with following values in Plans form
      | Copy Existing Plan | Yes |
    *     The user clicks on the Submit button on the modal overlay to verify error message
    *     The user should validate the following errors Please enter plan name or plan code on field Search Plan in Plans form