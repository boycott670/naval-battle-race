package com.sqli.nespresso.navalbattle.speed;

import java.util.Arrays;
import java.util.Comparator;

import com.sqli.nespresso.navalbattle.ships.Ship;

public final class DefaultSpeedStrategy implements SpeedStrategy
{

  @Override
  public Ship fastest(Ship[] ships)
  {
    return Arrays.stream(ships)
        .min(Comparator.comparingDouble(Ship::speed))
        .get();
  }

}
