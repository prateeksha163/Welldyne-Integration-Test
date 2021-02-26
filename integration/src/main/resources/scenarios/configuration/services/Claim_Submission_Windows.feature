@claim_submission_windows @epic=Configure_Claims_Submission_Windows
Feature: User Should Add and Update claim submission windows form

  @P1 @Automated @event=UpdateFields
  Scenario: The user should validate the attributes and values on updating Allow Manual Claims to No
    Given The user is on the client configuration page for client with code Auto_Apple
    *     The user navigates to the services tab in the configuration
    When  The user opts to update the claim submission window configuration in the services tab
    *     The user updates the following fields with their respective values on claim submission form
      | Rx Fill Submit Window    | 12 |
      | Rx Fill Re-Submit Window | 30 |
      | Rx Fill Window           | 25 |
      | Allow Manual Claims      | No |
    *     The user clicks on the submit button on the modal overlay
    *     The user should see the following values against given attributes on the claim submission windows section
      | Rx Fill Window           | 25 Day(s) |
      | Rx Fill Submit Window    | 12 Day(s) |
      | Rx Fill Re-Submit Window | 30 Day(s) |
      | Allow Manual Claims      | No        |
      | Allow E1 Transaction     | Yes       |

  @P1 @Automated
  Scenario Outline: user should get respective error msg on passing invalid character in the form field
    Given The user is on the client configuration page for client with code BMW0002
    *     The user navigates to the services tab in the configuration
    When  The user opts to update the claim submission window configuration in the services tab
    Then  The user updates the field <Field> with invalid character <Value> on claim submission form
    *     The user clicks on the submit button on the modal overlay to verify error message
    Then  The user should get the following errors Only numbers are allowed on field <Field> in claim submission form
    Examples:
      | Field                      | Value |
      | Rx Fill Window             | @     |
      | Rx Fill Submit Window      | j     |
      | Rx Fill Re-Submit Window   | +     |
      | Manual Claim Submit Window | .     |

  @P1 @event=UpdateManualClaims @Automated @waitingFor=UpdateFields
  Scenario: The user should validate the attributes and values on updating Claim Submission Windows Form
    Given The user is on the client configuration page for client with code Auto_Apple
    *     The user navigates to the services tab in the configuration
    When  The user opts to update the claim submission window configuration in the services tab
    *     The user updates the following fields with their respective values on claim submission form
      | Rx Fill Submit Window      | 12  |
      | Rx Fill Re-Submit Window   | 30  |
      | Rx Fill Window             | 25  |
      | Allow Manual Claims        | Yes |
      | Manual Claim Submit Window | 90  |
    *     The user clicks on the submit button on the modal overlay
    *     The user should see the following values against given attributes on the claim submission windows section
      | Rx Fill Window             | 25 Day(s) |
      | Rx Fill Submit Window      | 12 Day(s) |
      | Rx Fill Re-Submit Window   | 30 Day(s) |
      | Manual Claim Submit Window | 90 Day(s) |
      | Allow Manual Claims        | Yes       |
      | Allow E1 Transaction       | Yes       |

  @P1 @Automated
  Scenario Outline: The user gets the error messages that are coming on keeping the form fields blank
    Given The user is on the client configuration page for client with code BMW0002
    *     The user navigates to the services tab in the configuration
    When  The user opts to update the claim submission window configuration in the services tab
    Then  The user clears all the form fields
      | Rx Fill Window             |
      | Rx Fill Submit Window      |
      | Rx Fill Re-Submit Window   |
      | Manual Claim Submit Window |
    *     The user clicks on the submit button on the modal overlay to verify error message
    Then  The user should get the following errors <errorMessage> on field <Field> in claim submission form
    Examples:
      | Field                      | errorMessage                                |
      | Rx Fill Window             | Please enter the Rx Fill Window             |
      | Rx Fill Submit Window      | Please enter the Rx Fill Submit Window      |
      | Manual Claim Submit Window | Please enter the Manual Claim Submit Window |

  @P1 @Automated @waitingFor=UpdateManualClaims
  Scenario: User should see all the fields in claim submission window form
    Given The user is on the client configuration page for client with code Auto_Apple
    *     The user navigates to the services tab in the configuration
    When  The user opts to update the claim submission window configuration in the services tab
    Then  The user must see the following form fields in claim submission window configuration
      | Rx Fill Submit Window*      |
      | Rx Fill Re-Submit Window    |
      | Rx Fill Window*             |
      | Manual Claim Submit Window* |
      | Allow Manual Claims*        |
      | Allow E1 Transaction        |

  @P1 @Automated
  Scenario: User should validate the disabled fields in claim submission form
    Given The user is on the client configuration page for client with code BMW0002
    *     The user navigates to the services tab in the configuration
    When  The user opts to update the claim submission window configuration in the services tab
    *     The user should see the following fields as disabled in claim submission form
      | Allow E1 Transaction |

  @P1 @Automated
  Scenario: User should validate the default value coming in Allow E1 Transaction field
    Given The user is on the client configuration page for client with code BMW0002
    *     The user navigates to the services tab in the configuration
    When  The user opts to update the claim submission window configuration in the services tab
    *     The user validates value coming in Allow E1 Transaction should be by default Yes

  @P1 @Automated
  Scenario: Checking visibility condition of Manual Claim Submit Window field
    Given The user is on the client configuration page for client with code BMW0002
    *     The user navigates to the services tab in the configuration
    When  The user opts to update the claim submission window configuration in the services tab
    *     The user updates the field Allow Manual Claims with value No on claim submission form
    Then  Manual Claim Submit Window field should not be visible on claim submission form

  @P1 @Automated
  Scenario Outline: On clicking Cancel/Close button Claim submission window model should not be visible
    Given The user is on the client configuration page for client with code Auto_Apple
    *     The user navigates to the services tab in the configuration
    When  The user opts to update the claim submission window configuration in the services tab
    *     The user clicks on the <Action> button on the modal overlay to verify error message
    Examples:
      | Action |
      | Cancel |
      | Close  |