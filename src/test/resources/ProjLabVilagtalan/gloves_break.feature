Feature: Gloves break
  Virologist uses a virus on another virologist, who has gloves with one durability, so the virus bounces back and breaks the gloves

  Scenario: Virus bounces back but breaks the gloves
    Given There is a field with two virologist, the first knows chorea and created one, the second has gloves with one durability
    When The virologist uses the chorea virus on the other
    Then First virologist should be infected by chorea, second virologist should not have gloves