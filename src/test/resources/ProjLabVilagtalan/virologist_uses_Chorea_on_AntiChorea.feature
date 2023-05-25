Feature: Virologist uses Chorea virus on another with AntiChorea
  VIrologists cast Chorea virus on another Virologist

  Scenario: Virologist uses Forget virus on another with AntiChorea
    Given There are two Virologists on the same field with Forget and AntiChorea
    When One of the Virologists casts Chorea virus on the other
    Then The other virologist does not recieve the effect of the Chorea virus