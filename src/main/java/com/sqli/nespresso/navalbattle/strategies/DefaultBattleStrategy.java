package com.sqli.nespresso.navalbattle.strategies;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import com.sqli.nespresso.navalbattle.ships.Ship;
import com.sqli.nespresso.navalbattle.ships.Side;

public final class DefaultBattleStrategy implements BattleStrategy
{
  
  private final BiFunction<Side, Side, Double> damageEvaluator;
  
  private Side firstSide;
  
  private Side secondSide;
  
  private boolean isLocalizedBattle = false;
  
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
  public void localizeBattle()
  {
    isLocalizedBattle = true;
  }
  
  private void genericBlow(final double firstSideDamage, final double secondSideDamage, final BiConsumer<Ship, Double> takeDamage, final Function<Ship, Boolean> isAlive)
  {
    takeDamage.accept(secondSide.getTarget(), firstSideDamage);
    
    takeDamage.accept(firstSide.getTarget(), secondSideDamage);
    
    if (!isAlive.apply(secondSide.getTarget()))
    {
      secondSide.destroyTarget();
    }
    
    if (!isAlive.apply(firstSide.getTarget()))
    {
      firstSide.destroyTarget();
    }
  }

  @Override
  public Side winningSide()
  {
    while (firstSide.isAlive() && secondSide.isAlive())
    {
      double firstSideDamage = damageEvaluator.apply(firstSide, secondSide);
      
      double secondSideDamage = damageEvaluator.apply(secondSide, firstSide);
      
      if (isLocalizedBattle)
      {
        genericBlow(firstSideDamage, secondSideDamage, Ship::takeDamageInLocalizedMode, Ship::isAliveInLocalizedMode);
      }
      else
      {
        genericBlow(firstSideDamage, secondSideDamage, Ship::takeDamage, Ship::isAlive);
      }
    }
    
    return firstSide.isAlive() ? firstSide : secondSide;
  }

}
