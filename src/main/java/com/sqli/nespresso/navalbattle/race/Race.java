package com.sqli.nespresso.navalbattle.race;

import com.sqli.nespresso.navalbattle.ships.Ship;
import com.sqli.nespresso.navalbattle.speed.DefaultSpeedStrategy;
import com.sqli.nespresso.navalbattle.speed.SpeedStrategy;

public final class Race
{
  private final SpeedStrategy speedStrategy = new DefaultSpeedStrategy();

  private final Ship[] ships;

  public Race(final Ship... ships)
  {
    this.ships = ships;
  }

  public Ship winner()
  {
    return speedStrategy.fastest(ships);
  }
}
