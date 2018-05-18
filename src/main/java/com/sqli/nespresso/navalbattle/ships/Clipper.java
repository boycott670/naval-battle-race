package com.sqli.nespresso.navalbattle.ships;

public final class Clipper extends Ship
{
  
  private static final double FASTER_SPEED_RATE = .2;

  public Clipper(int displacement, int mast)
  {
    super(displacement, mast);
  }

  @Override
  public double speed()
  {
    return super.speed() * (1 - FASTER_SPEED_RATE);
  }

}
