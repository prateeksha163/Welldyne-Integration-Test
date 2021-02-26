@EditContact @epic=Configure_Contact @Contact
Feature: Edit an existing contact

  This feature allows the user to edit an existing contact.

  @P1 @Automated @event=EditContact
  Scenario: The user should be able to edit an existing contact
    Given The user is on the client configuration page for client with code IN2020
    When  The user opts to edit an existing Contact with field Contact Name and value Test Edit
    *     The user updates the field Contact Name with value Test Edit Pass on contacts overlay
    *     The user clicks on the submit button on the modal overlay
    Then  The user should be able to see the contact with field Contact Name and value Test Edit Pass in contacts section

  @P2 @Automated
  Scenario: An error message must be displayed when contact name field is left blank
    Given The user is on the client configuration page for client with code IN2020
    When  The user opts to edit an existing Contact with field Contact Name and value Test Phone
    *     The user clears the field Contact Name in the edit contact overlay
    *     The user clicks on the submit button on the modal overlay to verify error message
    Then  The user should see the following error message on Contact Name field
          """
          Please enter Contact Name
          """

  @P2 @Automated
  Scenario: An error message must be displayed when email field is left blank
    Given The user is on the client configuration page for client with code IN2020
    When  The user opts to edit an existing Contact with field Contact Name and value Test Email
    *     The user clears the field email in the edit contact overlay
    *     The user clicks on the submit button on the modal overlay to verify error message
    Then  The user should see the following error message on email field
          """
          Please enter Email Address
          """

  @P2 @Automated
  Scenario: An error message must be displayed when phone number field is left blank
    Given The user is on the client configuration page for client with code IN2020
    When  The user opts to edit an existing Contact with field Contact Name and value Test Phone
    *     The user clears the field phone number in the edit contact overlay
    *     The user clicks on the submit button on the modal overlay to verify error message
    Then  The user should see the following error message on phone number field
          """
          Please enter Phone Number
          """

  @P2 @Automated
  Scenario: An error message must be displayed when address field is left blank
    Given The user is on the client configuration page for client with code IN2020
    When  The user opts to edit an existing Contact with field Contact Name and value Test Address
    *     The user clears the field address line 1 in the edit contact overlay
    *     The user clicks on the submit button on the modal overlay to verify error message
    Then  The user should see the following error message on address line 1 field
          """
          Please enter Address line 1
          """

  @P2 @Automated
  Scenario: An error message must be displayed when city field is left blank
    Given The user is on the client configuration page for client with code IN2020
    When  The user opts to edit an existing Contact with field Contact Name and value Test Address
    *     The user clears the field city in the edit contact overlay
    *     The user clicks on the submit button on the modal overlay to verify error message
    Then  The user should see the following error message on city field
          """
          Please enter City
          """

  @P2 @Automated
  Scenario: An error message must be displayed when zip code field is left blank
    Given The user is on the client configuration page for client with code IN2020
    When  The user opts to edit an existing Contact with field Contact Name and value Test Address
    *     The user clears the field zip in the edit contact overlay
    *     The user clicks on the submit button on the modal overlay to verify error message
    Then  The user should see the following error message on zip field
          """
          Please enter Zip code
          """

  @P2 @Automated
  Scenario Outline: An error message must be displayed when format of phone number field is not valid
    Given The user is on the client configuration page for client with code IN2020
    When  The user opts to edit an existing Contact with field Contact Name and value Test Address
    *     The user updates the field <Field value> with value <Value> on contacts overlay
    *     The user clicks on the submit button on the modal overlay to verify error message
    Then  The user should see the following error message on phone number field
          """
          Please enter correct Phone Format
          """
    Examples:
      | Field value  | Value       |
      | Phone number | 777         |
      | Phone number | 77788899900 |
      | Phone number | test        |

  @P2 @Automated
  Scenario Outline: An error message must be displayed when format of email field is not valid
    Given The user is on the client configuration page for client with code IN2020
    When  The user opts to edit an existing Contact with field Contact Name and value Test Address
    *     The user updates the field <Field value> with value <Value> on contacts overlay
    *     The user clicks on the submit button on the modal overlay to verify error message
    Then  The user should see the following error message on email field
          """
          Please enter correct Email Format
          """
    Examples:
      | Field value | Value     |
      | email       | test      |
      | email       | test@com  |
      | email       | test@com. |

  @P2 @Automated
  Scenario Outline: An error message must be displayed when format of email field is not valid
    Given The user is on the client configuration page for client with code IN2020
    When  The user opts to edit an existing Contact with field Contact Name and value Test Address
    *     The user updates the field <Field value> with value <Value> on contacts overlay
    *     The user clicks on the submit button on the modal overlay to verify error message
    Then  The user should see the following error message on zip field
          """
          Please enter correct Zip code Format
          """
    Examples:
      | Field value | Value  |
      | zip         | 200    |
      | zip         | 201100 |
      | zip         | test   |

  @P2 @Automated
  Scenario Outline: Verify that on changing the method of communication the mandatory fields should change
    Given The user is on the client configuration page for client with code IN2020
    When  The user opts to edit an existing Contact with field Contact Name and value <Contact Name>
    *     The user updates the field method of communication with value <MOCvalue> on contacts overlay
    *     The user clicks on the submit button on the modal overlay to verify error message
    Then  The user should see the following error message on field <Field> : <ErrorMsg> in contacts modal
    Examples:
      | Contact Name | MOCvalue | Field          | ErrorMsg                    |
      | Test Phone   | email    | email          | Please enter Email Address  |
      | Test Phone   | address  | address line 1 | Please enter Address line 1 |
      | Test Phone   | address  | city           | Please enter City           |
      | Test Phone   | address  | state          | Please enter State          |
      | Test Phone   | address  | zip            | Please enter Zip code       |
      | Test Address | phone    | phone number   | Please enter Phone Number   |


  @TearDown @waitingFor=EditContact
  Scenario: Restoring data changes in the above script
    Given The user is on the client configuration page for client with code IN2020
    When  The user opts to edit an existing Contact with field Contact Name and value Test Edit Pass
    *     The user updates the field Contact Name with value Test Edit on contacts overlay
    *     The user clicks on the submit button on the modal overlay
    Then  The user should be able to see the contact with field Contact Name and value Test Edit in contacts section