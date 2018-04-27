package com.sqli.nespresso.navalbattle.battle;

import java.util.Arrays;

import com.sqli.nespresso.navalbattle.fight.DefaultFightStrategy;
import com.sqli.nespresso.navalbattle.fight.FightStrategy;
import com.sqli.nespresso.navalbattle.ships.Ship;

public final class Battle
{
  public static final byte LOCALIZED_DAMAGES = 1;

  private final FightStrategy fightStrategy = new DefaultFightStrategy();

  private Ship[] sideOne, sideTwo;

  private final boolean isLocalized;

  public Battle()
  {
    isLocalized = false;
  }

  public Battle(final byte isLocalizedBattle)
  {
    isLocalized = true;
  }

  public Battle side(final Ship... shipsOfSideOne)
  {
    sideOne = shipsOfSideOne;
    return this;
  }

  public Battle against(final Ship... shipsOfSideTwo)
  {
    sideTwo = shipsOfSideTwo;

    if (isLocalized)
    {
      fightStrategy.localizeFight();
    }

    fightStrategy.fight(sideOne, sideTwo);

    return this;
  }

  public boolean isInTheWinningSide(final Ship ship)
  {
    return fightStrategy.isSideOneWinningSide() ? Arrays.asList(sideOne)
        .contains(ship)
        : Arrays.asList(sideTwo)
            .contains(ship);
  }
}
