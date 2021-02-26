@AddNewContact @epic=Configure_Contact @Contact
Feature: Add A New Contact

  This feature allows the user to add a new contact for any client
  upon filling all mandatory fields successfully.

  @P1 @Automated @event=AddContact
  Scenario: The user should be able to add a new contact
    Given The user is on the client configuration page for client with code AP1920
    When  The user opts to add a new Contact
    *     The user updates the following fields with values on contacts overlay
          | Contact Type            | Clinical Consultant |
          | Contact Name            | Test01              |
          | Contact Organization    | Welldyne            |
          | Contact Structure       | Internal            |
          | Method of Communication | Email               |
          | Phone Number            | 9999922222          |
          | Email                   | Test@apple.com      |
          | Address Line 1          | Abc apartment       |
          | Address Line 2          | xyz road            |
          | City                    | FL                  |
          | State                   | Florida             |
          | Zip                     | 20120               |
    *     The user clicks on the submit button on the modal overlay
    Then  The user should be able to see the contact with field Contact Name and value Test01 in contacts section

  @P1 @Automated
  Scenario: The user should see an error message when user submits new contact form without Contact type field
    Given The user is on the client configuration page for client with code IN2020
    When  The user opts to add a new Contact
    *     The user updates the following fields with values on contacts overlay
          | Contact Name            | Test          |
          | Contact Organization    | Welldyne      |
          | Contact Structure       | Internal      |
          | Method of Communication | Email         |
          | Phone Number            | 9999922222    |
          | Email                   | Test@abc.com  |
          | Address Line 1          | Abc apartment |
          | Address Line 2          | xyz road      |
          | City                    | FL            |
          | State                   | Florida       |
          | Zip                     | 20120         |
    *     The user clicks on the submit button on the modal overlay to verify error message
    Then  The user should see the following error message on Contact Type field
          """
          Please select Contact Type
          """

  @P1 @Automated
  Scenario: The user should see an error message when user submits new contact form without Contact Name field
    Given The user is on the client configuration page for client with code IN2020
    When  The user opts to add a new Contact
    *     The user updates the following fields with values on contacts overlay
          | Contact Type            | Clinical Consultant |
          | Contact Organization    | Welldyne            |
          | Contact Structure       | Internal            |
          | Method of Communication | Email               |
          | Phone Number            | 9999922222          |
          | Email                   | Test@abc.com        |
          | Address Line 1          | Abc apartment       |
          | Address Line 2          | xyz road            |
          | City                    | FL                  |
          | State                   | Florida             |
          | Zip                     | 20120               |
    *     The user clicks on the submit button on the modal overlay to verify error message
    Then  The user should see the following error message on Contact Name field
          """
          Please enter Contact Name
          """

  @P1 @Automated
  Scenario: The user should see an error message when user submits new contact form without Contact Organisation field
    Given The user is on the client configuration page for client with code IN2020
    When  The user opts to add a new Contact
    *     The user updates the following fields with values on contacts overlay
          | Contact Type            | Clinical Consultant |
          | Contact Name            | Test                |
          | Contact Structure       | Internal            |
          | Method of Communication | Email               |
          | Phone Number            | 9999922222          |
          | Email                   | Test@abc.com        |
          | Address Line 1          | Abc apartment       |
          | Address Line 2          | xyz road            |
          | City                    | FL                  |
          | State                   | Florida             |
          | Zip                     | 11002               |
    *     The user clicks on the submit button on the modal overlay to verify error message
    Then  The user should see the following error message on Contact Organization field
          """
          Please select Contact Organization
          """

  @P1 @Automated
  Scenario: The user should see an error message when user submits new contact form without Contact Structure field
    Given The user is on the client configuration page for client with code IN2020
    When  The user opts to add a new Contact
    *     The user updates the following fields with values on contacts overlay
          | Contact Type            | Clinical Consultant |
          | Contact Name            | Test                |
          | Contact Organization    | Welldyne            |
          | Method of Communication | Email               |
          | Phone Number            | 9999922222          |
          | Email                   | Test@abc.com        |
          | Address Line 1          | Abc apartment       |
          | Address Line 2          | xyz road            |
          | City                    | FL                  |
          | State                   | Florida             |
          | Zip                     | 11002               |
    *     The user clicks on the submit button on the modal overlay to verify error message
    Then  The user should see the following error message on Contact Structure field
          """
          Please select Contact Structure
          """

  @P1 @Automated
  Scenario: The user should see an error message when user submits new contact form without Phone Number field
    Given The user is on the client configuration page for client with code IN2020
    When  The user opts to add a new Contact
    *     The user updates the following fields with values on contacts overlay
          | Contact Type            | Clinical Consultant |
          | Contact Name            | Test                |
          | Contact Structure       | Internal            |
          | Contact Organization    | Welldyne            |
          | Method of Communication | Phone Number        |
          | Email                   | Test@abc.com        |
          | Address Line 1          | Abc apartment       |
          | Address Line 2          | xyz road            |
          | City                    | FL                  |
          | State                   | Florida             |
          | Zip                     | 11002               |
    *     The user clicks on the submit button on the modal overlay to verify error message
    Then  The user should see the following error message on Phone Number field
          """
          Please enter Phone Number
          """

  @P1 @Automated
  Scenario: The user should see an error message when user submits new contact form without email field
    Given The user is on the client configuration page for client with code IN2020
    When  The user opts to add a new Contact
    *     The user updates the following fields with values on contacts overlay
          | Contact Type            | Clinical Consultant |
          | Contact Name            | Test                |
          | Contact Organization    | Welldyne            |
          | Contact Structure       | Internal            |
          | Method of Communication | Email               |
          | Phone Number            | 9999922222          |
          | Address Line 1          | Abc apartment       |
          | Address Line 2          | xyz road            |
          | City                    | FL                  |
          | State                   | Florida             |
          | Zip                     | 11002               |
    *     The user clicks on the submit button on the modal overlay to verify error message
    Then  The user should see the following error message on Email field
          """
          Please enter Email Address
          """

  @P1 @Automated
  Scenario: The user should see an error message when user submits new contact form without address field
    Given The user is on the client configuration page for client with code IN2020
    When  The user opts to add a new Contact
    *     The user updates the following fields with values on contacts overlay
          | Contact Type            | Clinical Consultant |
          | Contact Name            | Test                |
          | Contact Organization    | Welldyne            |
          | Contact Structure       | Internal            |
          | Method of Communication | Address             |
          | Email                   | Test@abc.com        |
          | Phone Number            | 9999922222          |
          | Address Line 2          | xyz road            |
          | City                    | FL                  |
          | State                   | Florida             |
          | Zip                     | 11002               |
    *     The user clicks on the submit button on the modal overlay to verify error message
    Then  The user should see the following error message on Address Line 1 field
          """
          Please enter Address line 1
          """

  @P1 @Automated
  Scenario: The user should see an error message when user submits new contact form without City field
    Given The user is on the client configuration page for client with code IN2020
    When  The user opts to add a new Contact
    *     The user updates the following fields with values on contacts overlay
          | Contact Type            | Clinical Consultant |
          | Contact Name            | Test                |
          | Contact Organization    | Welldyne            |
          | Contact Structure       | Internal            |
          | Method of Communication | Address             |
          | Phone Number            | 9999922222          |
          | Email                   | Test@abc.com        |
          | Address Line 1          | Abc apartment       |
          | Address Line 2          | xyz road            |
          | State                   | Florida             |
          | Zip                     | 11002               |
    *     The user clicks on the submit button on the modal overlay to verify error message
    Then  The user should see the following error message on City field
          """
          Please enter City
          """

  @P1 @Automated
  Scenario: The user should see an error message when user submits new contact form without State field
    Given The user is on the client configuration page for client with code IN2020
    When  The user opts to add a new Contact
    *     The user updates the following fields with values on contacts overlay
          | Contact Type            | Clinical Consultant |
          | Contact Name            | Test                |
          | Contact Organization    | Welldyne            |
          | Contact Structure       | Internal            |
          | Method of Communication | Address             |
          | Phone Number            | 9999922222          |
          | Email                   | Test@abc.com        |
          | Address Line 1          | Abc apartment       |
          | Address Line 2          | xyz road            |
          | City                    | FL                  |
          | Zip                     | 11002               |
    *     The user clicks on the submit button on the modal overlay to verify error message
    Then  The user should see the following error message on State field
          """
          Please enter State
          """

  @P1 @Automated
  Scenario: The user should see an error message when user submits new contact form without Zip field
    Given The user is on the client configuration page for client with code IN2020
    When  The user opts to add a new Contact
    *     The user updates the following fields with values on contacts overlay
          | Contact Type            | Clinical Consultant |
          | Contact Name            | Test                |
          | Contact Organization    | Welldyne            |
          | Contact Structure       | Internal            |
          | Method of Communication | Address             |
          | Phone Number            | 9999922222          |
          | Email                   | Test@abc.com        |
          | Address Line 1          | Abc apartment       |
          | Address Line 2          | xyz road            |
          | City                    | FL                  |
          | State                   | Florida             |
    *     The user clicks on the submit button on the modal overlay to verify error message
    Then  The user should see the following error message on Zip field
          """
          Please enter Zip code
          """

  @P2 @Automated
  Scenario Outline: A new contact should not be created when user cancels or closes the add new contact form
    Given The user is on the client configuration page for client with code IN2020
    When  The user opts to add a new Contact
    *     The user updates the following fields with values on contacts overlay
          | Contact Type            | Clinical Consultant |
          | Contact Name            | Test Cancel         |
          | Contact Organization    | Welldyne            |
          | Contact Structure       | Internal            |
          | Method of Communication | Email               |
          | Phone Number            | 9999922222          |
          | Email                   | Test@abc.com        |
          | Address Line 1          | Abc apartment       |
          | Address Line 2          | xyz road            |
          | City                    | FL                  |
          | State                   | Florida             |
          | Zip                     | 11002               |
    *     The user clicks on the <Action> button on the modal overlay
    Then  A contact with field Contact Name and value Test Cancel should not be present in the contacts section

    Examples:
      | Action |
      | Cancel |
      | Close  |