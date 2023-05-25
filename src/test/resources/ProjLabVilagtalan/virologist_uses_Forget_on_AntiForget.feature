Feature: Virologist uses Forget virus on another with AntiForget
  VIrologists cast Forget virus on another Virologist

  Scenario: Virologist uses Forget virus on another with AntiForget
    Given There are two Virologists on the same field with Forget and AntiForget
    When One of the Virologists casts Forget virus on the other
    Then The other virologist does not recieve the effect of the Forget virus