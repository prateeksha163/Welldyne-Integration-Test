@ConfigureEditStepTherapy @epic=configure_Edit_Therapy @configure_Step_Therapy
Feature: Edit and view Step therapies

  As an administrator
  I should be able to edit and view the details of Step Therapy created

  @P1 @Automated @event=step_therapy_to_edit
  Scenario: The user should be able to validate the heading and title in Step Therapy Schedule page
    Given The user is on List Management Page
    When  The user clicks on Add Step Therapy Button
    *     The user updates the following form fields and values in Step Therapy form
      | Step Therapy Name | My Step Therapy for edit purpose |
      | Step Therapy Type | H - Prerq, prior LstFmDt         |
      | Criteria          | Age related                      |
      | Patient Pay Notes | testing purpose notes            |
    *     The user clicks on the add sequence button on Step Therapy form
    *     The user updates the following form fields and values in Step Therapy form
      | Lookback period        | 40             |
      | Min Usage              | 70             |
      | Max Usage              | 90             |
      | Min Fills              | 20             |
      | Multi Source Indicator | Brand          |
      | OTC Name               | combination    |
      | Drug Status            | non-fr         |
      | Products               | 36800-0224-80  |
      | GPI                    | 02400040122110 |
    *     The user clicks on the add rule button on Step Therapy form
    *     The user updates the range field with min value 20 and max value 40 in Step Therapy schedule form
    Then  The user clicks on the Submit button on the modal overlay
    Then  The user searches the Step Therapy Table by entering value My Step Therapy for edit purpose in search bar
    *     The user clicks on view button for column Step Therapy Name and value My Step Therapy for edit purpose in Step Therapy Table
    *     The Step Therapy Schedule section should be present in the Step Therapy Page
    *     The user validates the title My Step Therapy for edit purpose on Step Therapy Page

  @P1 @Automated @event=edit_form_values @waitingFor=step_therapy_to_edit
  Scenario: The user should be able to validate Step Therapy Details in Step Therapy Schedule section
    Given The user is on List Management Page
    Then  The user searches the Step Therapy Table by entering value My Step Therapy for edit purpose in search bar
    Then  The user clicks on view button for column Step Therapy Name and value My Step Therapy for edit purpose in Step Therapy Table
    Then  The user should validate the below attributes and values in Step Therapy Schedule section
      | Drug Attribute Selection | Multi Source Indicator( Brand ), Drug Identifier by OTC Name( Y - Combination of P & O ), Drug Status( *p - 1 Pd Non-Frm; Pref Product ) |
      | GPI                      | 02400040122110 - Cefepime HCl For IV Soln 1 GM and Dextrose 5% (50 ML)                                                                   |
      | Products                 | 36800-0224-80 Anti-Diarrheal                                                                                                             |
      | Rules and Restrictions   | Age(20 to 40)                                                                                                                            |
      | Lookback period          | 40 Days                                                                                                                                  |
      | Days Supply              | Min Usage( 70 Days ), Max Usage( 90 Days ), Min Fills( 20 )                                                                              |
      | Step Therapy Type        | H - Prerq, prior LstFmDt                                                                                                                 |
      | Patient Pay Notes        | testing purpose notes                                                                                                                    |
      | Criteria                 | Age related                                                                                                                              |
      | Step Therapy Name        | My Step Therapy for edit purpose                                                                                                         |

  @P1 @Automated @waitingFor=edit_form_values
  Scenario: The user should be able to edit and validate step Therapy schedule form
    Given The user is on List Management Page
    Then  The user searches the Step Therapy Table by entering value My Step Therapy for edit purpose in search bar
    Then  The user clicks on view button for column Step Therapy Name and value My Step Therapy for edit purpose in Step Therapy Table
    Then  The user opts to update Step Therapy Schedule Modal
    *     The user updates the following form fields and values in Step Therapy Schedule Form
      | Step Therapy Type | F - Formulary Prerequisite                                                         |
      | Criteria          | the science or practice of the diagnosis, treatment, and prevention of disease.    |
      | Patient Pay Notes | Drugs are considered to be such things as stimulants, narcotics, and hallucinogens |
    *     The user updates the following form fields and values in Step Therapy Schedule Form
      | Lookback period        | 90                               |
      | Min Usage              | 50                               |
      | Max Usage              | 100                              |
      | Min Fills              | 50                               |
      | Multi Source Indicator | Brand Without Generic Alternates |
      | OTC Name               | over                             |
      | Drug Status            | msg;Form                         |
      | Products               | 55513-0110-01                    |
      | GPI                    | 01300040102118                   |
    Then  The user clicks on the Submit button on the modal overlay
    Then  The user should validate the below attributes and values in Step Therapy Schedule section
      | Drug Attribute Selection | Multi Source Indicator( Brand Without Generic Alternates ), Drug Identifier by OTC Name( P - Over the counter (Multiple Source) ), Drug Status( x - O Non-Form w/ msg;Form ) |
      | GPI                      | 02400040122110 - Cefepime HCl For IV Soln 1 GM and Dextrose 5% (50 ML) 01300040102118 - Nafcillin Sodium For IV Soln 2 GM                                                   |
      | Products                 | 36800-0224-80 Anti-Diarrheal 55513-0110-01 Aranesp (Albumin Free)                                                                                                           |
      | Rules and Restrictions   | Age(20 to 40)                                                                                                                                                               |
      | Lookback period          | 90 Days                                                                                                                                                                     |
      | Days Supply              | Min Usage( 50 Days ), Max Usage( 100 Days ), Min Fills( 50 )                                                                                                                |
      | Step Therapy Type        | F - Formulary Prerequisite                                                                                                                                                  |
      | Patient Pay Notes        | Drugs are considered to be such things as stimulants, narcotics, and hallucinogens                                                                                          |
      | Criteria                 | the science or practice of the diagnosis, treatment, and prevention of disease.                                                                                             |
      | Step Therapy Name        | My Step Therapy for edit purpose                                                                                                                                            |

  @P1 @Automated @waitingFor=validate_attributes
  Scenario Outline: The user should be able to edit Rules and Restrictions in Step Therapy Schedule form
    Given The user is on List Management Page
    Then  The user searches the Step Therapy Table by entering value My Step Therapy in search bar
    Then  The user clicks on view button for column Step Therapy Name and value My Step Therapy in Step Therapy Table
    Then  The user opts to update Step Therapy Schedule Modal
    Then The user updates the <criteria> age field with value <value> in Step Therapy Schedule form
    Then  The user clicks on the Submit button on the modal overlay
    Then  The user should validate the below attributes and values in Step Therapy Schedule section
      | Step Therapy Name        | My Step Therapy                                                                                                                                        |
      | Step Therapy Type        | H - Prerq, prior LstFmDt                                                                                                                               |
      | Criteria                 | Age related                                                                                                                                            |
      | Patient Pay Notes        | testing purpose notes                                                                                                                                  |
      | Rules and Restrictions   | Age(<value>)                                                                                                                                           |
      | Lookback period          | 40 Days                                                                                                                                                |
      | Days Supply              | Min Usage( 70 Days ), Max Usage( 90 Days ), Min Fills( 20 )                                                                                            |
      | Drug Attribute Selection | Multi Source Indicator( Brand ), Drug Identifier by OTC Name( S - Prescription Drug (Multiple Source) ), Drug Status( b - Non-Form (Skip OOP NoAccm) ) |
      | Products                 | 11917-0079-69 Antacid Extra Strength                                                                                                                   |
      | GPI                      | 02400040122110 - Cefepime HCl For IV Soln 1 GM and Dextrose 5% (50 ML)                                                                                 |
    Examples:
      | criteria | value |
      | max      | 30    |
      | min      | 20    |

  @P1 @Automated @waitingFor=validate_attributes
  Scenario Outline: The user should validate that '*' should appear for GPI codes with length less than 14 in Step Therapy Schedule form
    Given The user is on List Management Page
    Then  The user searches the Step Therapy Table by entering value My Step Therapy in search bar
    Then  The user clicks on view button for column Step Therapy Name and value My Step Therapy in Step Therapy Table
    Then  The user opts to update Step Therapy Schedule Modal
    *     The user clicks on the add sequence button on Step Therapy form
    *     The user validates the dropdown values coming in GPI dropdown for values <GPI> from database table gpi in Step Therapy Schedule Modal
    Examples:
      | GPI          |
      | (Trihydrate) |
      | Cabozantinib |

  @P1 @Automated @waitingFor=validate_attributes
  Scenario Outline: The user validates that the already added values should not be visible in dropdown in Step Therapy Schedule form
    Given The user is on List Management Page
    Then  The user searches the Step Therapy Table by entering value My Step Therapy in search bar
    Then  The user clicks on view button for column Step Therapy Name and value My Step Therapy in Step Therapy Table
    Then  The user opts to update Step Therapy Schedule Modal
    *     The user should validate the dropdown values coming in <field> dropdown should not contain <Value> in step therapy schedule Modal on searching <Searched Text>
    Examples:
      | field    | Searched Text  | Value                                                                  |
      | Products | 11917-0079-69  | 11917-0079-69 Antacid Extra Strength                                   |
      | GPI      | 02400040122110 | 02400040122110 - Cefepime HCl For IV Soln 1 GM and Dextrose 5% (50 ML) |

  @P1 @Automated @waitingFor=validate_attributes
  Scenario: The user should validate dropdown values in Multi Source Indicator field in Step Therapy Schedule form
    Given The user is on List Management Page
    Then  The user searches the Step Therapy Table by entering value My Step Therapy in search bar
    Then  The user clicks on view button for column Step Therapy Name and value My Step Therapy in Step Therapy Table
    Then  The user opts to update Step Therapy Schedule Modal
    *     The user clicks on the add sequence button on Step Therapy form
    *     The user should validate the static values coming in Multi Source Indicator dropdown in Step Therapy Schedule form
      | Brand | Brand With Generic Alternates | Brand Without Generic Alternates | Generic |

  @P1 @Automated @waitingFor=validate_attributes
  Scenario: The user should validate dropdown values in Drug Stats field in Step Therapy Schedule form
    Given The user is on List Management Page
    Then  The user searches the Step Therapy Table by entering value My Step Therapy in search bar
    Then  The user clicks on view button for column Step Therapy Name and value My Step Therapy in Step Therapy Table
    Then  The user opts to update Step Therapy Schedule Modal
    *     The user clicks on the add sequence button on Step Therapy form
    *     The user should validate the static values coming in Drug Status dropdown in Step Therapy Schedule form
      | * - No Override | *a - Plan Default | b - Non-Form (Skip OOP NoAccm) | *c - Diag Cde Mismatch,NxtLst | *d - Non-Form (NoBenMaxAccm) | e - NFrm (NoDed/Ben; apply OOP) | f - Form (NoDed/Ben; apply OOP) | g - Form (No OOP; apply Ded/Ben)) | h - Override-List/PA Dtls 2 | *j - Reject, Generic First | *p - 1 Pd Non-Frm; Pref Product | s - Formulary Captured No ACCUMS | t - Non-Form Capt No ACCUMS | x - O Non-Form w/ msg;Form | y - Y Form; Non-Form w/Msg | z - Y Form; Non-Form Reject | A - 1 Form; Pd Non-Form w/Msg | B - No Prescrptn Drug Benefit | C - Paid Non-Formulary w/Msg | D - 15 Form; Pd Non-Form w/Msg | E - 1 Form; Non-Form Reject | F - Formulary | G - Paid Non-Formulary w/oMsg | H - Formulary Captured | I - 15 Form; Non-Form Reject | J - 1 F; Non-F(NoDed/BenAccum) | K - Formulary (Skip Accumulator Maximums) | L - Non-Form (Skip Accumulator Maximums) | M - Message | N - Non-Formulary Reject | O - Override | P - Prior Authorization | *Q - F: Don’t Use GPI Price Dtl | R - Reject | S - Formulary (No Ded/Ben Accum) | T - Non-Form (No Ded/Ben Accum) | *U - 5 Formulary; Prior Auth | *V - 05 Paid Non-Form;Reject | W - Negative Pharmacy List;R | *X - Preferred Product | *Y - Diag Code Mismatch,Bypass | *Z - Formulary Alternative | *1 - Gen Ind=Y, Sts F; Sts C | *2 - Gen Ind=Y, Sts F; Sts G | 3 - Override-Use List/PA Dtls | *4 - G NF: Don’t Use Price Dtl | 5 - Formulary 5 | 6 - Formulary 6 | 7 - Non-Formulary Captured | 8 - Non-Preferred Form Reject | 9 - Y Pd NFw/o Msg; Pd NFw/Msg |

  @P1 @Automated @waitingFor=validate_attributes
  Scenario Outline: The user should validate the content of dropdown values from database in Step Therapy Schedule form
    Given The user is on List Management Page
    Then  The user searches the Step Therapy Table by entering value My Step Therapy in search bar
    Then  The user clicks on view button for column Step Therapy Name and value My Step Therapy in Step Therapy Table
    Then  The user opts to update Step Therapy Schedule Modal
    *     The user clicks on the add sequence button on Step Therapy form
    Then  The user validates the dropdown values coming in <Field> dropdown from database table <Table> in Step Therapy Schedule Modal
    Examples:
      | Field             | Table             |
      | Step Therapy Type | step_therapy_type |
      | OTC Name          | otc_code          |

  @P1 @Automated @waitingFor=validate_attributes
  Scenario Outline: The user validates field validation error messages when special characters are used in editing a Step Therapy
    Given The user is on List Management Page
    Then  The user searches the Step Therapy Table by entering value My Step Therapy in search bar
    Then  The user clicks on view button for column Step Therapy Name and value My Step Therapy in Step Therapy Table
    Then  The user opts to update Step Therapy Schedule Modal
    *     The user clicks on the add sequence button on Step Therapy form
    *     The user updates the following form fields and values in Step Therapy Schedule Form
      | Min Usage | @ |
      | Max Usage | * |
      | Min Fills | $ |
    *     The user clicks on the Save button on the modal overlay to verify error message
    Then  The user should validate the following errors <Error Message> on field <Field> in Step Therapy schedule form
    Examples:
      | Field     | Error Message            |
      | Min Usage | Only numbers are allowed |
      | Max Usage | Only numbers are allowed |
      | Min Fills | Only numbers are allowed |

  @P1 @Automated @waitingFor=validate_attributes
  Scenario Outline: The user validates field validation error messages when min value is greater than max value in Step Therapy Detail Page
    Given The user is on List Management Page
    Then  The user searches the Step Therapy Table by entering value My Step Therapy in search bar
    Then  The user clicks on view button for column Step Therapy Name and value My Step Therapy in Step Therapy Table
    Then  The user opts to update Step Therapy Schedule Modal
    *     The user clicks on the add sequence button on Step Therapy form
    *     The user updates the following form fields and values in Step Therapy Schedule Form
      | Min Usage | 70 |
      | Max Usage | 50 |
    *     The user clicks on the Save button on the modal overlay to verify error message
    Then  The user should validate the following errors <Error Message> on field <Field> in Step Therapy schedule form
    Examples:
      | Field     | Error Message                               |
      | Min Usage | Please enter a value lesser than max usage  |
      | Max Usage | Please enter a value greater than min usage |

  @P1 @Automated @teardown
  Scenario Outline: The user should be able to delete an existing Step Therapy in Step Therapy Schedule for cleanup
    Given The user is on List Management Page
    Then  The user searches the Step Therapy Table by entering value <Step Therapy Name> in search bar
    Then  The user clicks on delete button for column Step Therapy Name and value <Step Therapy Name> in Step Therapy Table
    *     The user clicks on the confirm button on the delete modal
    Then  The row with column name Step Therapy Name and value <Step Therapy Name> should not be visible in Step Therapy table
    Examples:
      | Step Therapy Name                |
      | My Step Therapy                  |
      | My Step Therapy for edit purpose |