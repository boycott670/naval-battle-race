package com.sqli.nespresso.navalbattle.ships;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Queue;

public final class Side
{
  private final Collection<Ship> initialMembers;
  
  private final Queue<Ship> members;
  
  public Side(final Ship[] members)
  {
    final List<Ship> membersAsList = Arrays.asList(members);
    
    this.members = new ArrayDeque<>(membersAsList);
    
    initialMembers = membersAsList;
  }
  
  public int damage()
  {
    return members.stream().mapToInt(Ship::damage).sum();
  }
  
  public int size()
  {
    return members.size();
  }
  
  public Ship getTarget()
  {
    return members.peek();
  }
  
  public void destroyTarget()
  {
    members.remove(getTarget());
  }
  
  public boolean isAlive()
  {
    return !members.isEmpty();
  }
  
  public boolean isInTheWinningSide(final Ship ship)
  {
    return initialMembers.contains(ship);
  }
}
