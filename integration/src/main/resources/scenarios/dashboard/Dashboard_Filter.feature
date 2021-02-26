@DashboardFilter @Dashboard @Demo
Feature: Dashboard Filter

  @P1 @Automated
  Scenario: A user should be able to see all the filters on the dashboard page
    Given The user is on Benefits Configurator Dashboard Page
    Then  The user should be able to see all the following filters
      | Stage          |
      | Effective Date |

  @P1 @Automated
  Scenario Outline: A user should be able to filter the client listing on the basis of stage value selected
    Given The user is on Benefits Configurator Dashboard Page
    When  The user selects the filter value <Filter Value> from the filter with name <Filter Name>
    Then  The user should find the client listing as per the stage filter criteria <Filter Value>

    Examples:
      | Filter Value   | Filter Name |
      | Edit           | Stage       |
      | Edit, Sign Off | Stage       |

  @P1 @Automated
  Scenario Outline: A user should be able to filter the client listing on the basis of Effective date value selected
    Given The user is on Benefits Configurator Dashboard Page
    When  The user selects the filter value <Filter_value> from the filter with name <Filter_name>
    Then  The user should find client listing with names <Client_Names> on the dashboard page

    Examples:
      | Filter_value | Filter_name    | Client_Names |
      | 07-13-2020   | Effective Date | Auto_Apple   |

  @P1 @Automated
  Scenario Outline: A user should be able to filter the client listing on the basis of multiple filters selected
    Given The user is on Benefits Configurator Dashboard Page
    When  The user selects the filter value <Stage_Filter_value> from the filter with name <Stage_Filter_name>
    And   The user selects the filter value <Date_Filter_value> from the filter with name <Date_Filter_name>
    Then  The user should find client listing with names <Client Name> on the dashboard page

    Examples:
      | Stage_Filter_value | Stage_Filter_name | Date_Filter_value | Date_Filter_name | Client Name   |
      | Edit               | Stage             | 07-13-2020        | Effective Date   | Auto_Apple    |


  @P1 @Automated
  Scenario Outline: An appropriate message should be displayed to the user in case multiple filters fetch no results
    Given The user is on Benefits Configurator Dashboard Page
    When  The user selects the filter value <Stage_Filter_value> from the filter with name <Stage_Filter_name>
    And   The user selects the filter value <Date_Filter_value> from the filter with name <Date_Filter_name>
    Then  An error title should be displayed as "No results available"
    And   An error description should be displayed as "We can't find any results based on your search or filter"

    Examples:
      | Stage_Filter_value | Stage_Filter_name | Date_Filter_value | Date_Filter_name |
      | Sign Off           | Stage             | 07-13-2020        | Effective Date   |

  @P2 @Automated
  Scenario Outline: A user should be able to clear the filter on the client listing
    Given The user is on Benefits Configurator Dashboard Page
    When  The user selects the filter value <Filter_value> from the filter with name <Filter_name>
    And   The user clears the filter with name <Filter_name>
    Then  The clear button should be removed from the filter <Filter_name>

    Examples:
      | Filter_value  | Filter_name    |
      | Edit          | Stage          |
      | Edit, Publish | Stage          |
      | 07-13-2020    | Effective Date |