package com.urjcstarshipbazaar.models.builders;

import com.urjcstarshipbazaar.models.spaceships.Spaceship;
import com.urjcstarshipbazaar.models.spaceships.SpacialStation;
import com.urjcstarshipbazaar.models.spaceships.components.DefenseSystem;
import com.urjcstarshipbazaar.models.spaceships.components.Propeller;

import java.util.List;

public class SpacialStationBuilder implements SpaceshipBuilder {

    private int id;
    private String registerNum;
    private int ownerId;
    private List<Propeller> propellers;
    private int crewNum;
    private int maxPassengers;
    private List<Spaceship> spaceships;
    private List<DefenseSystem> defenses;

    public SpacialStation getSpaceship() {
        return new SpacialStation(id, registerNum, ownerId, propellers, crewNum, maxPassengers, spaceships, defenses);
    }

    @Override
    public SpacialStationBuilder setRegisterNum(String registerNum) {
        this.registerNum = registerNum.toUpperCase();
        return this;
    }

    @Override
    public SpacialStationBuilder setOwnerId(int ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    @Override
    public SpacialStationBuilder setPropellers(List<Propeller> propellers) {
        this.propellers = propellers;
        return this;
    }

    @Override
    public SpacialStationBuilder setCrewNum(int crewNum) {
        this.crewNum = crewNum;
        return this;
    }

    @Override
    public SpacialStationBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public SpacialStationBuilder setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
        return this;
    }

    public SpacialStationBuilder setSpaceships(List<Spaceship> spaceships) {
        this.spaceships = spaceships;
        return this;
    }

    public SpacialStationBuilder setDefenses(List<DefenseSystem> defenses) {
        this.defenses = defenses;
        return this;
    }
}