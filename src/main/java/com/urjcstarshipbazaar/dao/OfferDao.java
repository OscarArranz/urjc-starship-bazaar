package com.urjcstarshipbazaar.dao;

import com.urjcstarshipbazaar.dao.exceptions.DAOException;
import com.urjcstarshipbazaar.models.Offer;
import com.urjcstarshipbazaar.models.User;
import com.urjcstarshipbazaar.models.spaceships.Spaceship;
import com.urjcstarshipbazaar.models.spaceships.SpaceshipType;
import sun.jvm.hotspot.gc.shared.Space;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfferDao implements OfferDaoInterface {

    private final String CONNECTION_URL = "jdbc:sqlite:database.db";
    private final char SEPARATOR = ',';
    private final char STRINGMARKUP = '\'';

    @Override
    public List<Offer> getBySpaceshipType(Offer offer, SpaceshipType spaceshipType) throws DAOException {
        List<Offer> offers = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(
                    "SELECT offer_id FROM offer_spaceship WHERE spaceship_register_num = IN " +
                    "(SELECT register_num FROM spaceships WHERE spaceship_type = '"
                            + spaceshipType.toString().toLowerCase() + "')"
            );

            while(results.next()) {
                offers.add(getByOfferId(results.getInt("offer_id")));
            }

            statement.close();
            connection.close();
        } catch (SQLException ex) {
            throw new DAOException("Couldn't get offers by spaceship type", ex);
        }
        return null;
    }

    @Override
    public void deleteById(Offer offer) {
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            Statement statement = connection.createStatement();

            statement.execute("PRAGMA foreign_keys = ON");
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

    @Override
    public Offer getByOfferId(int id) throws DAOException {
        Offer offer = new Offer();

        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement statement = connection.createStatement();

            ResultSet results = statement.executeQuery("SELECT * FROM offers WHERE id = " + id + ";");

            while (results.next()) {
                UserDAO userDAO = new UserDAO();
                User vendor = userDAO.getById(results.getInt("vendor_id"));

                offer.setId(results.getInt("id"));
                offer.setVendor(vendor);
                offer.setPrice(results.getInt("price_cents"));
                offer.setDeadline(results.getDate("deadline"));
                offer.setSpaceships(getSpaceshipsByOfferId(offer.getId()));
            }

            statement.close();
            connection.close();
        } catch (Exception ex) {
            throw new DAOException("Couldn't get offer", ex);
        }

        return offer;
    }

    public List<Spaceship> getSpaceshipsByOfferId(int id) throws DAOException {
        List<Spaceship> spaceships = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement statement = connection.createStatement();

            ResultSet results = statement.executeQuery(
                    "SELECT spaceship_register_num FROM offer_spaceship WHERE offer_id = " + id
            );

            while(results.next()) {
                spaceships.add(new SpaceshipDao()
                        .getSpaceshipByRegisterNum(results.getString("spaceship_register_num")));
            }

            statement.close();
            connection.close();
        } catch (Exception throwable) {
            throw new DAOException("Couldn't get spaceships by offer id", throwable);
        }

        return spaceships;
    }

}
