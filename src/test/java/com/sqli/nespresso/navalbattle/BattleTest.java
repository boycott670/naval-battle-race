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
}
