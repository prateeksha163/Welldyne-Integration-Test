@SSO_Login @Demo
Feature: SSO Login

  This is the Login functionality of the PPM application using SSO.

  @P1 @Automated
  Scenario: An active directory user with access to Benefits Configurator logs in using SSO
    Given The user is on Microsoft MyApps Page
    """
    http://myapps.microsoft.com/
    """
    When The user signs in using authorized SSO credentials
    Then user will not be asked for credentials and will be navigated to Benefits Configurator Dashboard page
    """
    https://ppm.qa-welldyne.com/dashboard
    """

  @P1 @Automated
  Scenario: An Unauthorized user tries to access Benefits Configurator
    Given An unauthorized user navigates to the Dashboard url
    """
    https://ppm.qa-welldyne.com/dashboard
    """
    Then The user will be redirected to the Error Landing Page
    """
    https://ppm.qa-welldyne.com/unauthenticated
    """
    And The user should see the error title as "Oops!"
    And The error text as "Your session has timed out. Please login again."


  #Scenario: An active directory user with no access to Benefits Configurator logs in using SSO
   # Given The user is on Microsoft MyApps Page
   # """
    #http://myapps.microsoft.com/
    #"""
    #When The user logs in using credentials username "jsethi@welldynerx.com" and password "/WngMjc|M-"
    #Then