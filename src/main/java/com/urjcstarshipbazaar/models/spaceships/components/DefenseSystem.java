package com.urjcstarshipbazaar.models.spaceships.components;

public abstract class DefenseSystem {

    private double maxDamageGj;

    public DefenseSystem(double maxDamageGj) {
        this.maxDamageGj = maxDamageGj;
    }

    public DefenseSystem() {

    }

    public double getMaxDamageGj() {
        return maxDamageGj;
    }

    public void setMaxDamageGj(double maxDamageGj) {
        this.maxDamageGj = maxDamageGj;
    }
}
