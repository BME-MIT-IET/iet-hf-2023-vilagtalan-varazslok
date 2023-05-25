Feature: Use virus both have gloves
  Virologist uses a virus on another virologist, both of them have gloves, so the virus doesn't affect anyone

  Scenario: Used virus doesn't affect anyone
    Given There is a field with two virologist, the first knows chorea and created one, both have gloves
    When The virologist uses the chorea virus on the other
    Then Noone should have chorea