@DashboardSearch @Dashboard @Demo
Feature: Dashboard Search

  @P1
  Scenario Outline: A user should be able to search for the client using client name or client code.
    Given The user is on Benefits Configurator Dashboard Page
    When  The user search the dashboard using correct client code or name <Client_Code_Or_Name>
    Then  The user should see exactly <Entries> client cards on the dashboard page
    And   The user should find client listing with names <Client_Names> on the dashboard page

    Examples:
      | Client_Code_Or_Name | Entries | Client_Names              |
      | Clynx               | 1       | Clynx                     |
      | HealthK             | 3       | Healthkart, HealthKart Part1, HealthKarts   |
      | GHT6574             | 1       | Starbucks@star            |
      | HG6                 | 5       | Man U, ABC@, CCDH, Chrome, mew |
 #     | kart                | 2       | Healthkart, HealthKart@   |
 #     | @                   | 2       | ABC@, HealthKart@         |

  @P2
  Scenario: A user should be shown an error with title and description in case the search result fetches nothing.
    Given The user is on Benefits Configurator Dashboard Page
    When  The user search the dashboard using incorrect client code or name Random Client
    Then  An error title should be displayed as "No results available"
    And   An error description should be displayed as "We can't find any results based on your search or filter"

  @P3
  Scenario: Validate the place holder text for the search box
    Given The user is on Benefits Configurator Dashboard Page
    Then  They should see the placeholder text in the search box as
          """
          Search by Client name or Client code
          """