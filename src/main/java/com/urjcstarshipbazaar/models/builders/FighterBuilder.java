package com.urjcstarshipbazaar.models.builders;

import com.urjcstarshipbazaar.models.spaceships.Fighter;
import com.urjcstarshipbazaar.models.spaceships.components.DefenseSystem;
import com.urjcstarshipbazaar.models.spaceships.components.Propeller;
import com.urjcstarshipbazaar.models.spaceships.components.Weapon;

import java.util.List;

public class FighterBuilder implements SpaceshipBuilder {

  private String registerNum;
  private int ownerId;
  private List<Propeller> propellers;
  private List<Weapon> weapons;
  private DefenseSystem defense;

  public Fighter build() {
    return new Fighter(registerNum, ownerId, propellers, weapons, defense);
  }

  @Override
  public FighterBuilder setRegisterNum(String registerNum) throws RuntimeException {
    registerNum = registerNum.toUpperCase();
    if (registerNum.matches("[A-Z]\\d{4}[A-Z]{3}"))
      this.registerNum = registerNum;
    else throw new RuntimeException("Not a valid Register Number!");
    return this;
  }

  @Override
  public FighterBuilder setOwnerId(int ownerId) {
    this.ownerId = ownerId;
    return this;
  }

  @Override
  public FighterBuilder setPropellers(List<Propeller> propellers) {
    this.propellers = propellers;
    return this;
  }

  public FighterBuilder setWeapons(List<Weapon> weapons) {
    this.weapons = weapons;
    return this;
  }

  public FighterBuilder setDefense(DefenseSystem defense) {
    this.defense = defense;
    return this;
  }
}