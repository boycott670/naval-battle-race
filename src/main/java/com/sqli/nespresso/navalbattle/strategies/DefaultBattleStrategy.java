package com.sqli.nespresso.navalbattle.strategies;

import com.sqli.nespresso.navalbattle.ships.Side;

public final class DefaultBattleStrategy implements BattleStrategy
{
  
  private Side firstSide;
  
  private Side secondSide;

  @Override
  public void setFirstSide(Side side)
  {
    firstSide = side;
  }

  @Override
  public void setSecondSide(Side side)
  {
    secondSide = side;
  }

  @Override
  public Side winningSide()
  {
    while (firstSide.isAlive() && secondSide.isAlive())
    {
      final int firstSideDamage = firstSide.damage();
      
      final int secondSideDamage = secondSide.damage();
      
      secondSide.getTarget().takeDamage(firstSideDamage);
      
      firstSide.getTarget().takeDamage(secondSideDamage);
      
      if (!secondSide.getTarget().isAlive())
      {
        secondSide.destroyTarget();
      }

      if (!firstSide.getTarget().isAlive())
      {
        firstSide.destroyTarget();
      }
    }
    
    return firstSide.isAlive() ? firstSide : secondSide;
  }

}
