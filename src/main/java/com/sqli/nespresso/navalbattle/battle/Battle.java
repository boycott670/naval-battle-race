package com.sqli.nespresso.navalbattle.battle;

import com.sqli.nespresso.navalbattle.fight.DefaultFightStrategy;
import com.sqli.nespresso.navalbattle.fight.FightStrategy;
import com.sqli.nespresso.navalbattle.ships.Ship;

public final class Battle
{
  public static final byte LOCALIZED_DAMAGES = 1;

  private final FightStrategy fightStrategy = new DefaultFightStrategy();

  private Team teamOne, teamTwo;

  private final boolean areDamagesLocalized;

  public Battle()
  {
    areDamagesLocalized = false;
  }

  public Battle(final byte damagesLocalized)
  {
    areDamagesLocalized = true;
  }

  public Battle side(final Ship... shipsOfSideOne)
  {
    teamOne = Team.of(shipsOfSideOne);

    return this;
  }

  public Battle against(final Ship... shipsOfSideTwo)
  {
    teamTwo = Team.of(shipsOfSideTwo);

    if (areDamagesLocalized)
    {
      fightStrategy.localizeDamages();
    }

    fightStrategy.fight(teamOne, teamTwo);

    return this;
  }

  public boolean isInTheWinningSide(final Ship ship)
  {
    return fightStrategy.isTeamOneWinning() ? teamOne.containsAsMember(ship) : teamTwo.containsAsMember(ship);
  }
}
