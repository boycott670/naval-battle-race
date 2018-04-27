package com.sqli.nespresso.navalbattle.ships;

public class Ship
{
  private double displacement;
  private double mast;
  private double canon;

  private double hp;

  public Ship(int displacement, int mast, int canon)
  {
    this.displacement = displacement;
    this.mast = mast * 1000;
    this.canon = canon * 100;

    hp = this.displacement + this.mast + this.canon;
  }

  public final double damage()
  {
    return Math.max(0, 2 * canon);
  }

  public final void takeDamage(final double damage)
  {
    hp -= damage;
  }

  public final void takeLocalizedDamage(final double damage)
  {
    if (mast > 0)
    {
      mast -= damage;
    }
    else if (canon > 0)
    {
      canon -= damage;
    }
    else
    {
      displacement -= damage;
    }
  }

  public final boolean isDestroyed()
  {
    return hp <= 0;
  }

  public final boolean isDestroyedInLocalizedMode()
  {
    return displacement <= 0;
  }
}
