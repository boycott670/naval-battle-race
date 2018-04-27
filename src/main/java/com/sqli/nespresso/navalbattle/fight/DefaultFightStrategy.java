package com.sqli.nespresso.navalbattle.fight;

import com.sqli.nespresso.navalbattle.battle.Team;

public final class DefaultFightStrategy implements FightStrategy
{
  private boolean localizedFight = false;

  private Team teamOne, teamTwo;

  @Override
  public void fight(final Team teamOne, final Team teamTwo)
  {
    this.teamOne = teamOne;
    this.teamTwo = teamTwo;

    while (!this.teamOne.isSunk() && !this.teamTwo.isSunk())
    {
      final double teamOneDamage = this.teamOne.damage(this.teamOne.size() > this.teamTwo.size());

      final double teamTwoDamage = this.teamTwo.damage(this.teamOne.size() < this.teamTwo.size());

      if (!localizedFight)
      {
        this.teamTwo.takeDamage(teamOneDamage);
        this.teamOne.takeDamage(teamTwoDamage);
      }
      else
      {
        this.teamTwo.takeLocalizedDamage(teamOneDamage);
        this.teamOne.takeLocalizedDamage(teamTwoDamage);
      }
    }
  }

  @Override
  public boolean isTeamOneWinning()
  {
    return teamTwo.isSunk();
  }

  @Override
  public void localizeDamages()
  {
    localizedFight = true;
  }

}
