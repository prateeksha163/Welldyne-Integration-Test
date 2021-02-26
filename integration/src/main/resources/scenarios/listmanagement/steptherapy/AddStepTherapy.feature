@ConfigureAddStepTherapy @epic=configure_Step_Therapy @configure_Step_Therapy
Feature: Manage Step therapy tables

  As an administrator
  I should be able to add and update a new Step Therapy in Step Therapy Page

  @P1 @Automated
  Scenario: The user validates the form fields in add Step Therapy Form
    Given The user is on List Management Page
    When  The user clicks on Add Step Therapy Button
    *     The user clicks on the add sequence button on Step Therapy form
    Then  The user should validate the following form fields
      | Step Therapy Name*                 |
      | Step Therapy Type*                 |
      | Criteria                           |
      | Patient Pay Notes                  |
      | Lookback period                    |
      | Min Usage                          |
      | Max Usage                          |
      | Min Fills                          |
      | Multi Source Indicator             |
      | Search Drug Identifier by OTC Name |
      | Search Drug Status                 |
      | Products                           |
      | GPI                                |

  @P1 @Automated @event=create_step_therapy
  Scenario: The user should be able to create a new Step Therapy
    Given The user is on List Management Page
    When  The user clicks on Add Step Therapy Button
    *     The user updates the following form fields and values in Step Therapy form
      | Step Therapy Name | My Step Therapy          |
      | Step Therapy Type | H - Prerq, prior LstFmDt |
      | Criteria          | Age related              |
      | Patient Pay Notes | testing purpose notes    |
    *     The user clicks on the add sequence button on Step Therapy form
    *     The user updates the following form fields and values in Step Therapy form
      | Lookback period        | 40             |
      | Min Usage              | 70             |
      | Max Usage              | 90             |
      | Min Fills              | 20             |
      | Multi Source Indicator | Brand          |
      | OTC Name               | Prescription   |
      | Drug Status            | NoAccm         |
      | Products               | 11917-0079-69  |
      | GPI                    | 02400040122110 |
    *     The user clicks on the add rule button on Step Therapy form
    *     The user updates the min age field with value 40 in Step Therapy form
    Then  The user clicks on the Submit button on the modal overlay
    Then  The user should validate the new row created with Step Therapy Name My Step Therapy and having below attributes
      | Step Therapy Type | Prerq, prior LstFmDt |
      | Criteria          | Age related          |
      | Step Therapy Name | My Step Therapy      |


  @P1 @Automated
  Scenario Outline: The user validates field errors on keeping fields blanks
    Given The user is on List Management Page
    When  The user clicks on Add Step Therapy Button
    *     The user clicks on the add sequence button on Step Therapy form
    *     The user clicks on the add rule button on Step Therapy form
    *     The user clicks on the Save button on the modal overlay to verify error message
    Then  The user should validate the following errors <Error Message> on field <Field> in Step Therapy form
    Examples:
      | Field             | Error Message                   |
      | Step Therapy Type | Please select step therapy type |
      | Age               | Please enter age                |
      | Step Therapy Name | Please enter step therapy name  |

  @P1 @Automated
  Scenario Outline: The user validates field errors on days supply fields
    Given The user is on List Management Page
    When  The user clicks on Add Step Therapy Button
    *     The user clicks on the add sequence button on Step Therapy form
    *     The user updates the following form fields and values in Step Therapy form
      | Min Usage | 70 |
      | Max Usage | 50 |
    *     The user clicks on the Save button on the modal overlay to verify error message
    Then  The user should validate the following errors <Error Message> on field <Field> in Step Therapy form
    Examples:
      | Field     | Error Message                               |
      | Min Usage | Please enter a value lesser than max usage  |
      | Max Usage | Please enter a value greater than min usage |

  @Automated @event=validate_attributes @waitingFor=create_step_therapy
  Scenario: The user should be able to view details of a Step Therapy created for the first time
    Given The user is on List Management Page
    Then  The user searches the Step Therapy Table by entering value My Step Therapy in search bar
    Then  The user clicks on view button for column Step Therapy Name and value My Step Therapy in Step Therapy Table
    Then  The user should validate the below attributes and values in Step Therapy Schedule section
      | Drug Attribute Selection | Multi Source Indicator( Brand ), Drug Identifier by OTC Name( S - Prescription Drug (Multiple Source) ), Drug Status( b - Non-Form (Skip OOP NoAccm) ) |
      | GPI                      | 02400040122110 - Cefepime HCl For IV Soln 1 GM and Dextrose 5% (50 ML)                                                                                 |
      | Products                 | 11917-0079-69 Antacid Extra Strength                                                                                                                   |
      | Rules and Restrictions   | Age(40)                                                                                                                                                |
      | Lookback period          | 40 Days                                                                                                                                                |
      | Days Supply              | Min Usage( 70 Days ), Max Usage( 90 Days ), Min Fills( 20 )                                                                                            |
      | Step Therapy Type        | H - Prerq, prior LstFmDt                                                                                                                               |
      | Patient Pay Notes        | testing purpose notes                                                                                                                                  |
      | Criteria                 | Age related                                                                                                                                            |
      | Step Therapy Name        | My Step Therapy                                                                                                                                        |

  @P1 @Automated
  Scenario Outline: The user validates field validation error messages when special characters are used in creating a new Step Therapy
    Given The user is on List Management Page
    When  The user clicks on Add Step Therapy Button
    *     The user clicks on the add sequence button on Step Therapy form
    *     The user updates the following form fields and values in Step Therapy form
      | <Field> | <Value> |
    *     The user clicks on the Save button on the modal overlay to verify error message
    Then  The user should validate the following errors <Error Message> on field <Field> in Step Therapy form
    Examples:
      | Field     | Error Message            | Value |
      | Min Usage | Only numbers are allowed | #     |
      | Max Usage | Only numbers are allowed | &     |
      | Min Fills | Only numbers are allowed | *     |


  @P1 @Automated
  Scenario Outline: The user validates the content of dropdown values from database
    Given The user is on List Management Page
    When  The user clicks on Add Step Therapy Button
    *     The user clicks on the add sequence button on Step Therapy form
    Then  The user should validate the dropdown values coming in <Field> dropdown from database table <Table>
    Examples:
      | Field             | Table             |
      | Step Therapy Type | step_therapy_type |
      | OTC Name          | otc_code          |

  @P1 @Automated
  Scenario: The user validates dropdown values in Drug Stats field
    Given The user is on List Management Page
    When  The user clicks on Add Step Therapy Button
    *     The user clicks on the add sequence button on Step Therapy form
    *     The user should validate the static values coming in Drug Status dropdown
      | * - No Override | *a - Plan Default | b - Non-Form (Skip OOP NoAccm) | *c - Diag Cde Mismatch,NxtLst | *d - Non-Form (NoBenMaxAccm) | e - NFrm (NoDed/Ben; apply OOP) | f - Form (NoDed/Ben; apply OOP) | g - Form (No OOP; apply Ded/Ben)) | h - Override-List/PA Dtls 2 | *j - Reject, Generic First | *p - 1 Pd Non-Frm; Pref Product | s - Formulary Captured No ACCUMS | t - Non-Form Capt No ACCUMS | x - O Non-Form w/ msg;Form | y - Y Form; Non-Form w/Msg | z - Y Form; Non-Form Reject | A - 1 Form; Pd Non-Form w/Msg | B - No Prescrptn Drug Benefit | C - Paid Non-Formulary w/Msg | D - 15 Form; Pd Non-Form w/Msg | E - 1 Form; Non-Form Reject | F - Formulary | G - Paid Non-Formulary w/oMsg | H - Formulary Captured | I - 15 Form; Non-Form Reject | J - 1 F; Non-F(NoDed/BenAccum) | K - Formulary (Skip Accumulator Maximums) | L - Non-Form (Skip Accumulator Maximums) | M - Message | N - Non-Formulary Reject | O - Override | P - Prior Authorization | *Q - F: Don’t Use GPI Price Dtl | R - Reject | S - Formulary (No Ded/Ben Accum) | T - Non-Form (No Ded/Ben Accum) | *U - 5 Formulary; Prior Auth | *V - 05 Paid Non-Form;Reject | W - Negative Pharmacy List;R | *X - Preferred Product | *Y - Diag Code Mismatch,Bypass | *Z - Formulary Alternative | *1 - Gen Ind=Y, Sts F; Sts C | *2 - Gen Ind=Y, Sts F; Sts G | 3 - Override-Use List/PA Dtls | *4 - G NF: Don’t Use Price Dtl | 5 - Formulary 5 | 6 - Formulary 6 | 7 - Non-Formulary Captured | 8 - Non-Preferred Form Reject | 9 - Y Pd NFw/o Msg; Pd NFw/Msg |

  @P1 @Automated
  Scenario: The user validates dropdown values in Multi Source Indicator field
    Given The user is on List Management Page
    When  The user clicks on Add Step Therapy Button
    *     The user clicks on the add sequence button on Step Therapy form
    *     The user should validate the static values coming in Multi Source Indicator dropdown
      | Brand | Brand With Generic Alternates | Brand Without Generic Alternates | Generic |

  @P1 @Automated
  Scenario Outline: The user validates that already added values should not be visible in dropdown in add new Step Therapy form
    Given The user is on List Management Page
    When  The user clicks on Add Step Therapy Button
    *     The user clicks on the add sequence button on Step Therapy form
    *     The user updates the following form fields and values in Step Therapy form
      | <field> | <Searched Text> |
    *     The user should validate the dropdown values coming in <field> dropdown should not contain <Value> in Step Therapy Form on searching <Searched Text>
    Examples:
      | field    | Searched Text  | Value                                                                  |
      | Products | 11917-0079-69  | 11917-0079-69 Antacid Extra Strength                                   |
      | GPI      | 02400040122110 | 02400040122110 - Cefepime HCl For IV Soln 1 GM and Dextrose 5% (50 ML) |

  @P1 @Automated
  Scenario Outline: The user validates on adding new section in Step Therapy Form, its section should appear
    Given The user is on List Management Page
    When  The user clicks on Add Step Therapy Button
    *     The user clicks on the <button> button on Step Therapy form
    Then  The <section> section should be present on the Step Therapy page
    Examples:
      | button       | section   |
      | add sequence | sequences |
      | add rule     | rules     |

  @P1 @Automated
  Scenario Outline: The user validates on removing a section in Step Therapy Form, its section should not appear
    Given The user is on List Management Page
    When  The user clicks on Add Step Therapy Button
    *     The user clicks on the <button> button on Step Therapy form
    *     The user clicks on remove button present on the section <section>
    Then The <section> section should not be present on the Step Therapy page
    Examples:
      | button       | section   |
      | add sequence | sequences |
      | add rule     | rules     |

  @P1 @Automated
  Scenario Outline: The user should validate that '*' should appear for GPI codes with length less than 14
    Given The user is on List Management Page
    When  The user clicks on Add Step Therapy Button
    *     The user clicks on the add sequence button on Step Therapy form
    *     The user validates the dropdown values coming in GPI dropdown for values <GPI> from database table gpi in Step Therapy form
    Examples:
      | GPI          |
      | (Trihydrate) |
      | Cabozantinib |

  @Automated
  Scenario: The user should be able to view details of a Step Therapy created for the first time test
    Given The user is on List Management Page
    Then  The user searches the Step Therapy Table by entering value My Step Therapy in search bar
    Then  The user clicks on view button for column Step Therapy Name and value My Step Therapy in Step Therapy Table
    Then  The user should validate the below attributes and values in Step Therapy Schedule section
      | Drug Attribute Selection | Multi Source Indicator( Brand ), Drug Identifier by OTC Name( S - Prescription Drug (Multiple Source) ), Drug Status( b - Non-Form (Skip OOP NoAccm) ) |
      | GPI                      | 02400040122110 - Cefepime HCl For IV Soln 1 GM and Dextrose 5% (50 ML)                                                                                 |
      | Products                 | 11917-0079-69 Antacid Extra Strength                                                                                                                   |
      | Rules and Restrictions   | Age(10) Age(50) Age(60 to 45)                                                                                                                                                |
      | Lookback period          | 40 Days                                                                                                                                                |
      | Days Supply              | Min Usage( 70 Days ), Max Usage( 90 Days ), Min Fills( 20 )                                                                                            |
      | Step Therapy Type        | H - Prerq, prior LstFmDt                                                                                                                               |
      | Patient Pay Notes        | testing purpose notes                                                                                                                                  |
      | Criteria                 | Age related                                                                                                                                            |
      | Step Therapy Name        | My Step Therapy                                                                                                                                        |
      | Lookback period          | 50 Days                                                                                                                                                |
      | Days Supply              | Min Usage( 50 Days ), Max Usage( 70 Days ), Min Fills( 50 )                                                                                            |
      | Drug Attribute Selection | Multi Source Indicator( Brand ), Drug Identifier by OTC Name( S - Prescription Drug (Multiple Source) ), Drug Status( *a - Plan Default )              |
      | Products                 | 11917-0156-57 Antacid Extra Strength                                                                                                                   |
      | GPI                      | 01100020* - Penicillin G Benzathine                                                                                                                    |