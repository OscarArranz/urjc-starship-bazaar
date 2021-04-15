package com.urjcstarshipbazaar.dao;

import com.urjcstarshipbazaar.models.Offer;
import com.urjcstarshipbazaar.models.User;
import com.urjcstarshipbazaar.models.spaceships.SpaceshipType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfferDao implements OfferDaoInterface {

    private final String CONNECTION_URL = "jdbc:sqlite:database.db";
    private final char SEPARATOR = ',';
    private final char STRINGMARKUP = '\'';

    @Override
    public List<Integer> getIdBySpaceshipType(Offer offer, SpaceshipType spaceshipType) {
        List<Integer> list = new ArrayList<Integer>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT offer_id FROM offer_spaceship WHERE spaceship_id = IN " +
                    "(SELECT id FROM spaceships WHERE spaceship_type = '" + spaceshipType + "' );" );
            while(results.next()) {
                list.add(results.getInt("offer_id"));
            }
            connection.close();
            return list;
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
            statement.executeUpdate("INSERT INTO offers VALUES(" + STRINGMARKUP + offer.getId() +
                    STRINGMARKUP + SEPARATOR + STRINGMARKUP + aux.getId() +
                    STRINGMARKUP + SEPARATOR + STRINGMARKUP + offer.getPrice() +
                    STRINGMARKUP + SEPARATOR + STRINGMARKUP + offer.getDeadline().toString() + STRINGMARKUP +
                    " ); " );
            //Segunda query para la otra tabla
            statement.executeUpdate("INSERT INTO offer_spaceship VALUES(" + STRINGMARKUP + offer.getId() +
                    STRINGMARKUP + SEPARATOR +  aux.getId() +
                    " ); " );
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    //Método que busca los datos de las ofertas.
    @Override
    public Offer getByOfferId(int id){
        Offer offer = new Offer();
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM offers WHERE id = " + id + ";");
            offer.setId(id);
            User user = new User();
            user.setId(results.getInt("vendor_id"));
            offer.setVendor(user);
            offer.setPrice(results.getInt("price_cents"));
            offer.setDeadline(results.getString("deadline"));
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return offer;
    }
}
