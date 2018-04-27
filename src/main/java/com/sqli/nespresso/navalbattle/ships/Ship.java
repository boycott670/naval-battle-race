package com.sqli.nespresso.navalbattle.ships;

public class Ship
{
  private static final int DAMAGE_PER_CANON = 200;

  private static final int HP_PER_HULL = 1;
  private static final int HP_PER_CANON = 100;
  private static final int HP_PER_MAST = 1000;
  
  private static final double SPEED_PENALTY_PER_CANON = .5 / 100;

  private double displacement;
  private double mast;
  private double canon;

  private double hp;

  public Ship(int displacement, int mast, int canon)
  {
    this.displacement = displacement;
    this.mast = mast;
    this.canon = canon;

    hp = this.displacement * HP_PER_HULL + this.mast * HP_PER_MAST + this.canon * HP_PER_CANON;
  }

  public Ship(int displacement, int mast)
  {
    this(displacement, mast, 0);
  }

  public final double damage()
  {
    return Math.max(0, canon * DAMAGE_PER_CANON);
  }

  public final void takeDamage(final double damage)
  {
    hp -= damage;
  }

  public final void takeLocalizedDamage(final double damage)
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
      displacement -= damage / HP_PER_HULL;
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

  public double speed()
  {
    return displacement / mast * (1 + SPEED_PENALTY_PER_CANON * canon);
  }
}
