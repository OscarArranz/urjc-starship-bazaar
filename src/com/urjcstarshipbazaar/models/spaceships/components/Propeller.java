package com.urjcstarshipbazaar.models.spaceships.components;

public class Propeller {

  private PropellerType propellerType;
  private double maxSpeedKmh;

  public Propeller(PropellerType propellerType, double maxSpeedKmh) {
    this.propellerType = propellerType;
    this.maxSpeedKmh = maxSpeedKmh;
  }

}
