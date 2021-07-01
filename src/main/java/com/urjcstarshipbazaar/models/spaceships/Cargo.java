package com.urjcstarshipbazaar.models.spaceships;

import com.urjcstarshipbazaar.models.spaceships.components.DefenseSystem;
import com.urjcstarshipbazaar.models.spaceships.components.Propeller;

import java.util.List;

public class Cargo extends Spaceship {

    private double maxLoadTons;
    private DefenseSystem defense;

    public Cargo(int id, String registerNum, int ownerId, List<Propeller> propellers, int crewNum,
                 double maxLoadTons, DefenseSystem defense, boolean isDefense) {
        super(id, registerNum, ownerId, propellers, crewNum, isDefense);
        this.maxLoadTons = maxLoadTons;
        this.defense = defense;
    }

    public Cargo(String registerNum, int ownerId, List<Propeller> propellers, int crewNum,
                 double maxLoadTons, DefenseSystem defense, boolean isDefense) {
        super(registerNum, ownerId, propellers, crewNum, isDefense);
        this.maxLoadTons = maxLoadTons;
        this.defense = defense;
    }

    public Cargo() {

    }

    public double getMaxLoadTons() {
        return maxLoadTons;
    }

    public DefenseSystem getDefense() {
        return defense;
    }

}
