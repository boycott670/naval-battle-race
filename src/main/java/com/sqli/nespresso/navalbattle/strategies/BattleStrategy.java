package com.sqli.nespresso.navalbattle.strategies;

import com.sqli.nespresso.navalbattle.ships.Side;

public interface BattleStrategy
{
  void setFirstSide(Side side);
  
  void setSecondSide(Side side);
  
  void localizeBattle();
  
  Side winningSide();
}
