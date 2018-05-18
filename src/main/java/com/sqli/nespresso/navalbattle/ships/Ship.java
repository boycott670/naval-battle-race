package com.sqli.nespresso.navalbattle.ships;

public final class Ship
{
  private static final int DAMAGE_PER_CANON = 200;
  
  private static final int HP_PER_DISPACEMENT = 1;
  private static final int HP_PER_MAST = 1000;
  private static final int HP_PER_CANON = 100;
  
  private final int displacement;
  private final int mast;
  private final int canon;
  
  private int hp;
  
  public Ship(int displacement, int mast, int canon)
  {
    this.displacement = displacement;
    this.mast = mast;
    this.canon = canon;
    
    hp = this.displacement * HP_PER_DISPACEMENT + this.mast * HP_PER_MAST + this.canon * HP_PER_CANON;
  }
  
  int damage()
  {
    return DAMAGE_PER_CANON * canon;
  }
  
  public void takeDamage(final int damage)
  {
    hp -= damage;
  }
  
  public boolean isAlive()
  {
    return hp > 0;
  }
}
