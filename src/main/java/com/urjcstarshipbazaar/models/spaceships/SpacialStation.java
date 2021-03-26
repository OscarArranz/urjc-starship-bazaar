package com.urjcstarshipbazaar.models.spaceships;

import com.urjcstarshipbazaar.models.spaceships.components.DefenseSystem;
import com.urjcstarshipbazaar.models.spaceships.components.Propeller;

import java.util.List;

public class SpacialStation extends Spaceship {

  private int maxPassengers;
  private List<Spaceship> spaceships;
  private List<DefenseSystem> defenses;

  public SpacialStation(int id, String registerNum, int ownerId, List<Propeller> propellers, int crewNum,
                        int maxPassengers, List<Spaceship> spaceships, List<DefenseSystem> defenses) {
    super(id, registerNum, ownerId, propellers, crewNum);
    this.maxPassengers = maxPassengers;
    this.spaceships = spaceships;
    this.defenses = defenses;
  }

}
