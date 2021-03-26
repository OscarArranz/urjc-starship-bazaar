package com.urjcstarshipbazaar.models.spaceships;

import com.urjcstarshipbazaar.models.spaceships.components.Propeller;

import java.util.List;

public abstract class Spaceship {

  private int id;
  private String registerNum;
  private int ownerId;
  private List<Propeller> propellers;
  private int crewNum;

  public Spaceship(int id, String registerNum, int ownerId, List<Propeller> propellers, int crewNum) {
    this.id = id;
    this.registerNum = registerNum;
    this.ownerId = ownerId;
    this.propellers = propellers;
    this.crewNum = crewNum;
  }

  public int getId() { return id; }

  public String getRegisterNum() {
    return registerNum;
  }

  public int getOwnerId() {
    return ownerId;
  }

  public List<Propeller> getPropellers() {
    return propellers;
  }

  public int getCrewNum() {
    return crewNum;
  }

}
