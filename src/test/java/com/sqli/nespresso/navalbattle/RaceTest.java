package com.sqli.nespresso.navalbattle;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.sqli.nespresso.navalbattle.race.Race;
import com.sqli.nespresso.navalbattle.ships.Clipper;
import com.sqli.nespresso.navalbattle.ships.Ship;

public class RaceTest
{
  @Test
  public void fastestShouldWin()
  {

    Ship a = new Ship(20000, 2); // 20000 tons of displacement, 2 masts
    Ship b = new Ship(7500, 1);

    Race race = new Race(a, b);
    assertThat(race.winner()).isEqualTo(b);
  }

  @Test
  public void clipperGoesFaster()
  {

    Ship a = new Ship(20000, 2);
    Ship b = new Ship(7500, 1);
    Clipper c = new Clipper(18000, 2);

    Race race = new Race(a, b, c);
    assertThat(race.winner()).isEqualTo(c);
  }
}
