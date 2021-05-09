package com.urjcstarshipbazaar.models.spaceships.components;

public class Propeller {

    private PropellerType propellerType;
    private double maxSpeedKmh;

    public Propeller(PropellerType propellerType, double maxSpeedKmh) {
        this.propellerType = propellerType;
        this.maxSpeedKmh = maxSpeedKmh;
    }

    public Propeller() {

    }

    public PropellerType getPropellerType() {
        return propellerType;
    }

    public double getMaxSpeedKmh() {
        return maxSpeedKmh;
    }

    public void setPropellerType(PropellerType propellerType) {
        this.propellerType = propellerType;
    }

    public void setMaxSpeedKmh(double maxSpeedKmh) {
        this.maxSpeedKmh = maxSpeedKmh;
    }

}
