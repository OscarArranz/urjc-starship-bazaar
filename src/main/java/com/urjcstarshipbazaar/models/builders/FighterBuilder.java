package com.urjcstarshipbazaar.models.builders;

import com.urjcstarshipbazaar.models.spaceships.Destroyer;
import com.urjcstarshipbazaar.models.spaceships.Fighter;
import com.urjcstarshipbazaar.models.spaceships.components.DefenseSystem;
import com.urjcstarshipbazaar.models.spaceships.components.Propeller;
import com.urjcstarshipbazaar.models.spaceships.components.Weapon;

import java.util.List;

public class FighterBuilder implements SpaceshipBuilder {

    private int id;
    private String registerNum;
    private int ownerId;
    private int crewNum;
    private List<Propeller> propellers;
    private List<Weapon> weapons;
    private DefenseSystem defense;
    private boolean isDefense;

    public Fighter getSpaceship() {
        return new Fighter(id, registerNum, ownerId, propellers, weapons, defense, isDefense);
    }

    @Override
    public FighterBuilder setRegisterNum(String registerNum) {
        this.registerNum = registerNum.toUpperCase();
        return this;
    }

    @Override
    public FighterBuilder setOwnerId(int ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    @Override
    public FighterBuilder setPropellers(List<Propeller> propellers) {
        this.propellers = propellers;
        return this;
    }

    @Override
    public FighterBuilder setCrewNum(int crewNum) {
        this.crewNum = crewNum;
        return this;
    }

    @Override
    public FighterBuilder setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public FighterBuilder setIsDefense(boolean isDefense) {
        this.isDefense = isDefense;
        return this;
    }

    public FighterBuilder setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
        return this;
    }

    public FighterBuilder setDefense(DefenseSystem defense) {
        this.defense = defense;
        return this;
    }
}