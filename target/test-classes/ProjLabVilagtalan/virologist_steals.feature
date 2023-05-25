Feature: Virologist steals
  Virologist steals from a stunned virologist

  Scenario: Virologist steals
    Given there are two virologists, one who is stunned with a backpack and a cape, and an other with a backpack
    When the virologist steals from the stunned one
    Then the virologist gains the other's materials and cape, but not the backpack, since they already have one