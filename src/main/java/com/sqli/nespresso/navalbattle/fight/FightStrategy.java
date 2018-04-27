package com.sqli.nespresso.navalbattle.fight;

import com.sqli.nespresso.navalbattle.battle.Team;

public interface FightStrategy
{
  void localizeDamages();

  void fight(final Team teamOne, final Team teamTwo);

  boolean isTeamOneWinning();
}
