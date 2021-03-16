package com.urjcstarshipbazaar.models.spaceships.components;

public class Weapon {

  private WeaponType weaponType;
  private double potencyGj;

  public Weapon(WeaponType weaponType, double potencyGj) {
    this.weaponType = weaponType;
    this.potencyGj = potencyGj;
  }

}
