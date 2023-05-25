Feature: Virologist pick
  Virologist tries to move

  Scenario: Virologist tries to move to another field despite being stunned
    Given There are two fields that are neighbours and a there is a virologist on the first field and a stun potion
    When The virologist tries to move to the other field
    Then The virologist cannot move. 