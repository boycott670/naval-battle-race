package com.sqli.nespresso.navalbattle.battle;

import com.sqli.nespresso.navalbattle.ships.Ship;
import com.sqli.nespresso.navalbattle.ships.Side;
import com.sqli.nespresso.navalbattle.strategies.BattleStrategy;
import com.sqli.nespresso.navalbattle.strategies.DefaultBattleStrategy;

public final class Battle
{
  private BattleStrategy strategy = new DefaultBattleStrategy();
  
  private Side winningSide;
  
  public Battle side(final Ship... members)
  {
    strategy.setFirstSide(new Side(members));
    
    return this;
  }
  
  public Battle against(final Ship... members)
  {
    strategy.setSecondSide(new Side(members));
    
    winningSide = strategy.winningSide();
    
    return this;
  }
  
  public boolean isInTheWinningSide(final Ship ship)
  {
    return winningSide.isInTheWinningSide(ship);
  }
}
