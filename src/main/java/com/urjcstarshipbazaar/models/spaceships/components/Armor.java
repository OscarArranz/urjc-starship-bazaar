package com.urjcstarshipbazaar.models.spaceships.components;

public class Armor extends DefenseSystem {

  private String material;
  private double weigthTons;

  public Armor(double maxDamageGj, String material, double weigthTons) {
    super(maxDamageGj);
    this.material = material;
    this.weigthTons = weigthTons;
  }

}
