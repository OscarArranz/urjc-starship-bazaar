package com.urjcstarshipbazaar.models.spaceships.components;

public class Shield extends DefenseSystem {

    private double requiredEnergyGj;

    public Shield(double maxDamageGj, double requiredEnergyGj) {
        super(maxDamageGj);
        this.requiredEnergyGj = requiredEnergyGj;
    }

    public Shield() {

    }

  public double getRequiredEnergyGj() {
    return requiredEnergyGj;
  }
}
