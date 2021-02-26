@Dashboard @AddNewClient @Demo
Feature: Add A New Client

  As an administrator
  I should be able to add a new client on the dashboard
  So as I can start with their onboarding

  @P1
  Scenario: User should see the relevant form fields, and action buttons on the Add New Client form.
    Given The user is on Benefits Configurator Dashboard Page
    When  The user opts to add a new client on the dashboard page
    Then  The user should see the following fields and buttons on the add new client form
          | Client Logo             |
          | Client Name             |
          | Client Code             |
          | Effective Date          |
          | Cancel Button           |
          | Submit Button           |
          | Close Overlay Button    |

  @P2
  Scenario: User should by default see the first day of the next month selected in the effective date
    Given The user is on Benefits Configurator Dashboard Page
    When  The user opts to add a new client on the dashboard page
    Then  The form should open with first of next month selected as the effective date

  @P2
  Scenario Outline: User should be able to close the Add New Client form using cross button or cancel button
    Given The user is on Benefits Configurator Dashboard Page
    When  The user opts to add a new client on the dashboard page
    *     The user opts to click on the <Action> button on the add a new client form
    Then  The Add New Client form should not be visible on the dashboard page

    Examples:
      | Action  |
      | Cancel  |
      | Close   |

  @P2
  Scenario: User should see an error when they try to submit the form without entering Client Name
    Given The user is on Benefits Configurator Dashboard Page
    When  The user opts to add a new client on the dashboard page
    *     The form opens with first of next month selected as the effective date
    *     The user updates the following fields with values
          | Field             | Value         |
          | Client Code       | J1101S        |
    *     The user opts to click on the Submit button on the add a new client form
    Then  The user should see the following error set on the add a new client form
          | Please enter the client name       |

  @P2
  Scenario: User should see an error when they try to submit the form without entering Client Code
    Given The user is on Benefits Configurator Dashboard Page
    When  The user opts to add a new client on the dashboard page
    *     The form opens with first of next month selected as the effective date
    *     The user updates the following fields with values
          | Field             | Value              |
          | Client Name       | Manchester United  |
    *     The user opts to click on the Submit button on the add a new client form
    Then  The user should see the following error set on the add a new client form
          | Please enter the carrier id           |

  @P2
  Scenario: User should be shown multiple errors in case they try to submit the Add New Client form blank
    Given The user is on Benefits Configurator Dashboard Page
    When  The user opts to add a new client on the dashboard page
    *     The form opens with first of next month selected as the effective date
    *     The user clears the effective date field on the add a new client form
    *     The user opts to click on the Submit button on the add a new client form
    Then  The user should see the following error set on the add a new client form
          | Please enter the client name        |
          | Please enter the carrier id         |
          | Please enter client effective date  |

  @P2
  Scenario: User should see an error when they try to submit the form with incorrect date format
    Given The user is on Benefits Configurator Dashboard Page
    When  The user opts to add a new client on the dashboard page
    *     The form opens with first of next month selected as the effective date
    *     The user updates the following fields with values
          | Field             | Value              |
          | Client Name       | Manchester United  |
          | Client Code       | J1101S             |
          | Effective Date    | 22-07-2023         |
    *     The user opts to click on the Submit button on the add a new client form
    Then  The user should see the following error set on the add a new client form
          | Please enter correct date format       |

  @P1
  Scenario Outline: User should see an error in case they upload an incorrect image
    Given The user is on Benefits Configurator Dashboard Page
    When  The user opts to add a new client on the dashboard page
    *     User uploads an incorrect image file "<Image>" on the add a new client form
    Then  The user should see the error for the incorrect image on the add a new client form
          """
          The client logo's size should not be more than 5MB. The minimum resolution should be 300px x 300px and the maximum resolution should be 1200px x 1200px. The aspect ratio should be 1:1 and the supported file formats are .png and .jpg
          """
    Examples:
      | Image                                                 |
      | logo_images/MAN_U_LOGO_PNG_20X20.png                  |
      | logo_images/LIFE_IS_MUSIC_LOGO_PNG_1300X1300.png      |

  @P1
  Scenario: User should see an error message in case of existing carrier ID
    Given The user is on Benefits Configurator Dashboard Page
    When  The user opts to add a new client on the dashboard page
    *     The form opens with first of next month selected as the effective date
    *     The user updates the following fields with values
          | Field             | Value                            |
          | Logo              | logo_images/BMW_400x400_LOGO.jpg |
          | Client Name       | BMW                              |
          | Client Code       | BMW0001                          |
    *     The user opts to click on the Submit button on the add a new client form
    *     The user should see the following error set on the add a new client form
          | Carrier ID already exists please enter a unique code.       |

  @P1
  Scenario: User should be able to add a new client by adding all the relevant information
    Given The user is on Benefits Configurator Dashboard Page
    When  The user opts to add a new client on the dashboard page
    *     The form opens with first of next month selected as the effective date
    *     The user updates the following fields with values
          | Field             | Value                            |
          | Logo              | logo_images/BMW_400x400_LOGO.jpg |
          | Client Name       | BMW 3                            |
          | Client Code       | BMW0003                          |
    *     The user clicks on the Submit button on the modal overlay
    *     The user search the dashboard using correct client code or name BMW0002
    Then  The user should see exactly 1 client cards on the dashboard page
    *     The user should find client listing with names BMW 3 on the dashboard page