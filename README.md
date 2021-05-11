# Assignment
    The Automation test framework developed for https://ratesapi.io/documentation/. 
    
# Tech stack:
  1. Programming language: Java
  2. Build management tool: Maven
  3. Unit Testing Framework: JUnit
  4. BDD framework: cucumber
  5. Reporting framework: Extent Report
  6. CICD: github Actions
  
  
  # Test Report Screenshot:
  <img src="./test_report.PNG" alt="Your image title" width="300"/>
  
  # How to Execute Tests
    - Execute all features : mvn test
    - Execute smoke Scenarios: -Dcucumber.filter.tags="@smoke"
    - Execute regression Scenarios: -Dcucumber.filter.tags="@regression"
