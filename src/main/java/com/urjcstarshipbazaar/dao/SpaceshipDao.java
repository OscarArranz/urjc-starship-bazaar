package com.urjcstarshipbazaar.dao;

import com.urjcstarshipbazaar.models.builders.CargoBuilder;
import com.urjcstarshipbazaar.models.builders.DestroyerBuilder;
import com.urjcstarshipbazaar.models.builders.SpaceshipBuilder;
import com.urjcstarshipbazaar.models.spaceships.Spaceship;

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

    public List<Spaceship> getSpaceshipByUserid(int id) {
        List<Spaceship> list = new ArrayList<Spaceship>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement statement = connection.createStatement();

            ResultSet results = statement.executeQuery("SELECT * FROM spaceships WHERE owner_id = " + id + ";");

            while(results.next()) {
                String shipType = results.getString("spaceship_type");
                //¿Factory Builder??
                //si es destroyer, o si es fighter...
                DestroyerBuilder ship = new DestroyerBuilder()
                        .setOwnerId(results.getInt("id"))
                        .setRegisterNum(results.getString("register_num"))
                        .setCrewNum(results.getInt("crew_num"));
                list.add(ship.getSpaceship());
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

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
}
