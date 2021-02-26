@ConfigurePAResponsibility
Feature: PA Responsibility in Plan Attributes

  @P1 @Automated
  Scenario: The PA responsibility in Plan attributes should by default show all the attributes for WellDyneRx Reviews
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plan Attribute tab in the configuration
    Then  The user should see the the following value against WellDyneRx Reviews attribute on the PA Responsibility section
      | Vacation supply up to 90 days        |
      | Vacation supply greater than 90 days |
      | Increased dosage                     |

  @P1 @Automated
  Scenario: The PA responsibility in Plan attributes should by default show all the attributes for WellDyneRx initiates PA
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plan Attribute tab in the configuration
    Then  The user should see the the following value against WellDyneRx initiates PA attribute on the PA Responsibility section
      | Lost medication        |
      | PA appeals (1st Level) |
      | Age related rules      |
      | Step Therapy           |

  @P1 @Automated
  Scenario: The PA responsibility in Plan attributes should by default show all the attributes for WellDyneRx will direct to PA Approver
    Given The user is on the client configuration page for client with code BMW0002
    When  The user navigates to the Plan Attribute tab in the configuration
    Then  The user should see the the following value against WellDyneRx will direct to PA Approver attribute on the PA Responsibility section
      | Refill-too-soon                                                              |
      | Maximum claim dollar exceeds amount                                          |
      | Medical Exception (Formulary exclusion review allowed to determine coverage) |
      | PA appeals (2nd Level)                                                       |
      | Clinical PAs (PA Required)                                                   |

  @P1 @event=Checks_responsibility_Yes @Automated
  Scenario: The user should have the ability to update PA Responsibility form
    Given The user is on the client configuration page for client with code BMW0002
    *     The user navigates to the Plan Attribute tab in the configuration
    When  The user opts to update the PA Responsibility configuration in the PA Responsibility tab
    *     The user selects the following attributes in the PA Responsibility section with value WellDyneRx Reviews
      | Refill Too Soon                     |
      | Maximum Claim Dollar Exceeds Amount |
    *     The user clicks on the Submit button on the modal overlay
    Then  The user should see the the following value against WellDyneRx Reviews attribute on the PA Responsibility section
      | Vacation supply up to 90 days        |
      | Vacation supply greater than 90 days |
      | Refill-too-soon                      |
      | Maximum claim dollar exceeds amount  |
      | Increased dosage                     |

  @P1 @Automated
  Scenario: User validates the title information on the Prior Authorization Rule Responsibility Tab
    Given The user is on the client configuration page for client with code BMW0002
    *     The user navigates to the Plan Attribute tab in the configuration
    When  The user clicks on the information button
    Then  The user validates the title of the popover
      | WellDyneRx Initiates PA               |
      | WellDyneRx will direct to PA approver |

  @P1 @Automated
  Scenario: User validates the section title information on the Prior Authorization Rule Responsibility Tab
    Given The user is on the client configuration page for client with code BMW0002
    *     The user navigates to the Plan Attribute tab in the configuration
    When  The user clicks on the information button
    Then  The user validates the section title of the popover
      | WelldyneRx Will |
      | Approver Will   |
      | WelldyneRx Will |
      | Approver Will   |

  @P1 @Automated
  Scenario: User validates the entire information on the Prior Authorization Rule Responsibility Tab
    Given The user is on the client configuration page for client with code BMW0002
    *     The user navigates to the Plan Attribute tab in the configuration
    When  The user clicks on the information button
    Then  The user validates the Information of the popover
      | - Initiate the PA                                                                     |
      | - Provide approver with letter of medical necessity and claim information             |
      | - Provide PA entry services                                                           |
      | - Pharmacy communication                                                              |
      | - Conduct PA determinations                                                           |
      | - Follow-up and communicate final determinations to physician                         |
      | - Notify patient of determination and appeals process                                 |
      | - via the claim system, direct the pharmacy to contact the approver for PA processing |
      | - Provide complete PA processing and appeals services, including all communications   |
      | - Have access to the claim system for PA entry (provided at no additional cost)       |

  @P2 @Automated
  Scenario Outline: On clicking Cancel/Close button PA Responsibility model should not be visible
    Given The user is on the client configuration page for client with code BMW0002
    *     The user navigates to the Plan Attribute tab in the configuration
    When  The user opts to update the PA Responsibility configuration in the PA Responsibility tab
    *     The user opts to click on the <Action> button on the add a new client form
    Then  The PA Responsibility form should not be visible on the dashboard page
    Examples:
      | Action |
      | Cancel |
      | Close  |


  @P1 @teardown @waitingFor=Checks_responsibility_Yes @Automated
  Scenario: Cleanup scenario to keep Plan Attribute form fields to default condition
    Given The user is on the client configuration page for client with code BMW0002
    *     The user navigates to the Plan Attribute tab in the configuration
    When  The user opts to update the PA Responsibility configuration in the PA Responsibility tab
    *     The user selects the following attributes in the PA Responsibility section with value WellDyneRx will direct to PA Approver
      | Refill Too Soon                     |
      | Maximum Claim Dollar Exceeds Amount |
    *     The user clicks on the Submit button on the modal overlay
    Then  The user should see the the following value against WellDyneRx will direct to PA Approver attribute on the PA Responsibility section
      | Refill-too-soon                                                              |
      | Maximum claim dollar exceeds amount                                          |
      | Medical Exception (Formulary exclusion review allowed to determine coverage) |
      | PA appeals (2nd Level)                                                       |
      | Clinical PAs (PA Required)                                                   |