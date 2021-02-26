@ClientOverview
Feature: Client Overview Home

  @P1 @Automated
  Scenario: The About sub-section should be present on the Overview Page
    Given The user is on the client configuration page for client with code AP1920
    Then  The user should see the About sub-section on the Overview Page

  @P1 @Automated
  Scenario: The user should see all the client information in About section
    Given The user is on the client configuration page for client with code AP1920
    Then  The user should see the following fields with values in the About section
      | Client Name       | Auto_Apple |
      | Carrier ID        | AP1920     |
      | Effective Date    | 08-01-2020 |
      | Term Date         | 07-01-2021 |
      | External ID       | AP1001     |
      | Number of Lives   | 100        |
      | ACA Grandfathered | Yes        |

  @P1 @Automated
  Scenario: The Contacts sub-section should be present on the Overview Page
    Given The user is on the client configuration page for client with code AP1920
    Then  The user should see the Contacts sub-section on the Overview Page


