Feature: Free Dictionary API
  This feature is to perform tests on Free Dictionary API

  @TEST_TI_0001
  Scenario: Validate GET request for word definition
    Given I have the dictionary API endpoint
    When I send a GET request for 'hello'
    Then API call got success with status code 200
    And the response should contain the word 'hello'
