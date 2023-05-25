Feature: Virologist can't learn something twice
  Virologist tries to learn something twice, but fails to do so

  Scenario: Virologist tries to learn something twice, but fails to do so
    Given there is a laboratory with chorea and antichorea in it, and a virologist, who already knows chorea and antichorea
    When the virologist moves to the laboratory
    Then the virologist's learned viruses shouldn't change