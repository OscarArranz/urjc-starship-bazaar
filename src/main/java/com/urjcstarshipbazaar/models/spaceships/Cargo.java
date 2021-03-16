package com.urjcstarshipbazaar.models.spaceships;

import com.urjcstarshipbazaar.models.spaceships.components.DefenseSystem;
import com.urjcstarshipbazaar.models.spaceships.components.Propeller;

import java.util.List;

public class Cargo extends Spaceship {

  private double maxLoadTons;
  private DefenseSystem defense;

  public Cargo(String registerNum, int ownerId, List<Propeller> propellers, int crewNum,
               double maxLoadTons, DefenseSystem defense) {
    super(registerNum, ownerId, propellers, crewNum);
    this.maxLoadTons = maxLoadTons;
    this.defense = defense;
  }

}
