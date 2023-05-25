Feature: Use virus on virologist with glove
  Virologist uses a virus on another virologist who has gloves, so it bounces back

  Scenario: Used virus bounces back from gloved virologist
    Given There is a field with two virologist, the first knows chorea and created one, the second has gloves
    When The virologist uses the chorea virus on the other
    Then The first virologist should get the chorea