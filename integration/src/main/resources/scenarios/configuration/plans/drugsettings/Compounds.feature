@ConfigureCompounds
Feature: Configure a Compound in Drug Settings page

  @P1 @Automated
  Scenario: User should see the Compounds section present
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH003
    *     The user navigates to the Drug Settings tab in the plans page
    Then  The Compounds section should be present on the Plans page

  @P1
  Scenario: User should be able to edit a Compound
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH003
    *     The user navigates to the Drug Settings tab in the plans page
    *     The user opts to edit a compound
    *     The user clears the following form fields
      | High Dollar Limit   |
      | Minimum Ingredients |
      | Maximum Ingredients |
      | Maximum Refills     |
      | Fixed Copay         |
      | Notes               |
    *     The user enters the following values in Compounds Modal
      | High Dollar Limit              | 100           |
      | Exceed High Dollar Status      | PA Review     |
      | Minimum Ingredients            | 10            |
      | Maximum Ingredients            | 20            |
      | Clarification Code Recognition | Yes           |
      | Legend Ingredient Required     | No            |
      | Maximum Refills                | 100           |
      | Fixed Copay                    | 10            |
      | Notes                          | Test Compound |
    *     The user clicks on the save button on the modal overlay
    Then  The user should se the following values in the compounds section
      | High Dollar Limit              | $100          |
      | Exceed High Dollar Status      | PA Review     |
      | Minimum Ingredients            | 10            |
      | Maximum Ingredients            | 20            |
      | Clarification Code Recognition | Yes           |
      | Legend Ingredient Required     | No            |
      | Maximum Refills                | 100           |
      | Fixed Copay                    | 10            |
      | Notes                          | Test Compound |

  @P2 @Automated
  Scenario: User validates the error messages on mandatory fields
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH003
    *     The user navigates to the Drug Settings tab in the plans page
    *     The user opts to edit a compound
    *     The user clears the form field Fixed Copay
    *     The user clicks on the save button on the modal overlay to verify error message
    Then  The user should see the following error message on field Fixed Copay in compounds modal
      """
      Please enter Fixed Copay
      """

  @P2 @Automated
  Scenario: User validates the field validations on the form fields
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH001
    *     The user navigates to the Drug Settings tab in the plans page
    *     The user opts to edit a compound
    *     The user enters the following values in Compounds Modal
      | High Dollar Limit              | test0         |
      | Minimum Ingredients            | `test1`       |
      | Maximum Ingredients            | @             |
      | Maximum Refills                | #             |
      | Fixed Copay                    | *             |
    *     The user clicks on the save button on the modal overlay to verify error message
    Then  The user validates the following error messages on the Compounds Modal form
      | High Dollar Limit   | Only numbers are allowed |
      | Minimum Ingredients | Only numbers are allowed |
      | Maximum Ingredients | Only numbers are allowed |
      | Maximum Refills     | Only numbers are allowed |
      | Fixed Copay         | Only numbers are allowed |

  @P2 @Automated
  Scenario: User validates the min max validations on the form fields
    Given The user is on the client configuration page for client with code AP1920
    When  The user navigates to the Plans tab in the configuration
    *     The user clicks on edit button for Plan Code with value CH001
    *     The user navigates to the Drug Settings tab in the plans page
    *     The user opts to edit a compound
    *     The user enters the following values in Compounds Modal
      | Minimum Ingredients | 100 |
      | Maximum Ingredients | 10  |
      | Fixed Copay         | 100 |
    *     The user clicks on the save button on the modal overlay to verify error message
    Then  The user validates the following error messages on the Compounds Modal form
      | Minimum Ingredients | Please enter a value lesser than maximum ingredients  |
      | Maximum Ingredients | Please enter a value greater than minimum ingredients |