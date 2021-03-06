package com.urjcstarshipbazaar.models.spaceships;

import com.urjcstarshipbazaar.models.spaceships.components.Propeller;

import java.util.List;
import java.util.Objects;

public abstract class Spaceship {

    private int id;
    private String registerNum;
    private int ownerId;
    private List<Propeller> propellers;
    private int crewNum;
    private boolean isDefense;

    public Spaceship(int id, String registerNum, int ownerId, List<Propeller> propellers, int crewNum
            , boolean isDefense) {
        this.id = id;
        this.registerNum = registerNum;
        this.ownerId = ownerId;
        this.propellers = propellers;
        this.crewNum = crewNum;
        this.isDefense = isDefense;
    }

    public Spaceship(String registerNum, int ownerId, List<Propeller> propellers, int crewNum, boolean isDefense) {
        this.registerNum = registerNum;
        this.ownerId = ownerId;
        this.propellers = propellers;
        this.crewNum = crewNum;
        this.isDefense = isDefense;
    }

    public Spaceship() {
    }


    public int getId() {
        return id;
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

    public boolean isDefense() {
        return this.isDefense;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spaceship spaceship = (Spaceship) o;
        return Objects.equals(registerNum, spaceship.registerNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registerNum);
    }
}
