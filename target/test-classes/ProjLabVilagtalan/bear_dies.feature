Feature: Bear dies
  Virologist bith axe kills bear

  Scenario: Virologist moves to other field, kills bear
    Given There are two fields that are neighbours and a there is virologist on the first field
    And the virologist has a Bear
    And there is another virologist on the second field
    And there is an axe with him        
    When the second virologist moves to the other field
    Then the bear should die