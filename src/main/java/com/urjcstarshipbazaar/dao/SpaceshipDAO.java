package com.urjcstarshipbazaar.dao;

import com.urjcstarshipbazaar.dao.exceptions.DAOException;
import com.urjcstarshipbazaar.models.builders.*;
import com.urjcstarshipbazaar.models.spaceships.*;
import com.urjcstarshipbazaar.models.spaceships.components.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SpaceshipDAO implements SpaceshipDAOInterface {

    private final String CONNECTION_URL = "jdbc:sqlite:database.db";
    private final char SEPARATOR = ',';
    private final char STRINGMARKUP = '\'';

    @Override
    public void saveSpaceship(Spaceship spaceship) throws DAOException {
       try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            Statement statement = connection.createStatement();

            saveSpaceshipBasicInfo(spaceship, statement);
            savePropellers(spaceship, statement);
            saveSpaceshipExtraInfo(spaceship, statement);

            statement.close();
            connection.close();
        } catch (SQLException throwable) {
            throw new DAOException("Couldn't save spaceship", throwable);
        }
    }

    public void saveSpaceshipBasicInfo(Spaceship spaceship, Statement statement) throws DAOException {
        String maxPassengers = spaceship.getClass().getSimpleName().equalsIgnoreCase("spacialstation") ?
                ((SpacialStation) spaceship).getMaxPassengers() + "" : "null";
        String maxLoadTons = spaceship.getClass().getSimpleName().equalsIgnoreCase("cargo") ?
                ((Cargo) spaceship).getMaxLoadTons() + "" : "null";

        try {
            statement.executeUpdate("INSERT INTO spaceships VALUES(null, " + spaceship.getOwnerId() +
                    SEPARATOR + STRINGMARKUP + spaceship.getClass().getSimpleName().toLowerCase() +
                    STRINGMARKUP + SEPARATOR + STRINGMARKUP + spaceship.getRegisterNum() +
                    STRINGMARKUP + SEPARATOR + spaceship.getCrewNum() + SEPARATOR + maxLoadTons + SEPARATOR +
                    maxPassengers + ")" );
        } catch (SQLException throwable) {
            throw new DAOException("Couldn't save spaceship basic info", throwable);
        }
    }

    public void saveSpaceshipExtraInfo(Spaceship spaceship, Statement statement) throws DAOException {
        String spaceshipType = spaceship.getClass().getSimpleName().toLowerCase();

        switch (spaceshipType) {
            case "cargo":
                saveCargo((Cargo) spaceship, statement);
                break;
            case "destroyer":
                saveDestroyer((Destroyer) spaceship, statement);
                break;
            case "fighter":
                saveFighter((Fighter) spaceship, statement);
                break;
            case "spacialstation":
                saveSpacialStation((SpacialStation) spaceship, statement);
                break;
        }
    }

    @Override
    public List<Spaceship> getSpaceshipsByUserId(int id) throws DAOException {
        List<Spaceship> spaceships = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            Statement statement = connection.createStatement();

            ResultSet results = statement.executeQuery("SELECT * FROM spaceships WHERE owner_id = " + id + ";");

            while(results.next()) {
                spaceships.add(buildSpaceshipFromResults(results));
            }

            statement.close();
            connection.close();
        } catch (Exception e) {
            throw new DAOException("Couldn't get spaceships", e);
        }
        return spaceships;
    }

    public Spaceship buildSpaceshipFromResults(ResultSet results) throws DAOException {
        Spaceship spaceship = new Cargo();

        try {
            String spaceshipType = results.getString("spaceship_type").toLowerCase();
            switch (spaceshipType) {
                case "cargo":
                    spaceship = buildCargoFromResults(results);
                    break;
                case "destroyer":
                    spaceship = buildDestroyerFromResults(results);
                    break;
                case "fighter":
                    spaceship = buildFighterFromResults(results);
                    break;
                case "spacialstation":
                    spaceship = buildSpacialStationFromResults(results);
            }
        } catch (Exception throwable) {
            throw new DAOException("Couldn't get spaceship", throwable);
        }

        return spaceship;
    }

    public Cargo buildCargoFromResults(ResultSet results) throws DAOException {
        Cargo cargo;

        try {
            String registerNum = results.getString("register_num");
            List<Propeller> propellers = getPropellersByRegisterNum(registerNum);
            DefenseSystem defense = getDefensesByRegisterNum(registerNum).get(0);

            cargo = new CargoBuilder()
                    .setId(results.getInt("id"))
                    .setRegisterNum(results.getString("register_num"))
                    .setOwnerId(results.getInt("owner_id"))
                    .setPropellers(propellers)
                    .setCrewNum(results.getInt("crew_num"))
                    .setMaxLoadTons(results.getDouble("max_load_tons"))
                    .setDefense(defense)
                    .getSpaceship();
        } catch (SQLException throwable) {
            throw new DAOException("Couldn't get cargo", throwable);
        }

        return cargo;
    }

    public Destroyer buildDestroyerFromResults(ResultSet results) throws DAOException {
        Destroyer destroyer;

        try {
            String registerNum = results.getString("register_num");
            List<Propeller> propellers = getPropellersByRegisterNum(registerNum);
            List<Weapon> weapons = getWeaponsByRegisterNum(registerNum);
            List<DefenseSystem> defenses = getDefensesByRegisterNum(registerNum);

            destroyer = new DestroyerBuilder()
                    .setId(results.getInt("id"))
                    .setRegisterNum(results.getString("register_num"))
                    .setOwnerId(results.getInt("owner_id"))
                    .setPropellers(propellers)
                    .setCrewNum(results.getInt("crew_num"))
                    .setWeapons(weapons)
                    .setDefenses(defenses)
                    .getSpaceship();
        } catch (SQLException throwable) {
            throw new DAOException("Couldn't get cargo", throwable);
        }

        return destroyer;
    }

    public Fighter buildFighterFromResults(ResultSet results) throws DAOException {
        Fighter fighter;

        try {
            String registerNum = results.getString("register_num");
            List<Propeller> propellers = getPropellersByRegisterNum(registerNum);
            List<Weapon> weapons = getWeaponsByRegisterNum(registerNum);
            DefenseSystem defense = getDefensesByRegisterNum(registerNum).get(0);

            fighter = new FighterBuilder()
                    .setId(results.getInt("id"))
                    .setRegisterNum(results.getString("register_num"))
                    .setOwnerId(results.getInt("owner_id"))
                    .setPropellers(propellers)
                    .setCrewNum(results.getInt("crew_num"))
                    .setWeapons(weapons)
                    .setDefense(defense)
                    .getSpaceship();
        } catch (SQLException throwable) {
            throw new DAOException("Couldn't get cargo", throwable);
        }

        return fighter;
    }

    public SpacialStation buildSpacialStationFromResults(ResultSet results) throws DAOException {
        SpacialStation spacialStation;

        try {
            String registerNum = results.getString("register_num");
            List<Propeller> propellers = getPropellersByRegisterNum(registerNum);
            List<DefenseSystem> defenses = getDefensesByRegisterNum(registerNum);
            List<Spaceship> spaceships = getHangarSpaceshipsByRegisterNum(registerNum);

            spacialStation = new SpacialStationBuilder()
                    .setId(results.getInt("id"))
                    .setRegisterNum(results.getString("register_num"))
                    .setOwnerId(results.getInt("owner_id"))
                    .setPropellers(propellers)
                    .setCrewNum(results.getInt("crew_num"))
                    .setMaxPassengers(results.getInt("max_passengers"))
                    .setDefenses(defenses)
                    .setSpaceships(spaceships)
                    .getSpaceship();
        } catch (SQLException throwable) {
            throw new DAOException("Couldn't get cargo", throwable);
        }

        return spacialStation;
    }

    public List<Propeller> getPropellersByRegisterNum(String registerNum) throws DAOException {
        List<Propeller> propellers = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM propellers WHERE spaceship_register_num = "
            + STRINGMARKUP + registerNum + STRINGMARKUP);

            while(results.next()) {
                propellers.add(new Propeller(
                        PropellerType.valueOf(results.getString("propeller_type").toUpperCase()),
                        results.getDouble("max_speed_kmh")
                ));
            }

            statement.close();
            connection.close();
        } catch (SQLException throwable) {
            throw new DAOException("Couldn't get propellers", throwable);
        }

        return propellers;
    }

    public List<DefenseSystem> getDefensesByRegisterNum(String registerNum) throws DAOException {
        List<DefenseSystem> defenses = new ArrayList<>();

        defenses.addAll(getArmorsByRegisterNum(registerNum));
        defenses.addAll(getShieldsByRegisterNum(registerNum));

        return defenses;
    }

    public List<Weapon> getWeaponsByRegisterNum(String registerNum) throws DAOException {
        List<Weapon> weapons = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM weapons WHERE spaceship_register_num = "
                    + STRINGMARKUP + registerNum + STRINGMARKUP);

            while(results.next()) {
                weapons.add(new Weapon(
                        WeaponType.valueOf(results.getString("weapon_type").toUpperCase()),
                        results.getDouble("potency_gigajoules")
                ));
            }

            statement.close();
            connection.close();
        } catch (SQLException throwable) {
            throw new DAOException("Couldn't get propellers", throwable);
        }

        return weapons;
    }

    public List<Spaceship> getHangarSpaceshipsByRegisterNum(String registerNum) throws DAOException {
        List<Spaceship> spaceships = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM station_spaceship " +
                    "WHERE station_register_num = " + STRINGMARKUP + registerNum + STRINGMARKUP);

            while(results.next()) {
                spaceships.add(getSpaceshipByRegisterNum(results.getString("spaceship_register_num")));
            }

            statement.close();
            connection.close();
        } catch (SQLException throwable) {
            throw new DAOException("Couldn't get propellers", throwable);
        }

        return spaceships;
    }

    public List<Armor> getArmorsByRegisterNum(String registerNum) throws DAOException {
        List<Armor> armors = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM armors WHERE spaceship_register_num = "
                    + STRINGMARKUP + registerNum + STRINGMARKUP);

            while(results.next()) {
                armors.add(new Armor(
                        results.getDouble("max_damage_gigajoules"),
                        results.getString("material"),
                        results.getDouble("weight_tons")
                ));
            }

            statement.close();
            connection.close();
        } catch (SQLException throwable) {
            throw new DAOException("Couldn't get propellers", throwable);
        }

        return armors;
    }

    public List<Shield> getShieldsByRegisterNum(String registerNum) throws DAOException {
        List<Shield> shields = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM shields WHERE spaceship_register_num = "
                    + STRINGMARKUP + registerNum + STRINGMARKUP);

            while(results.next()) {
                shields.add(new Shield(
                        results.getDouble("max_damage_gigajoules"),
                        results.getDouble("required_energy_gigacoulombs")
                ));
            }

            statement.close();
            connection.close();
        } catch (SQLException throwable) {
            throw new DAOException("Couldn't get propellers", throwable);
        }

        return shields;
    }

    @Override
    public void deleteSpaceshipByRegisterNum(String registerNum) {
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            Statement statement = connection.createStatement();

            statement.execute("PRAGMA foreign_keys = ON");
            statement.executeUpdate("DELETE FROM spaceships WHERE register_num = "
            + STRINGMARKUP + registerNum + STRINGMARKUP);

            statement.close();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public Spaceship getSpaceshipByRegisterNum(String registerNum) throws DAOException {
        Spaceship spaceship = new Cargo();
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement statement = connection.createStatement();

            ResultSet results = statement.executeQuery("SELECT * FROM spaceships WHERE register_num = "
            + STRINGMARKUP + registerNum + STRINGMARKUP);

            while(results.next()) {
                spaceship = buildSpaceshipFromResults(results);
            }
            connection.close();
        } catch (Exception ex) {
            throw new DAOException("Couldn't get spaceship", ex);
        }
        return spaceship;
    }

    public void saveCargo(Cargo cargo, Statement statement) throws DAOException {
        DefenseSystem defense = cargo.getDefense();

        saveDefense(defense, cargo.getRegisterNum(), statement);
    }

    public void saveDestroyer(Destroyer destroyer, Statement statement) throws DAOException {
        List<DefenseSystem> defenses = destroyer.getDefenses();
        List<Weapon> weapons = destroyer.getWeapons();

        for (DefenseSystem defense : defenses) {
            saveDefense(defense, destroyer.getRegisterNum(), statement);
        }

        for (Weapon weapon : weapons) {
            saveWeapon(weapon, destroyer.getRegisterNum(), statement);
        }
    }

    public void saveFighter(Fighter fighter, Statement statement) throws DAOException {
        DefenseSystem defense = fighter.getDefense();
        List<Weapon> weapons = fighter.getWeapons();

        saveDefense(defense, fighter.getRegisterNum(), statement);
        for(Weapon weapon : weapons) {
            saveWeapon(weapon, fighter.getRegisterNum(), statement);
        }
    }

    public void saveSpacialStation(SpacialStation spacialStation, Statement statement) throws DAOException {
        List<DefenseSystem> defenses = spacialStation.getDefenses();
        List<Spaceship> spaceships = spacialStation.getSpaceships();

        for(DefenseSystem defense : defenses) {
            saveDefense(defense, spacialStation.getRegisterNum(), statement);
        }

        for(Spaceship spaceship : spaceships) {
            try {
                saveSpaceship(spaceship);
                statement.executeUpdate("INSERT INTO station_spaceship VALUES(" + STRINGMARKUP
                        + spacialStation.getRegisterNum() + STRINGMARKUP + SEPARATOR + STRINGMARKUP
                        + spaceship.getRegisterNum() + STRINGMARKUP + ")");
            } catch (Exception throwable) {
                throw new DAOException("Can't save spaceship on spacialStation hangar", throwable);
            }
        }
    }

    public void savePropellers(Spaceship spaceship, Statement statement) throws DAOException {
        for(Propeller propeller : spaceship.getPropellers()) {
            try {
                statement.executeUpdate("INSERT INTO propellers VALUES(null, " + STRINGMARKUP
                        + spaceship.getRegisterNum() + STRINGMARKUP + SEPARATOR
                        + STRINGMARKUP + propeller.getPropellerType() + STRINGMARKUP + SEPARATOR
                        + propeller.getMaxSpeedKmh() + ")");
            } catch (SQLException throwable) {
                throw new DAOException("Couldn't save propeller", throwable);
            }
        }
    }

    public void saveWeapon(Weapon weapon, String spaceshipRegisterNum, Statement statement) throws DAOException {
        try {
            statement.executeUpdate("INSERT INTO weapons VALUES(null, " + STRINGMARKUP + spaceshipRegisterNum
                    + STRINGMARKUP + SEPARATOR + STRINGMARKUP + weapon.getWeaponType() + STRINGMARKUP
                    + SEPARATOR + weapon.getPotencyGj() + ")");
        } catch (SQLException throwable) {
            throw new DAOException("Couldn't save weapon", throwable);
        }
    }

    public void saveDefense(DefenseSystem defense, String spaceshipRegisterNum, Statement statement) throws DAOException {
        String defenseType = defense.getClass().getSimpleName().toLowerCase();

        switch (defenseType) {
            case "armor":
                saveArmor((Armor) defense, spaceshipRegisterNum, statement);
                break;
            case "shield":
                saveShield((Shield) defense, spaceshipRegisterNum, statement);
                break;
        }
    }

    public void saveArmor(Armor armor, String spaceshipRegisterNum, Statement statement) throws DAOException {
        try {
            statement.executeUpdate("INSERT INTO armors VALUES(null, " + STRINGMARKUP + spaceshipRegisterNum
                    + STRINGMARKUP + SEPARATOR + armor.getMaxDamageGj() + SEPARATOR + STRINGMARKUP
                    + armor.getMaterial() + STRINGMARKUP + SEPARATOR + armor.getWeightTons() + ")");
        } catch (SQLException throwable) {
            throw new DAOException("Couldn't save armor", throwable);
        }
    }

    public void saveShield(Shield shield, String spaceshipRegisterNum, Statement statement) throws DAOException {
        try {
            statement.executeUpdate("INSERT INTO shields VALUES(null, " + STRINGMARKUP + spaceshipRegisterNum
                    + STRINGMARKUP + SEPARATOR + shield.getMaxDamageGj()
                    + SEPARATOR + shield.getRequiredEnergyGj() +")");
        } catch (SQLException throwable) {
            throw new DAOException("Couldn't save shield", throwable);
        }
    }

}
