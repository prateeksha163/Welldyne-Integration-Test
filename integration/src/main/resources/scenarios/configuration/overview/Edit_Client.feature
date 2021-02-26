@EditClient @epic=Configure_Client
Feature: Edit an existing client

  This feature allows the user to edit details of an existing client.

  @P1 @Automated
  Scenario: The user should be able to edit and save changes in Client information
    Given The user is on the client configuration page for client with code IN2020
    When  The user opts to update the client information in the Edit Client overlay
    *     The user updates the field Number Of Lives with value 100 in the Edit Client overlay
    *     The user clicks on the submit button on the modal overlay
    Then  The user should find the field Number Of Lives updated with value 100 in about section

  @P1 @Automated
  Scenario: User validates the form fields on the edit client overlay
    Given The user is on the client configuration page for client with code IN2020
    When  The user opts to update the client information in the Edit Client overlay
    Then  The user should see the following fields on the edit client overlay
          | Company Logo       |
          | Client Name*       |
          | Carrier ID*        |
          | Effective Date*    |
          | Term Date          |
          | External ID        |
          | Number of Lives    |
          | ACA Grandfathered* |

  @P1 @Automated
  Scenario Outline: The client information should not be updated if user opts to cancel the change
    Given The user is on the client configuration page for client with code IN2020
    When  The user opts to update the client information in the Edit Client overlay
    *     The user updates the field Carrier ID with value Test123 in the Edit Client overlay
    *     The user clicks on the <Action> button on the modal overlay
    Then  The user should find the field Carrier ID updated with value IN2020 in about section

    Examples:
      | Action |
      | Cancel |
      | Close  |

  @P2 @Automated
  Scenario: The user should see an error message when they try to clear the Client Name field
    Given The user is on the client configuration page for client with code IN2020
    When  The user opts to update the client information in the Edit Client overlay
    *     The user clears the field Client Name in the edit client overlay
    *     The user clicks on the submit button on the modal overlay to verify error message
    Then  The user should see the following error below the Client Name field
          """
          Please enter the client name
          """

  @P2 @Automated
  Scenario: The user should see an error message when they try to clear the Carrier ID field
    Given The user is on the client configuration page for client with code IN2020
    When  The user opts to update the client information in the Edit Client overlay
    *     The user clears the field Carrier ID in the edit client overlay
    *     The user clicks on the submit button on the modal overlay to verify error message
    Then  The user should see the following error below the Carrier ID field
          """
          Please enter the client code
          """

  @P2 @Automated
  Scenario: The user should see an error message when they try to clear the Effective Date field
    Given The user is on the client configuration page for client with code IN2020
    When  The user opts to update the client information in the Edit Client overlay
    *     The user clears the field Effective Date in the edit client overlay
    *     The user clicks on the submit button on the modal overlay to verify error message
    Then  The user should see the following error below the Effective Date field
          """
          Please enter the effective date
          """