Feature: Virologist moves by chorea
  Virologist moves on the map by chorea virus

  Scenario: Virologist moves from a field to another by chorea
    Given There are two fields that are neighbours and a there is a virologist on the first field infected by chorea
    When Timer ticks
    Then The virologist with chorea should appear on the other field