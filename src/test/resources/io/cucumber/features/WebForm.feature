Feature: The Web Form
  This feature covers all form elements which needs to be verified

  @TEST_TI_0001
  Scenario: User is able to Add details in all input fields and Submit the form
    Given the page under test is 'https://www.selenium.dev/selenium/web/web-form.html'
    When Enter 'User1' text in 'Text input' field
    * Enter 'ABCD' text in 'Password' field
    * Enter 'Address line 1 \n Address Line 2' text in 'Textarea' field
    * Check whether Disable input field is disabled
    * Select 'Two' value from select dropdown
    * Enter 'data1' value in 'Datalist Dropdown' field
    * Upload a File: 'src/test/resources/data.txt'
    * 'Uncheck' checkbox 'Checked'
    * 'Check' checkbox 'Default'
    * select radio button 'Default'
    * select radio button 'Checked'
    * Pick 'Blue' color
    * Pick 'hotpink' color
    * Pick 'lightgreen' color
    And Select today's date
    And Select date '01-Jan-2021' from calendar
    And Select date '31-Oct-2030' from calendar
    And Select date '15-May-1994' from calendar
    And Move Example range to 'MAX'
    Then Click on Submit
    And Verify form submitted message is displayed


