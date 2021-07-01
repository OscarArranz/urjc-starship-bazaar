package com.urjcstarshipbazaar.models.spaceships;

import com.urjcstarshipbazaar.models.spaceships.components.DefenseSystem;
import com.urjcstarshipbazaar.models.spaceships.components.Propeller;
import com.urjcstarshipbazaar.models.spaceships.components.Weapon;

import java.util.List;

public class Destroyer extends Spaceship {

    private Weapon weapon;
    private List<DefenseSystem> defenses;

    public Destroyer(int id, String registerNum, int ownerId, List<Propeller> propellers, int crewNum,
                     Weapon weapon, List<DefenseSystem> defenses, boolean isDefense) {
        super(id, registerNum, ownerId, propellers, crewNum, isDefense);
        this.weapon = weapon;
        this.defenses = defenses;
    }

    public Destroyer(String registerNum, int ownerId, List<Propeller> propellers, int crewNum,
                     Weapon weapon, List<DefenseSystem> defenses, boolean isDefense) {
        super(registerNum, ownerId, propellers, crewNum, isDefense);
        this.weapon = weapon;
        this.defenses = defenses;
    }

    public Destroyer() {

    }

    public Weapon getWeapon() {
        return weapon;
    }

    public List<DefenseSystem> getDefenses() {
        return defenses;
    }

}
