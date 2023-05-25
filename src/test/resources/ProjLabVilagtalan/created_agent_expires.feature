Feature: Created agent expires
  Created agent expires after 3 ticks

  Scenario: Created antistun agent expires after 3 ticks
    Given There is a virologist who has a created antistun
    When The timer ticks 3 times
    Then The antistun agent should be expired