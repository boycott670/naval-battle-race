package com.sqli.nespresso.navalbattle.strategies;

import java.util.function.BiFunction;

import com.sqli.nespresso.navalbattle.ships.Side;

public final class DefaultBattleStrategy implements BattleStrategy
{
  
  private final BiFunction<Side, Side, Double> damageEvaluator;
  
  private Side firstSide;
  
  private Side secondSide;
  
  public DefaultBattleStrategy()
  {
    damageEvaluator = (attacker, defender) -> attacker.damage() * (attacker.size() > defender.size() ? 1.15 * defender.size() : 1);
  }

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
      double firstSideDamage = damageEvaluator.apply(firstSide, secondSide);
      
      double secondSideDamage = damageEvaluator.apply(secondSide, firstSide);
      
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
