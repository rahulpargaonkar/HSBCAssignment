Feature: Validate Latest Foreign Exchange rates 

Background: Pressetting API endpoint 
	Given user has base endpoint 
	
Scenario: test date rates API For SuccessCode 
	When user wants to call API with date as "2021-03-02" 
	Then response code should be 200 
	And API should have dates "2021-03-02"
	
Scenario: verify Base And Symbol Both Same Currency INR for specific date 
	When user wants to call API with date as "2020-03-02" and Base as "INR" and symbol as "GBP,USD" 
	Then response code should be 200
	And API should have Base "INR" and rates as "GBP,USD" with some value in response and date as "2020-03-02" 	
	
		
Scenario: verify invalid Currency Code For Symbol 
	When user wants to call API with date as "2021-03-02" and Base as "Invalid" and symbol as "Invalid" 
	Then response code should be 400
	
Scenario: verify for invalid date 
	When user wants to call API with date as "2021-29-02" and Base as "Invalid" and symbol as "Invalid" 
	Then response code should be 400
	
Scenario: verify for Older Date than 1999_01_04 
	When user wants to call API with date as "1999_01_03" and Base as "Invalid" and symbol as "Invalid" 
	Then response code should be 400
	And response should have error message "There is no data for dates older then 1999-01-04." 
	
Scenario: verify for future date 
	When user wants to call API with date as "futureDate" 
	Then response code should be 200 
	And API response should have date as "todaysDate" 
	
Scenario: verify for specific date where currency does not exists 
	When user wants to call API with date as "2003-03-02" and Base as "EUR" and symbol as "INR" 
	Then response code should be 400
	And API response should have message as "Symbols 'INR' are invalid for date 2003-03-02"