package com.urjcstarshipbazaar.models.spaceships.components;

public class Armor extends DefenseSystem {

    private String material;
    private double weightTons;

    public Armor(double maxDamageGj, String material, double weightTons) {
        super(maxDamageGj);
        this.material = material;
        this.weightTons = weightTons;
    }

    public Armor() {

    }

    public String getMaterial() {
        return material;
    }

    public double getWeightTons() {
        return weightTons;
    }
}
