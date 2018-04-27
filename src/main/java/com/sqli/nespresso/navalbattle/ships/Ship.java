package com.sqli.nespresso.navalbattle.ships;

public class Ship
{
  private int displacement;
  private int mast;
  private int canon;

  private int hp;

  public Ship(int displacement, int mast, int canon)
  {
    this.displacement = displacement;
    this.mast = mast;
    this.canon = canon;

    hp = this.displacement + (this.mast * 1000) + (this.canon * 100);
  }

  public final int damage()
  {
    return Math.max(0, 200 * canon);
  }

  public final void takeDamage(final int damage)
  {
    hp -= damage;
  }

  public final boolean isDestroyed()
  {
    return hp <= 0;
  }
}
