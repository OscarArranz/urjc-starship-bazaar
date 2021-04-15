package com.urjcstarshipbazaar.models.builders;

import com.urjcstarshipbazaar.models.spaceships.Cargo;
import com.urjcstarshipbazaar.models.spaceships.components.DefenseSystem;
import com.urjcstarshipbazaar.models.spaceships.components.Propeller;

import java.util.List;


public class CargoBuilder implements SpaceshipBuilder {

  private int id;
  private String registerNum;
  private int ownerId;
  private List<Propeller> propellers;
  private int crewNum;
  private double maxLoadTons;
  private DefenseSystem defense;

  public Cargo getSpaceship() {
    return new Cargo(id, registerNum, ownerId, propellers, crewNum, maxLoadTons, defense);
  }

  public CargoBuilder setRegisterNum(String registerNum) {
    this.registerNum = registerNum;
    return this;
  }

  public CargoBuilder setOwnerId(int ownerId) {
    this.ownerId = ownerId;
    return this;
  }

  public CargoBuilder setPropellers(List<Propeller> propellers) {
    this.propellers = propellers;
    return this;
  }

  @Override
  public CargoBuilder setCrewNum(int crewNum) {
    this.crewNum = crewNum;
    return this;
  }

  @Override
  public SpaceshipBuilder setId(int id) {
    this.id = id;
    return this;
  }

  public void setMaxLoadTons(double maxLoadTons) {
    this.maxLoadTons = maxLoadTons;
  }

  public void setDefense(DefenseSystem defense) {
    this.defense = defense;
  }

}