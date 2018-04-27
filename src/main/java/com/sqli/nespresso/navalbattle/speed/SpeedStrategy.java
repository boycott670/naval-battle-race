package com.sqli.nespresso.navalbattle.speed;

import com.sqli.nespresso.navalbattle.ships.Ship;

public interface SpeedStrategy
{
  Ship fastest(final Ship[] ships);
}
