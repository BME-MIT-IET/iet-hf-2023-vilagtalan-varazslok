Feature: Virologist uses Stun virus on another with AntiStun
  VIrologists cast Stun virus on another Virologist

  Scenario: Virologist uses Stun virus on another with AntiStun
    Given There are two Virologists on the same field with Stun and AntiStun
    When One of the Virologists casts Stun virus on the other
    Then The other virologist does not recieve the effect of the Stun virus