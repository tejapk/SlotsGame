Feature: Testing the Slots game

  Scenario: Check game homepage is displayed correctly
    Given Player opens the gamepage
    Then Player should see welcome message

  Scenario: Check if start button is clickable or not
    Given Player opens the gamepage
    Then Start button should be clickable

  Scenario: Checking status is corresponding to the result
    Given Player opens the gamepage
    When Player hits on the play button
    Then Correct status should be displayed