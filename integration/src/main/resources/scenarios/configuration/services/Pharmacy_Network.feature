@ConfigurePharmacyNetwork @epic=Configure_Pharmacy_Network

Feature: User Should Be able to add and update Pharmacy Network form

  As an administrator
  I should be able to add, delete and update Pharmacy Network in the services Page

  @P1 @Automated
  Scenario: User validates the form fields on the add new Pharmacy channel overlay
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Services tab in the configuration
    Then  The user opts to add new Pharmacy Network container
    *     The user should see the following fields on the add Pharmacy Network channel overlay
          | Network Name* |
          | Network Code* |
          | Description   |
          | NCPDP ID*     |

  @P1 @event=Checks_PharmacyNetwork @Automated
  Scenario: User creates new  pharmacy network
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Services tab in the configuration
    *     The user opts to add new Pharmacy Network container
    *     The user updates the form fields with value in Pharmacy network form
          | NCPDP ID     | 1234567                   |
          | Network Name | MyPersonalNetwork         |
          | Network Code | 1123                      |
          | Description  | My personal dummy network |
    *     The user clicks on the save button on the modal overlay
    Then Pharmacy network should be created with heading MyPersonalNetwork and with following attributes and values
          | NCPDP ID     | 1234567                   |
          | Network Code | 1123                      |
          | Description  | My personal dummy network |

  @P1 @Automated
  Scenario Outline: The user validates the error messages that are coming on keeping the form fields blank
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Services tab in the configuration
    *     The user opts to add new Pharmacy Network container
    *     The user clicks on the save button on the modal overlay to verify error message
    Then  The user should get following errors <Error Message> on field <Field>
    Examples:
      | Field        | Error Message                 |
      | Network Name | Please enter the network name |
      | Network Code | Please enter the network code |
      | NCPDP ID     | Please add a NCPDP code       |

  @P1 @event=updateFields @Automated
  Scenario:User updates fields in pharmacy network form
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Services tab in the configuration
    *     The user opts to update existing Pharmacy Network container Dummy
    *     The user clears the previous value in NCPDP ID field
    *     The user updates the form fields with value in Pharmacy network form
          | Network Name | MyPrivateNetwork         |
          | Network Code | 1111                     |
          | Description  | My private dummy network |
          | NCPDP ID     | 9876540                  |
    *     The user clicks on the save button on the modal overlay
    Then  Pharmacy network should be created with heading MyPrivateNetwork and with following attributes and values
          | NCPDP ID     | 9876540                  |
          | Network Code | 1111                     |
          | Description  | My private dummy network |

  @P1 @waitingFor=Checks_PharmacyNetwork @Automated
  Scenario:User deletes the pharmacy network container
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Services tab in the configuration
    *     The user tries to delete MyPersonalNetwork network container
    *     The user clicks on the confirm button on the delete modal
    Then  MyPersonalNetwork Pharmacy network should not be visible in the service page

  @P1 @Automated
  Scenario Outline:User Performs the cancel/close actions
    Given The user is on the client configuration page for client with code Auto_Apple
    When  The user navigates to the Services tab in the configuration
    *     The user tries to delete Pharmacy Network 1 network container
    *     The user clicks on the <Action> button on the delete modal
    Examples:
      | Action |
      | cancel |
      | close  |

  @P1 @Automated
  Scenario Outline: User verifies the title in the delete modal
    Given The user is on the client configuration page for client with code Auto_Apple
    When  The user navigates to the Services tab in the configuration
    *     The user tries to delete Pharmacy Network 1 network container
    *     The user should see the delete icon present in the delete modal
    Then  The user should see the <Section> with value as <Value> on the delete modal
    Examples:
      | Section     | Value                                                                                               |
      | title       | Are you sure?                                                                                       |
      | Description | Deleting Pharmacy Network 1 Network will remove its data completely. |

  @P1 @Automated
  Scenario:User tries to delete the dependent container
    Given The user is on the client configuration page for client with code Auto_Apple
    When  The user navigates to the Services tab in the configuration
    *     The user tries to delete Pharmacy Network 1 network container
    *     The user clicks on confirm button to verify errors
    Then  User should see following acknowledgement message on confirmation Page
    """
    You cannot delete Pharmacy Network 1 Network, there are channels associated to it. Please disassociate it from following channels.
    """

  @P1 @Automated
  Scenario Outline:User tries to update fields in new pharmacy network with invalid characters
    Given The user is on the client configuration page for client with code Auto_Apple
    When  The user navigates to the Services tab in the configuration
    *     The user opts to add new Pharmacy Network container
    *     The user updates the form fields with value in Pharmacy network form
          | Network Name | * |
          | Network Code | # |
    *     The user clicks on the save button on the modal overlay to verify error message
    Then  The user should get following errors <Error Message> on field <Field>
    Examples:
      | Field        | Error Message                                       |
      | Network Name | Network Name can only contain alphabets and numbers |
      | Network Code | Network Code can only contain alphabets and numbers |

  @P1 @Automated
  Scenario: User tries to update field ncpdp_id network with invalid characters
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Services tab in the configuration
    *     The user opts to add new Pharmacy Network container
    *     The user updates the form fields with value in Pharmacy network form
          | NCPDP ID | & |
    Then  The user should get following errors Only numbers are allowed on field NCPDP ID

  @P1 @Automated
  Scenario: User tries to add less than 7 digits in NCPDP_ID field
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Services tab in the configuration
    *     The user opts to add new Pharmacy Network container
    *     The user updates the form fields with value in Pharmacy network form
          | NCPDP ID | 1234 |
    Then  The user should get following errors Please enter 7 digit NCPDP ID on field NCPDP ID

  @P1 @Automated
  Scenario: User tries to add greater than 7 digits in NCPDP_ID field
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Services tab in the configuration
    *     The user opts to add new Pharmacy Network container
    *     The user updates the form fields with value in Pharmacy network form
          | NCPDP ID | 1234567890 |
    Then  The user should get following errors Please enter 7 digit NCPDP ID on field NCPDP ID

  @P1 @teardown @waitingFor=updateFields
  Scenario: Teardown method to update fields in pharmacy network form
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Services tab in the configuration
    *     The user opts to update existing Pharmacy Network container MyPrivateNetwork
    *     The user clears the previous value in NCPDP ID field
    *     The user updates the form fields with value in Pharmacy network form
          | Network Name | Dummy         |
          | Network Code | 2222          |
          | Description  | updated value |
          | NCPDP ID     | 2222222       |
    *     The user clicks on the save button on the modal overlay
    Then  Pharmacy network should be created with heading Dummy and with following attributes and values
          | NCPDP ID     | 2222222       |
          | Network Code | 2222          |
          | Description  | updated value |