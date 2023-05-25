Feature: Virologist uses Forget virus
  VIrologists cast Forget virus on another Virologist

  Scenario: Virologist uses Forget virus on another Virologist
    Given There are two Virologists on the same field
    When One of the Virologists casts Forget virus on the other
    Then The other virologist recieves the effect of the Forget virus