Feature: Validate Foreign Exchange rates for specific date

Background: Pressetting API endpoint 
	Given User has base endpoint 
	
@smoke	
Scenario: Verify that the rates with dates  API'sresponse is Successful
	When User calls Rates API with date as "2021-03-02" 
	Then Response code should be 200 
	And Expected Base,rates values should match with the actual for date "2021-03-02" 
	
@regression	
Scenario: Verify Base And Symbol Both Same Currency INR for specific date 
	When User calls Rates API with date as "2020-03-02" and Base as "INR" and symbol as "GBP,USD" 
	Then Response code should be 200
	And API should have Base "INR" and rates as "GBP,USD" with some value in response and date as "2020-03-02" 	
	
@regression			
Scenario: Verify invalid Currency Code For Symbol 
	When User calls Rates API with date as "2021-03-02" and Base as "Invalid" and symbol as "Invalid" 
	Then Response code should be 400
	And Response should have error message "Base 'Invalid' is not supported."
	
@regression		
Scenario: Verify for invalid date 
	When User calls Rates API with date as "2021-29-02" and Base as "Invalid" and symbol as "Invalid" 
	Then Response code should be 400
	And Response should have error message "time data '2021-29-02' does not match format"
	
@regression		
Scenario: Verify for Older Date than 1999_01_04 
	When User calls Rates API with date as "1999-01-03" and Base as "GBP" and symbol as "GBP" 
	Then Response code should be 400
	And Response should have error message "There is no data for dates older then 1999-01-04." 
	
@regression	
Scenario: Verify for future date 
	When User calls Rates API with date as "futureDate" 
	Then Response code should be 200 
	Then API should have date "yesterdaysDate" in response
	
@regression		
Scenario: Verify for specific date where currency does not exists 
	When User calls Rates API with date as "2003-03-02" and Base as "EUR" and symbol as "INR" 
	Then Response code should be 400
	And Response should have error message "Symbols 'INR' are invalid for date 2003-03-02"