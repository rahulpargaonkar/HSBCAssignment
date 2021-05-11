Feature: Validate Latest Foreign Exchange rates

  Background: Pressetting API endpoint
    Given User has base endpoint
    
@smoke
  Scenario: Verify that the rates API's response is Successful
    When User calls Rates API with date as "latest"
    Then Response code should be 200

@regression
  Scenario: Verify latest currency code API with Date API as todays date
    When User calls Rates API with date as "latest"
    Then Response code should be 200
    And API response should have date as "todaysDate" and validate the response with privoius resposnse
  
@regression
  Scenario: Verify Latest rates API with one rate Symbol
    When User calls Rates API with date as "latest" and Symbols as "GBP"
    Then Response code should be 200
    And API should have rates "GBP" with some value in response
    
@regression
  Scenario: Verify Latest rates API with multiple rate Symbols
    When User calls Rates API with date as "latest" and Symbols as "GBP,INR"
    Then Response code should be 200
    And API should have rates "GBP,INR" with some value in response
    
@regression
  Scenario: Verify Latest rates API For Base
    When User calls Rates API with date as "latest" and Base as "GBP"
    Then Response code should be 200
    And API should have Base "GBP" with some value in response

@regression 
  Scenario: Verify Latest rates API For multiple Base
    When User calls Rates API with date as "latest" and Base as "GBP,MNX"
    Then Response code should be 400
    
@regression
  Scenario: Verify Default Base Is EURO for Latest API
    When User calls Rates API with date as "latest"
    Then Response code should be 200
    And API should have Base "EUR" with some value in response
    
@regression 
  Scenario: Verify Base and Symbol Both have Same Currency EURO
    When User calls Rates API with date as "latest" and Base as "EUR" and symbol as "EUR"
    Then Response code should be 400
    And Response should have error message "Symbols 'EUR' are invalid for date"
    
@regression
  Scenario: Verify Base And Symbol Both Same Currency INR
    When User calls Rates API with date as "latest" and Base as "INR" and symbol as "INR"
    Then Response code should be 200
    And API should have Base "INR" and rates as "INR" with some value in response
    
@regression 
  Scenario: Verify invalid Currency Code For Symbol
    When User calls Rates API with date as "latest" and Base as "Invalid" and symbol as "Invalid"
    Then Response code should be 400
    And Response should have error message "Base 'Invalid' is not supported"  
  