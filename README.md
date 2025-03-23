# Selenium, Java, and Cucumber with POM
This automation project follows the BDD (Behavior-Driven Development) approach, with test scenarios written in Gherkin (Cucumber) language. It is built using Selenium and Java and follows the Page Object Model (POM) framework for better maintainability and reusability 

📦 Project Root  
 ┣ 📂 src/main/java/io/cucumber/  
 ┃ ┣ 📂 pages            # Contains Page Object classes for the application  
 ┃ ┣ 📂 stepDefinition   # Holds step definition files mapping Gherkin steps to Java methods  
 ┃ ┣ 📂 core            # Contains base classes and core utilities  
 ┃ ┣ 📂 util            # Includes reusable functions across the test suite  
 ┣ 📂 src/test/resources  
 ┃ ┣ 📂 features        # Holds Cucumber feature files (test scenarios written in Gherkin)  
 ┃ ┣ 📂 testdata        # Contains test data files and application properties  
 ┣ 📂 target            # Stores execution results and reports  

## Run the tests

Execute the following command in the terminal to run all test cases:
`mvn clean test`

**##Generate Allure Reports**
After test execution, Allure reports will be created under the allure-results folder.

**#Generate Allure Report**
`allure generate allure-results --clean -o allure-report`
or
`allure serve Allure-Report`
