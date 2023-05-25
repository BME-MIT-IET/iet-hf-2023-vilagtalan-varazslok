
 Feature: Virologist cannot pick  up 2 capes
  Virologist tries to pick up 2 capes

  Scenario: Virologist has a cape and trying to pick up another one
    Given The virologist has a cape and is on a shelter
    When The virologist tries to pick up another cape
    Then The virologist cannot pick up 