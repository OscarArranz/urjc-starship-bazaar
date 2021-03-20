package com.urjcstarshipbazaar.models.spaceships;

import com.urjcstarshipbazaar.models.spaceships.components.DefenseSystem;
import com.urjcstarshipbazaar.models.spaceships.components.Propeller;
import com.urjcstarshipbazaar.models.spaceships.components.Weapon;

import java.util.List;

public class Fighter extends Spaceship {

  private List<Weapon> weapons;
  private DefenseSystem defense;

  public Fighter(String registerNum, int ownerId, List<Propeller> propellers,
                 List<Weapon> weapons, DefenseSystem defense) {
    super(registerNum, ownerId, propellers, 1);
    this.weapons = weapons;
    this.defense = defense;
  }

  @Override
  public String toString() {
    return getRegisterNum() + " - Fighter";
  }
}