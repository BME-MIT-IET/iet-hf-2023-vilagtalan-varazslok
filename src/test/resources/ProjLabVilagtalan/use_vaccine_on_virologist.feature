Feature: Use vaccine on virologist
  Virologist uses a vaccine on another virologist

  Scenario: Antichorea is used on second virologist
    Given There is a field with two virologist, the first has an antichorea
    When The virologist uses the antichorea vaccine on the other
    Then The second virologist should be antichorea vaccinated