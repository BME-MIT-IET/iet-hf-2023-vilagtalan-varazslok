Feature: Bear virus infects
  Virologist moves on laboratory, gets infected by bear

  Scenario: Virologist moves on laboratory, gets infected by bear, loses their other virus
    Given there is a laboratory with bear and a virologist
    And the virologist has a Forget        
    When the virologist moves to the laboratoty
    Then the virologist should have one virus
    And the virus should be bear