package com.urjcstarshipbazaar.dao;

import com.urjcstarshipbazaar.models.builders.*;
import com.urjcstarshipbazaar.models.spaceships.Spaceship;
import com.urjcstarshipbazaar.models.spaceships.components.DefenseSystem;
import com.urjcstarshipbazaar.models.spaceships.components.Shield;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpaceshipDao implements SpaceshipDaoInterface {

    private final String CONNECTION_URL = "jdbc:sqlite:database.db";
    private final char SEPARATOR = ',';
    private final char STRINGMARKUP = '\'';

    @Override
    public void saveSpaceship(Spaceship spaceship) {
       try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            Statement statement = connection.createStatement();

            statement.executeUpdate("INSERT INTO spaceships VALUES(" + STRINGMARKUP + spaceship.getOwnerId() +
                    STRINGMARKUP + SEPARATOR + STRINGMARKUP + spaceship.getClass().getSimpleName().toLowerCase() +
                    STRINGMARKUP + SEPARATOR + STRINGMARKUP + spaceship.getRegisterNum() +
                    STRINGMARKUP + SEPARATOR + STRINGMARKUP + spaceship.getCrewNum() + STRINGMARKUP +
                    " ); " );

            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public List<Spaceship> getSpaceshipByUserid(int id) {
        List<Spaceship> list = new ArrayList<Spaceship>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement statement = connection.createStatement();

            ResultSet results = statement.executeQuery("SELECT * FROM spaceships WHERE owner_id = " + id + ";");

            while(results.next()) {
                String shipType = results.getString("spaceship_type");
                switch (shipType){ //CARGO, DESTROYER, FIGHTER, SPACIAL_STATION
                    case "CARGO":
                        ResultSet aux = statement.executeQuery("SELECT * FROM shields WHERE spaceship_id = " +
                                results.getInt("id") + ";");
                        //Cómo diferenciar de su sistema de defensa es armor o shield.
                        Shield shield = new Shield(results.getDouble("max_damage_gigajoules"),results.getDouble("required_energy_gigacoulombs"));
                        CargoBuilder cargo = (CargoBuilder) new CargoBuilder()
                                .setId(results.getInt("id"))
                                .setOwnerId(results.getInt("owner_id"))
                                .setRegisterNum(results.getString("register_num"))
                                .setCrewNum(results.getInt("crew_num"));
                        list.add(cargo.getSpaceship());
                        break;
                    case "DESTROYER":
                        DestroyerBuilder destroyer = (DestroyerBuilder) new DestroyerBuilder()
                                .setId(results.getInt("id"))
                                .setOwnerId(results.getInt("owner_id"))
                                .setRegisterNum(results.getString("register_num"))
                                .setCrewNum(results.getInt("crew_num"));
                        list.add(destroyer.getSpaceship());
                        break;
                    case "FIGHTER":
                        FighterBuilder fighter = (FighterBuilder) new FighterBuilder()
                                .setId(results.getInt("id"))
                                .setOwnerId(results.getInt("owner_id"))
                                .setRegisterNum(results.getString("register_num"))
                                .setCrewNum(results.getInt("crew_num"));
                        list.add(fighter.getSpaceship());
                        break;
                    case "SPACIAL_STATION":
                        SpacialStationBuilder spacialStation = (SpacialStationBuilder) new SpacialStationBuilder()
                                .setId(results.getInt("id"))
                                .setOwnerId(results.getInt("owner_id"))
                                .setRegisterNum(results.getString("register_num"))
                                .setCrewNum(results.getInt("crew_num"));
                        list.add(spacialStation.getSpaceship());
                        break;
                }

            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void deleteSpaceshipByUserId(Spaceship spaceship) {
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            Statement statement = connection.createStatement();

            statement.executeUpdate("DELETE FROM spaceships WHERE id = " + spaceship.getId() + STRINGMARKUP
            + " ); ");

            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public Spaceship getSpaceshipById(int id) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement statement = connection.createStatement();

            ResultSet results = statement.executeQuery("SELECT * FROM spaceships WHERE id = " + id + ";");

            while(results.next()) {
                String shipType = results.getString("spaceship_type");
                switch (shipType){ //CARGO, DESTROYER, FIGHTER, SPACIAL_STATION
                    case "CARGO":
                        ResultSet aux = statement.executeQuery("SELECT * FROM spaceships WHERE id = " + id + ";");
                        CargoBuilder cargo = new CargoBuilder()
                                .setOwnerId(results.getInt("id"))
                                .setRegisterNum(results.getString("register_num"))
                                .setCrewNum(results.getInt("crew_num"));
                        return cargo.getSpaceship();
                    case "DESTROYER":

                        DestroyerBuilder destroyer = new DestroyerBuilder()
                                .setOwnerId(results.getInt("id"))
                                .setRegisterNum(results.getString("register_num"))
                                .setCrewNum(results.getInt("crew_num"));
                        return destroyer.getSpaceship();
                    case "FIGHTER":

                        FighterBuilder fighter = new FighterBuilder()
                                .setOwnerId(results.getInt("id"))
                                .setRegisterNum(results.getString("register_num"))
                                .setCrewNum(results.getInt("crew_num"));
                        return fighter.getSpaceship();
                    case "SPACIAL_STATION":

                        SpacialStationBuilder spacialStation = new SpacialStationBuilder()
                                .setOwnerId(results.getInt("id"))
                                .setRegisterNum(results.getString("register_num"))
                                .setCrewNum(results.getInt("crew_num"));
                        return spacialStation.getSpaceship();
                    default:
                        return null;
                }

            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


}
