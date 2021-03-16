package com.urjcstarshipbazaar.models.spaceships;

import com.urjcstarshipbazaar.models.spaceships.components.Propeller;

import java.util.List;

public abstract class Spaceship {

  private String registerNum;
  private int ownerId;
  private List<Propeller> propellers;
  private int crewNum;

  public Spaceship(String registerNum, int ownerId, List<Propeller> propellers, int crewNum) {
    this.registerNum = registerNum;
    this.ownerId = ownerId;
    this.propellers = propellers;
    this.crewNum = crewNum;
  }

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
