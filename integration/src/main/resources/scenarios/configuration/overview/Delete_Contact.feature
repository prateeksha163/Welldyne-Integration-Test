@DeleteContact @epic=Configure_Contact @Contact
Feature: Delete an existing contact

  This feature allows the user to delete a contact for any client.

  @P1 @Automated @waitingFor=AddContact
  Scenario: The user should be able to delete an existing contact
    Given The user is on the client configuration page for client with code AP1920
    When  The user opts to delete an existing Contact with field Contact Name and value Test01
    *     The user clicks on the Confirm button on the delete modal
    Then  A contact with field Contact Name and value Test01 should not be present in the contacts section

  @P2 @Automated
  Scenario: User should be able to cancel the delete option
    Given The user is on the client configuration page for client with code AP1920
    When  The user opts to delete an existing Contact with field Contact Name and value Test Cancel
    *     The user clicks on the cancel button on the delete modal
    Then  The user should be able to see the contact with field Contact Name and value Test Cancel in contacts section

  @P2 @Automated
  Scenario Outline: Description of the delete contact modal should be present in the delete alert box
    Given The user is on the client configuration page for client with code AP1920
    When  The user opts to delete an existing Contact with field Contact Name and value Test Cancel
    Then  The user should see the <Field> with value as <Value> on the delete modal
    Examples:
      | Field       | Value                                                                                 |
      | Description | If you delete Clinical Consultant, the details for this contact type will be deleted. |
      | Title       | Are you sure?                                                                         |

  @P2 @Automated
  Scenario: An error image should be present in the delete alert box
    Given The user is on the client configuration page for client with code AP1920
    When  The user opts to delete an existing Contact with field Contact Name and value Test Cancel
    Then  The user should see the delete icon present in the delete modal