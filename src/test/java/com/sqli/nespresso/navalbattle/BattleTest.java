package com.sqli.nespresso.navalbattle;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.sqli.nespresso.navalbattle.battle.Battle;
import com.sqli.nespresso.navalbattle.ships.Ship;

public class BattleTest
{
  @Test
  public void fightWithSameFirePower()
  {
    Ship a = new Ship(7500, 1, 16);
    Ship b = new Ship(12000, 1, 16);

    Battle battle = new Battle().side(a)
        .against(b);
    assertThat(battle.isInTheWinningSide(b)).isTrue();
  }

  @Test
  public void fightWithDifferentFirePower()
  {
    Ship a = new Ship(2000, 1, 15); // 3k damage, 4500 hp (2000 + 1000 + 1500), can take 3 hits [should win]
    // 3k damage, 2000 hp (just displacement), can take 1 hits
    Ship b = new Ship(3100, 1, 10); // 2k damage, 5100 hp (3100 + 1000 + 1000), can take 2 hits
    // 2k damage, 3000 hp (just displacement), can take 2 hits [wins]

    Battle battle = new Battle().side(a)
        .against(b);
    assertThat(battle.isInTheWinningSide(a)).isTrue();
  }

  @Test
  public void packfight()
  {
    Ship a = new Ship(65000, 3, 64);
    Ship b = new Ship(23000, 1, 24);
    Ship c = new Ship(23000, 1, 24);
    Ship d = new Ship(23000, 1, 24);

    Battle battle = new Battle().side(a)
        .against(b, c, d);
    assertThat(battle.isInTheWinningSide(b)).isTrue();
    assertThat(battle.isInTheWinningSide(c)).isTrue();
    assertThat(battle.isInTheWinningSide(d)).isTrue();
  }

  @Test
  public void packfightLightBoat()
  {
    Ship a = new Ship(65000, 3, 64);
    Ship b = new Ship(15000, 1, 24);
    Ship c = new Ship(15000, 1, 24);
    Ship d = new Ship(15000, 1, 24);
    Battle battle = new Battle().side(a)
        .against(b, c, d);
    assertThat(battle.isInTheWinningSide(a)).isTrue();
  }

  @Test
  public void packfightLightBoatLocalized()
  {
    Ship a = new Ship(65000, 3, 64);
    Ship b = new Ship(23000, 1, 24);
    Ship c = new Ship(23000, 1, 24);
    Ship d = new Ship(23000, 1, 24);

    Battle battle = new Battle(Battle.LOCALIZED_DAMAGES).side(a)
        .against(b, c, d);
    assertThat(battle.isInTheWinningSide(b)).isTrue();
    assertThat(battle.isInTheWinningSide(c)).isTrue();
    assertThat(battle.isInTheWinningSide(d)).isTrue();
  }
}
