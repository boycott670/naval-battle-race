package com.sqli.nespresso.navalbattle.battle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import com.sqli.nespresso.navalbattle.ships.Ship;

public final class Team
{
  static Team of(final Ship... ships)
  {
    return new Team(ships);
  }

  private final Collection<Ship> immutableMembers;
  private final List<Ship> members;

  private Team(final Ship... ships)
  {
    members = new ArrayList<>(Arrays.asList(ships));
    immutableMembers = Collections.unmodifiableList(members);
  }

  public boolean isSunk()
  {
    return members.isEmpty();
  }

  public double damage(final boolean withBonus)
  {
    return members.stream()
        .mapToDouble(member -> member.damage() * (withBonus ? 1.15 : 1))
        .sum();
  }

  private void genericTakeDamage(final Consumer<? super Ship> action, final Predicate<? super Ship> isToDestroy)
  {
    action.accept(members.get(0));

    if (isToDestroy.test(members.get(0)))
    {
      members.remove(0);
    }
  }

  public void takeDamage(final double damage)
  {
    genericTakeDamage(ship -> ship.takeDamage(damage), Ship::isDestroyed);
  }

  public void takeLocalizedDamage(final double damage)
  {
    genericTakeDamage(ship -> ship.takeLocalizedDamage(damage), Ship::isDestroyedInLocalizedMode);
  }

  public int size()
  {
    return members.size();
  }

  boolean containsAsMember(final Ship ship)
  {
    return immutableMembers.contains(ship);
  }
}
