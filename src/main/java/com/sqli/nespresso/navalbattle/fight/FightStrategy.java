package com.sqli.nespresso.navalbattle.fight;

import com.sqli.nespresso.navalbattle.ships.Ship;

public interface FightStrategy
{
  void fight(final Ship[] sideOne, final Ship[] sideTwo);

  boolean isSideOneWinningSide();
}
