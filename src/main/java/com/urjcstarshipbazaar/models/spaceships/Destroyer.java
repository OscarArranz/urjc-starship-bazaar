package com.urjcstarshipbazaar.models.spaceships;

import com.urjcstarshipbazaar.models.spaceships.components.DefenseSystem;
import com.urjcstarshipbazaar.models.spaceships.components.Propeller;
import com.urjcstarshipbazaar.models.spaceships.components.Weapon;

import java.util.List;

public class Destroyer extends Spaceship {

    private List<Weapon> weapons;
    private List<DefenseSystem> defenses;

    public Destroyer(int id, String registerNum, int ownerId, List<Propeller> propellers, int crewNum,
                     List<Weapon> weapons, List<DefenseSystem> defenses) {
        super(id, registerNum, ownerId, propellers, crewNum);
        this.weapons = weapons;
        this.defenses = defenses;
    }

    public Destroyer(String registerNum, int ownerId, List<Propeller> propellers, int crewNum,
                     List<Weapon> weapons, List<DefenseSystem> defenses) {
        super(registerNum, ownerId, propellers, crewNum);
        this.weapons = weapons;
        this.defenses = defenses;
    }

    public Destroyer() {

    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public List<DefenseSystem> getDefenses() {
        return defenses;
    }

}
