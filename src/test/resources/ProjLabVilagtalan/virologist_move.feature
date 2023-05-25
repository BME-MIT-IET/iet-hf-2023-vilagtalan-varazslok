Feature: Virologist move
  Virologist moves on the map

  Scenario: Virologist moves from a field to another
    Given There are two fields that are neighbours and a there is a virologist on the first field
    When The virologist moves to the other field
    Then The virologist should appear on the other field