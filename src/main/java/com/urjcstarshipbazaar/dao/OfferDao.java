package com.urjcstarshipbazaar.dao;

import com.urjcstarshipbazaar.models.Offer;
import com.urjcstarshipbazaar.models.User;
import com.urjcstarshipbazaar.models.builders.DestroyerBuilder;
import com.urjcstarshipbazaar.models.spaceships.Spaceship;
import com.urjcstarshipbazaar.models.spaceships.SpaceshipType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfferDao implements OfferDaoInterface {

    private final String CONNECTION_URL = "jdbc:sqlite:database.db";
    private final char SEPARATOR = ',';
    private final char STRINGMARKUP = '\'';

    @Override
    public Offer getBySpaceshipType(Offer offer, SpaceshipType spaceshipType) {
        //devuelve una única oferta, y si busco por tipo, debería devolver una lista de ofertas de ese tipo??
        List<Spaceship> list = new ArrayList<Spaceship>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement statement = connection.createStatement();

            ResultSet results = statement.executeQuery("SELECT * FROM spaceships WHERE spaceship_type = '" +
                    spaceshipType +"' AND id IN (SELECT * FROM offer_spaceship);");
            Offer aux = new Offer();
            while(results.next()) {
                //Debería de haber un factory para que gestionara la creación de cada tipo de nave
                DestroyerBuilder ship = new DestroyerBuilder()
                        .setOwnerId(results.getInt("id"))
                        .setRegisterNum(results.getString("register_num"))
                        .setCrewNum(results.getInt("crew_num"));
                list.add(ship.getSpaceship());
            }

            aux.setSpaceships(list);
            connection.close();
            return aux;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteById(Offer offer) {
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            Statement statement = connection.createStatement();

            statement.executeUpdate("DELETE FROM offers WHERE id = " + offer.getId()
                    + " ); ");

            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void save(Offer offer) {
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            Statement statement = connection.createStatement();
            User aux = offer.getVendor();
            //PENDIENTE DE LA IMPLEMENTACIÓN DE USUARIO
            statement.executeUpdate("INSERT INTO offers VALUES(" + STRINGMARKUP + offer.getId() +
                    STRINGMARKUP + SEPARATOR + STRINGMARKUP + /*aux.getId*/ +
                    STRINGMARKUP + SEPARATOR + STRINGMARKUP + offer.getPrice() +
                    STRINGMARKUP + SEPARATOR + STRINGMARKUP + offer.getDeadline().toString() + STRINGMARKUP +
                    " ); " );
            //Segunda query para la otra tabla
            statement.executeUpdate("INSERT INTO offer_spaceship VALUES(" + STRINGMARKUP + offer.getId() +
                    STRINGMARKUP + SEPARATOR +  /*aux.getId() +*/
                    " ); " );
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
