package com.sqli.nespresso.navalbattle.ships;

public class Ship
{
  private static final double PENALTY_SPEED_PER_CANON = .005;
  
  private static final int DAMAGE_PER_CANON = 200;
  
  private static final int HP_PER_DISPACEMENT = 1;
  private static final int HP_PER_MAST = 1000;
  private static final int HP_PER_CANON = 100;
  
  private int displacement;
  private int mast;
  private int canon;
  
  private double hp;
  
  public Ship(int displacement, int mast, int canon)
  {
    this.displacement = displacement;
    this.mast = mast;
    this.canon = canon;
    
    hp = this.displacement * HP_PER_DISPACEMENT + this.mast * HP_PER_MAST + this.canon * HP_PER_CANON;
  }
  
  public Ship(int displacement, int mast)
  {
    this(displacement, mast, 0);
  }
  
  int damage()
  {
    return canon <= 0 ? 0 : DAMAGE_PER_CANON * canon;
  }
  
  public void takeDamage(final double damage)
  {
    hp -= damage;
  }
  
  public void takeDamageInLocalizedMode(final double damage)
  {
    if (mast > 0)
    {
      mast -= damage / HP_PER_MAST;
    }
    else if (canon > 0)
    {
      canon -= damage / HP_PER_CANON;
    }
    else
    {
      displacement -= damage / HP_PER_DISPACEMENT;
    }
  }
  
  public boolean isAlive()
  {
    return hp > 0;
  }
  
  public boolean isAliveInLocalizedMode()
  {
    return displacement > 0;
  }
  
  public double speed()
  {
    final int speed = displacement / mast;
    
    return speed + speed * PENALTY_SPEED_PER_CANON * canon;
  }
}
