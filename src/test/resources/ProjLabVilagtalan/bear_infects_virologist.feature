Feature: Bear infects virologist
  Bear moves to other field, infects other virologist

  Scenario: Bear moves to other field, infects other virologist
    Given There are two fields that are neighbours and a there is virologist on the first field
    And the virologist has a Bear
    And there is another virologist on the second field        
    When the timer ticks
    Then the other virologist should be bear