package com.sqli.nespresso.navalbattle.race;

import java.util.Arrays;
import java.util.Comparator;

import com.sqli.nespresso.navalbattle.ships.Ship;

public final class Race
{
  private final Ship[] ships;

  public Race(Ship... ships)
  {
    this.ships = ships;
  }
  
  public Ship winner()
  {
    return Arrays.stream(ships).sorted(Comparator.comparingInt(Ship::speed)).findFirst().get();
  }
}
