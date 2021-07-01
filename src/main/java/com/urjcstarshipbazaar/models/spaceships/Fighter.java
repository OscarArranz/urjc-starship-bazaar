package com.urjcstarshipbazaar.models.spaceships;

import com.urjcstarshipbazaar.models.spaceships.components.DefenseSystem;
import com.urjcstarshipbazaar.models.spaceships.components.Propeller;
import com.urjcstarshipbazaar.models.spaceships.components.Weapon;

import java.util.List;

public class Fighter extends Spaceship {

    private List<Weapon> weapons;
    private DefenseSystem defense;

    public Fighter(int id, String registerNum, int ownerId, List<Propeller> propellers,
                   List<Weapon> weapons, DefenseSystem defense, boolean isDefense) {
        super(id, registerNum, ownerId, propellers, 1, isDefense);
        this.weapons = weapons;
        this.defense = defense;
    }

    public Fighter(String registerNum, int ownerId, List<Propeller> propellers,
                   List<Weapon> weapons, DefenseSystem defense, boolean isDefense) {
        super(registerNum, ownerId, propellers, 1, isDefense);
        this.weapons = weapons;
        this.defense = defense;
    }

    public Fighter() {

    }

    @Override
    public String toString() {
        return getRegisterNum() + " - Fighter";
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public DefenseSystem getDefense() {
        return defense;
    }

}
