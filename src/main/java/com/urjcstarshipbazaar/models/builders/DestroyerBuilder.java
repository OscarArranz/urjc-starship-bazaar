package com.urjcstarshipbazaar.models.builders;

import com.urjcstarshipbazaar.models.spaceships.Destroyer;
import com.urjcstarshipbazaar.models.spaceships.components.DefenseSystem;
import com.urjcstarshipbazaar.models.spaceships.components.Propeller;
import com.urjcstarshipbazaar.models.spaceships.components.Weapon;

import java.util.List;

public class DestroyerBuilder implements SpaceshipBuilder {

    private int id;
    private String registerNum;
    private int ownerId;
    private List<Propeller> propellers;
    private int crewNum;
    private List<Weapon> weapons;
    private List<DefenseSystem> defenses;

    public Destroyer getSpaceship() {
        return new Destroyer(id, registerNum, ownerId, propellers, crewNum, weapons, defenses);
    }

    @Override
    public DestroyerBuilder setRegisterNum(String registerNum) {
        this.registerNum = registerNum;
        return this;
    }

    @Override
    public DestroyerBuilder setOwnerId(int ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    @Override
    public DestroyerBuilder setCrewNum(int crewNum) {
        this.crewNum = crewNum;
        return this;
    }

    @Override
    public SpaceshipBuilder setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public DestroyerBuilder setPropellers(List<Propeller> propellers) {
        this.propellers = propellers;
        return this;
    }

    public DestroyerBuilder setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
        return this;
    }

    public DestroyerBuilder setDefenses(List<DefenseSystem> defenses) {
        this.defenses = defenses;
        return this;
    }
}