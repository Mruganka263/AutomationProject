Feature: The Window Switching test
  This feature covers test to verify new window

  @TEST_TI_0002
  Scenario: User is able to switch window and frame and verify text
    Given the page under test is 'window_switching_url'
    When user clicks Open new window link
    Then a new window should open
    And verify text on new window
    Then navigate back to previous window
    And verify text on main window by switching iframe
