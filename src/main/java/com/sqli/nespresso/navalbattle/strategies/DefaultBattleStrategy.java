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
      double firstSideDamage = firstSide.damage();
      
      double secondSideDamage = secondSide.damage();
      
      if (firstSide.size() > secondSide.size())
      {
        firstSideDamage += firstSideDamage * 1.15 * (firstSide.size() - secondSide.size());
      }
      else if (secondSide.size() > firstSide.size())
      {
        secondSideDamage += secondSideDamage * 1.15 * (secondSide.size() - firstSide.size());
      }
      
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
