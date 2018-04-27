package com.sqli.nespresso.navalbattle.fight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sqli.nespresso.navalbattle.ships.Ship;

public final class DefaultFightStrategy implements FightStrategy
{
  private boolean localizedFight = false;

  private List<Ship> shipsOfSideOne, shipsOfSideTwo;

  @Override
  public void fight(Ship[] sideOne, Ship[] sideTwo)
  {
    shipsOfSideOne = new ArrayList<>(Arrays.asList(sideOne));
    shipsOfSideTwo = new ArrayList<>(Arrays.asList(sideTwo));

    while (!shipsOfSideOne.isEmpty() && !shipsOfSideTwo.isEmpty())
    {
      double totalDamageOfSideOne = shipsOfSideOne.stream()
          .mapToDouble(ship -> ship.damage() * (shipsOfSideOne.size() > shipsOfSideTwo.size() ? 1.15 : 1))
          .sum();

      double totalDamageOfSideTwo = shipsOfSideTwo.stream()
          .mapToDouble(ship -> ship.damage() * (shipsOfSideTwo.size() > shipsOfSideOne.size() ? 1.15 : 1))
          .sum();

      if (!localizedFight)
      {
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
      else
      {
        shipsOfSideTwo.get(0)
            .takeLocalizedDamage(totalDamageOfSideOne);

        shipsOfSideOne.get(0)
            .takeLocalizedDamage(totalDamageOfSideTwo);

        if (shipsOfSideTwo.get(0)
            .isDestroyedInLocalizedMode())
        {
          shipsOfSideTwo.remove(0);
        }

        if (shipsOfSideOne.get(0)
            .isDestroyedInLocalizedMode())
        {
          shipsOfSideOne.remove(0);
        }
      }
    }
  }

  @Override
  public boolean isSideOneWinningSide()
  {
    return shipsOfSideTwo.isEmpty();
  }

  @Override
  public void localizeFight()
  {
    localizedFight = true;
  }

}
