Feature: Validate Latest Foreign Exchange rates

  Background: Pressetting API endpoint
    Given user has base endpoint

  Scenario: test Latest rates API For SuccessCode
    When user wants to call API with date as "latest"
    Then response code should be 200

  #will take this later
  Scenario: verify latest currency code API with Date API as todays date
    When user wants to call API with date as "latest"
    Then response code should be 200
    And API response should have date as "todaysDate" and validate the response with privoius resposnse

  Scenario: test Latest rates API with one rate Symbol
    When user wants to call API with date as "latest" and Symbols as "GBP"
    Then response code should be 200
    And API should have rates "GBP" with some value in response

  Scenario: test Latest rates API with multiple rate Symbols
    When user wants to call API with date as "latest" and Symbols as "GBP,INR"
    Then response code should be 200
    And API should have rates "GBP,INR" with some value in response

  Scenario: test Latest rates API For Base
    When user wants to call API with date as "latest" and Base as "GBP"
    Then response code should be 200
    And API should have Base "GBP" with some value in response

  #negative
  Scenario: test Latest rates API For multiple Base
    When user wants to call API with date as "latest" and Base as "GBP,MNX"
    Then response code should be 400

  Scenario: verify Default Base Is EURO for Latest API
    When user wants to call API with date as "latest"
    Then response code should be 200
    And API should have Base "GBP" with some value in response

  Scenario: verify Base and Symbol Both have Same Currency EURO
    When user wants to call API with date as "latest" and Base as "EUR" and symbol as "EUR"
    Then response code should be 400

  Scenario: verify Base And Symbol Both Same Currency INR
    When user wants to call API with date as "latest" and Base as "INR" and symbol as "INR"
    Then response code should be 200
    And API should have Base "GBP" and rates as "INR" with some value in response

  Scenario: verify invalid Currency Code For Symbol
    When user wants to call API with date as "latest" and Base as "Invalid" and symbol as "Invalid"
    Then response code should be 400
  