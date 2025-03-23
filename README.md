# Selenium, Java, and Cucumber with POM
This automation project follows BDD approach and tests are written in Gherkin(Cucumber) language. It's build on Selenium and Java using Page Object Model(POM) framework. 
# Folder Structure

- **java/io/cucumber/pages** : This folder will contain the page objects for the application
- **java/io/cucumber/stepDefinition** : This folder contains step Definition files which are used to write the code for every test step.
- **java/io/cucumber/core** : This folder contains main base classes.
- **java/io/cucumber/util** : This folder contain functions which can be used across test suite.
- **resource** :  This folder contains the test data files and application properties that are require for testing.
- **target** : This folder contains the results of execution
- **resource/features** : This folder will contain features files. And each feature file contains step-by-step test scenarios.


## Run the tests

To run all the tests use below command in terminal
`mvn clean test`

Allure reports will be created after the run and to generate report
`allure generate allure-results --clean -o allure-report`
or
`allure serve Allure-Report`