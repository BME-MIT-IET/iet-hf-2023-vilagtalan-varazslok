Feature: Virologist cannot steal
  Virologist cannot steal since they are stunned

  Scenario: Virologist cannot steal
    Given there are two virologists, one has a cape and both are stunned
    When the capeless virologist tries to steal from the other
    Then nothing happens