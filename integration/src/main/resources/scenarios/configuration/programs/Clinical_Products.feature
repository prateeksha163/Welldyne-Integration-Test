@ConfigureClinicalProducts @epic=Clinical_Products_Programs

Feature: Edit Clinical Products Programs section in Programs

  As an administrator
  I should be able to Edit and update fields in Clinical Products Programs in Programs

  @P1 @Automated @event=Checks_Programs
  Scenario: The configuration should by default show all the programs opted out
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Programs tab in the configuration
    Then  The user should see the the following value against given programs on the Clinical Products Programs section
      | WM Brands                                        | No |
      | WM Generics                                      | No |
      | WM HyperInflationary                             | No |
      | WM Diabetes                                      | No |
      | WM Opioids                                       | No |
      | WM Respiratory                                   | No |
      | WM Heart Failure                                 | No |
      | WellAssist                                       | No |
      | Custom Clinical Programs for in House Pharmacies | No |

  @P1 @Automated
  Scenario: The user should validate all the form fields in Clinical Products Form
    Given The user is on the client configuration page for client with code Auto_Apple
    When  The user navigates to the Programs tab in the configuration
    When  The user opts to update the Clinical Products Programs configuration in the Programs tab
    Then  The user should see the following sections in Clinical Products form
      | Select Clinical Products |
      | Notes                    |

  @P1 @Automated
  Scenario: The user should validate all the form fields in Select Clinical Products in Managed Program Form
    Given The user is on the client configuration page for client with code Auto_Apple
    When  The user navigates to the Programs tab in the configuration
    When  The user opts to update the Clinical Products Programs configuration in the Programs tab
    Then  The user should see the following checkbox fields in Select Clinical Products section
      | WM Brands                                        |
      | WM Generics                                      |
      | WM HyperInflationary                             |
      | WM Diabetes                                      |
      | WM Opioids                                       |
      | WM Respiratory                                   |
      | WM Heart Failure                                 |
      | WellAssist                                       |
      | Custom Clinical Programs for in House Pharmacies |


  @P1 @Automated @event=Update_Fields @waitingFor=Checks_Programs
  Scenario: The user should update the form fields in Clinical Products
    Given The user is on the client configuration page for client with code BMW0002
    And   The user navigates to the Programs tab in the configuration
    When  The user opts to update the Clinical Products Programs configuration in the Programs tab
    And   The user opts in the following programs in the Select Clinical Products section
      | WM Brands                                        |
      | WM Generics                                      |
      | WM HyperInflationary                             |
      | WM Diabetes                                      |
      | WM Opioids                                       |
      | WM Respiratory                                   |
      | WM Heart Failure                                 |
      | WellAssist                                       |
      | Custom Clinical Programs for in House Pharmacies |
    Then The user updates the Notes field with following description in Clinical Products Form
       """
       The science or practice of the diagnosis, treatment, and prevention of disease (in technical use often taken to exclude surgery).
       """
    And   The user clicks on the Submit button on the modal overlay
    Then  The user should see the value against the following programs updated to Yes on the Clinical Products Programs section
      | WM Brands                                        | Yes                                                                                                                               |
      | WM Generics                                      | Yes                                                                                                                               |
      | WM HyperInflationary                             | Yes                                                                                                                               |
      | WM Diabetes                                      | Yes                                                                                                                               |
      | WM Opioids                                       | Yes                                                                                                                               |
      | WM Respiratory                                   | Yes                                                                                                                               |
      | WM Heart Failure                                 | Yes                                                                                                                               |
      | WellAssist                                       | Yes                                                                                                                               |
      | Custom Clinical Programs for in House Pharmacies | Yes                                                                                                                               |
      | Notes                                            | The science or practice of the diagnosis, treatment, and prevention of disease (in technical use often taken to exclude surgery). |

  @P1 @Automated
  Scenario: The Notes section should not be visible when user opts out of The program 'Custom Clinical Programs for in House Pharmacies'
    Given The user is on the client configuration page for client with code Auto_Apple
    And   The user navigates to the Programs tab in the configuration
    When  The user opts to update the Clinical Products Programs configuration in the Programs tab
    And   The user opts out of the following programs in the Select Clinical Products section
      | Custom Clinical Programs for in House Pharmacies |
    Then The Notes section should not be visible in Clinical Programs Section

  @P1 @teardown @Automated
  Scenario: The user should update the form fields in Clinical Products to initial state for cleanup purpose
    Given The user is on the client configuration page for client with code BMW0002
    And   The user navigates to the Programs tab in the configuration
    When  The user opts to update the Clinical Products Programs configuration in the Programs tab
    And   The user opts out of the following programs in the Select Clinical Products section
      | WM Brands                                        |
      | WM Generics                                      |
      | WM HyperInflationary                             |
      | WM Diabetes                                      |
      | WM Opioids                                       |
      | WM Respiratory                                   |
      | WM Heart Failure                                 |
      | WellAssist                                       |
      | Custom Clinical Programs for in House Pharmacies |
    And   The user clicks on the Submit button on the modal overlay
    Then  The user should see the value against the following programs updated to Yes on the Clinical Products Programs section
      | WM Brands                                        | No |
      | WM Generics                                      | No |
      | WM HyperInflationary                             | No |
      | WM Diabetes                                      | No |
      | WM Opioids                                       | No |
      | WM Respiratory                                   | No |
      | WM Heart Failure                                 | No |
      | WellAssist                                       | No |
      | Custom Clinical Programs for in House Pharmacies | No |