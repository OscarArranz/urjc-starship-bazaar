package com.urjcstarshipbazaar.dao;

import com.urjcstarshipbazaar.dao.exceptions.DAOException;
import com.urjcstarshipbazaar.models.builders.CargoBuilder;
import com.urjcstarshipbazaar.models.builders.DestroyerBuilder;
import com.urjcstarshipbazaar.models.builders.FighterBuilder;
import com.urjcstarshipbazaar.models.builders.SpacialStationBuilder;
import com.urjcstarshipbazaar.models.spaceships.*;
import com.urjcstarshipbazaar.models.spaceships.components.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SpaceshipDaoTest {

    @Test
    public void saveGetAndDeleteCargo() {
        SpaceshipDao spaceshipDao = new SpaceshipDao();
        List<Propeller> propellers = new ArrayList<>();
        propellers.add(new Propeller(PropellerType.CURVATURE_ENGINE, 800));
        propellers.add(new Propeller(PropellerType.FTL_ENGINE, 500));
        DefenseSystem defense = new Armor(30, "Graphite", 10);
        Cargo cargo = new CargoBuilder()
                .setRegisterNum("A1111AAA")
                .setOwnerId(1)
                .setPropellers(propellers)
                .setCrewNum(8)
                .setMaxLoadTons(20)
                .setDefense(defense)
                .getSpaceship();

        Cargo receivedCargo;
        try {
            spaceshipDao.saveSpaceship(cargo);
            receivedCargo = (Cargo) spaceshipDao.getSpaceshipByRegisterNum(cargo.getRegisterNum());
            assertEquals(receivedCargo, cargo);
            spaceshipDao.deleteSpaceshipByRegisterNum(receivedCargo.getRegisterNum());
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveDestroyer() {
        SpaceshipDao spaceshipDao = new SpaceshipDao();
        List<Propeller> propellers = new ArrayList<>();
        propellers.add(new Propeller(PropellerType.CURVATURE_ENGINE, 800));
        propellers.add(new Propeller(PropellerType.FTL_ENGINE, 500));
        List<DefenseSystem> defenses = new ArrayList<>();
        defenses.add(new Armor(30, "Graphite", 10));
        defenses.add(new Shield(50, 70));
        List<Weapon> weapons = new ArrayList<>();
        weapons.add(new Weapon(WeaponType.LASER_RAYS, 20));
        weapons.add(new Weapon(WeaponType.PLASMA_CANONS, 40));
        Destroyer destroyer = new DestroyerBuilder()
                .setRegisterNum("A1111AAA")
                .setOwnerId(1)
                .setPropellers(propellers)
                .setCrewNum(8)
                .setWeapons(weapons)
                .setDefenses(defenses)
                .getSpaceship();

        Destroyer receivedDestroyer;
        try {
            spaceshipDao.saveSpaceship(destroyer);
            receivedDestroyer = (Destroyer) spaceshipDao.getSpaceshipByRegisterNum(destroyer.getRegisterNum());
            assertEquals(receivedDestroyer, destroyer);
            spaceshipDao.deleteSpaceshipByRegisterNum(receivedDestroyer.getRegisterNum());
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveFighter() {
        SpaceshipDao spaceshipDao = new SpaceshipDao();
        List<Propeller> propellers = new ArrayList<>();
        propellers.add(new Propeller(PropellerType.CURVATURE_ENGINE, 800));
        propellers.add(new Propeller(PropellerType.FTL_ENGINE, 500));
        DefenseSystem defense = new Armor(30, "Graphite", 10);
        List<Weapon> weapons = new ArrayList<>();
        weapons.add(new Weapon(WeaponType.LASER_RAYS, 20));
        weapons.add(new Weapon(WeaponType.PLASMA_CANONS, 40));
        Fighter fighter = new FighterBuilder()
                .setRegisterNum("A1111AAA")
                .setOwnerId(1)
                .setPropellers(propellers)
                .setCrewNum(8)
                .setWeapons(weapons)
                .setDefense(defense)
                .getSpaceship();

        Fighter receivedFighter;
        try {
            spaceshipDao.saveSpaceship(fighter);
            receivedFighter = (Fighter) spaceshipDao.getSpaceshipByRegisterNum(fighter.getRegisterNum());
            assertEquals(receivedFighter, fighter);
            spaceshipDao.deleteSpaceshipByRegisterNum(receivedFighter.getRegisterNum());
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveSpacialStation() {
        SpaceshipDao spaceshipDao = new SpaceshipDao();
        List<Propeller> propellers = new ArrayList<>();
        propellers.add(new Propeller(PropellerType.CURVATURE_ENGINE, 800));
        propellers.add(new Propeller(PropellerType.FTL_ENGINE, 500));
        List<DefenseSystem> defenses = new ArrayList<>();
        defenses.add(new Armor(30, "Graphite", 10));
        defenses.add(new Shield(50, 70));
        List<Weapon> weapons = new ArrayList<>();
        weapons.add(new Weapon(WeaponType.LASER_RAYS, 20));
        weapons.add(new Weapon(WeaponType.PLASMA_CANONS, 40));
        List<Spaceship> spaceships = new ArrayList<>();
        spaceships.add(new FighterBuilder()
                .setRegisterNum("A1111AAA")
                .setOwnerId(1)
                .setPropellers(propellers)
                .setCrewNum(8)
                .setWeapons(weapons)
                .setDefense(defenses.get(1))
                .getSpaceship());
        spaceships.add(new DestroyerBuilder()
                .setRegisterNum("A1111AAB")
                .setOwnerId(1)
                .setPropellers(propellers)
                .setCrewNum(8)
                .setWeapons(weapons)
                .setDefenses(defenses)
                .getSpaceship());
        SpacialStation spacialStation = new SpacialStationBuilder()
                .setRegisterNum("A1111AAC")
                .setOwnerId(1)
                .setPropellers(propellers)
                .setCrewNum(8)
                .setMaxPassengers(40)
                .setDefenses(defenses)
                .setSpaceships(spaceships)
                .getSpaceship();

        SpacialStation receivedStation;
        try {
            spaceshipDao.saveSpaceship(spacialStation);
            receivedStation = (SpacialStation) spaceshipDao.getSpaceshipByRegisterNum(spacialStation.getRegisterNum());
            assertEquals(receivedStation, spacialStation);
            spaceshipDao.deleteSpaceshipByRegisterNum(receivedStation.getRegisterNum());
            receivedStation.getSpaceships().forEach(spaceship -> {
                spaceshipDao.deleteSpaceshipByRegisterNum(spaceship.getRegisterNum());
            });
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

}