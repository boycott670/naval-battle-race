package com.sqli.nespresso.navalbattle.fight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sqli.nespresso.navalbattle.ships.Ship;

public final class DefaultFightStrategy implements FightStrategy
{
  private List<Ship> shipsOfSideOne, shipsOfSideTwo;

  @Override
  public void fight(Ship[] sideOne, Ship[] sideTwo)
  {
    shipsOfSideOne = new ArrayList<>(Arrays.asList(sideOne));
    shipsOfSideTwo = new ArrayList<>(Arrays.asList(sideTwo));

    while (!shipsOfSideOne.isEmpty() && !shipsOfSideTwo.isEmpty())
    {
      int totalDamageOfSideOne = shipsOfSideOne.stream()
          .mapToInt(Ship::damage)
          .sum();

      int totalDamageOfSideTwo = shipsOfSideTwo.stream()
          .mapToInt(Ship::damage)
          .sum();

      if (shipsOfSideOne.size() > shipsOfSideTwo.size())
      {
        totalDamageOfSideOne *= 1.15 * shipsOfSideTwo.size();
      }

      if (shipsOfSideOne.size() < shipsOfSideTwo.size())
      {
        totalDamageOfSideTwo *= 1.15 * shipsOfSideOne.size();
      }

      shipsOfSideTwo.get(0)
          .takeDamage(totalDamageOfSideOne);

      shipsOfSideOne.get(0)
          .takeDamage(totalDamageOfSideTwo);

      if (shipsOfSideTwo.get(0)
          .isDestroyed())
      {
        shipsOfSideTwo.remove(0);
      }

      if (shipsOfSideOne.get(0)
          .isDestroyed())
      {
        shipsOfSideOne.remove(0);
      }
    }
  }

  @Override
  public boolean isSideOneWinningSide()
  {
    return shipsOfSideTwo.isEmpty();
  }

}
