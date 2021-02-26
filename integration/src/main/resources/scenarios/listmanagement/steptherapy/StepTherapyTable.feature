@ConfigureStepTherapy @epic=configure_Step_Therapy @configure_Step_Therapy @P20
Feature: Manage Step therapy tables

  As an administrator
  I should be able to view and search the step therapies in Step Therapy table

  @P1 @Automated
  Scenario: The user should be able to search Step Therapy by Name in step therapy table
    Given The user is on List Management Page
    Then  The user searches the Step Therapy Table by entering value Erythroid Stimulants in search bar
    Then  The user should validate the following row attributes and values for column Step Therapy Name and value Erythroid Stimulants
      | Step Therapy Name | Erythroid Stimulants                                           |
      | Step Therapy Type | Prerequisite                                                   |
      | Criteria          | New utilizer of 2nd line agent without trial of 1st line agent |

  @P1 @Automated #known issue
  Scenario Outline: The user should validate Step Therapy Names coming from database on searching Step Therapy by Name
    Given The user is on List Management Page
    Then  The user searches the Step Therapy Table by entering value <value> in search bar
    Then  The user validates the rows coming on searching Step Therapy table on column Step Therapy Name with value <value>
    Examples:
      | value     |
      | Androgens |
      | Oral      |

  @P1 @Automated
  Scenario Outline: The user should validate the error message on entering invalid characters in Step Therapy table Search bar
    Given The user is on List Management Page
    Then  The user searches the Step Therapy Table by entering value <characters> in search bar
    Then  The user should validate the below error message in Step therapy table
     """
     No records found.
     """
    Examples:
      | characters |
      | @          |
      | $$@        |

  @P1 @Automated #known issue
  Scenario: The user should be able to validate all the Step Therapy Names coming in Step Therapy table from database
    Given The user is on List Management Page
    Then  The user should validate all the Step Therapy names coming against column Step Therapy Name from database

  @P1 @Automated
  Scenario: User should validate search bar text in Step Therapy Page
    Given The user is on List Management Page
    Then  The user should validate following text coming in Step therapy page search bar
    """
    Search by Step Therapy Name
    """

  @P1 @Automated
  Scenario: The user should validate the title in Step Therapy Schedule Page
    Given The user is on List Management Page
    Then  The user searches the Step Therapy Table by entering value Erythroid Stimulants in search bar
    Then  The user clicks on view button for column Step Therapy Name and value Erythroid Stimulants in Step Therapy Table
    *     The Step Therapy Schedule section should be present in the Step Therapy Page
    *     The user validates the title Erythroid Stimulants on Step Therapy Page

  @P1 @Automated
  Scenario: The user validates table headers in Step Therapy Table
    Given The user is on List Management Page
    Then  The user should validate below table headers in Step Therapy table
      | Step Therapy Name |
      | Step Therapy Type |
      | Criteria          |