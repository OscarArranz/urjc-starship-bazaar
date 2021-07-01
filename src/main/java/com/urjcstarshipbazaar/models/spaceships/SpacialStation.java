package com.urjcstarshipbazaar.models.spaceships;

import com.urjcstarshipbazaar.models.spaceships.components.DefenseSystem;
import com.urjcstarshipbazaar.models.spaceships.components.Propeller;

import java.util.List;
import java.util.Objects;

public class SpacialStation extends Spaceship {

    private int maxPassengers;
    private List<Spaceship> spaceships;
    private List<DefenseSystem> defenses;

    public SpacialStation(int id, String registerNum, int ownerId, List<Propeller> propellers, int crewNum,
                          int maxPassengers, List<Spaceship> spaceships, List<DefenseSystem> defenses,
                          boolean isDefense) {
        super(id, registerNum, ownerId, propellers, crewNum, isDefense);
        this.maxPassengers = maxPassengers;
        this.spaceships = spaceships;
        this.defenses = defenses;
    }

    public SpacialStation(String registerNum, int ownerId, List<Propeller> propellers, int crewNum,
                          int maxPassengers, List<Spaceship> spaceships, List<DefenseSystem> defenses,
                          boolean isDefense) {
        super(registerNum, ownerId, propellers, crewNum, isDefense);
        this.maxPassengers = maxPassengers;
        this.spaceships = spaceships;
        this.defenses = defenses;
    }

    public SpacialStation() {

    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public List<Spaceship> getSpaceships() {
        return spaceships;
    }

    public List<DefenseSystem> getDefenses() {
        return defenses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SpacialStation that = (SpacialStation) o;
        boolean spaceshipsEqual = true;
        for (int i = 0; i < spaceships.size(); i++) {
            if (!spaceships.get(i).getRegisterNum().equals(that.getSpaceships().get(i).getRegisterNum()))
                spaceshipsEqual = false;
        }
        return spaceshipsEqual && Objects.equals(getRegisterNum(), that.getRegisterNum());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), spaceships);
    }
}
