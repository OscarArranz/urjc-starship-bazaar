package com.urjcstarshipbazaar.dao;

import com.urjcstarshipbazaar.dao.exceptions.UserDAOException;
import com.urjcstarshipbazaar.models.Client;
import com.urjcstarshipbazaar.models.User;

import java.sql.*;

public class UserDAO implements UserDAOInterface {

    private final String CONNECTION_URL = "jdbc:sqlite:database.db";
    private final char SEPARATOR = ',';
    private final char STRINGMARKUP = '\'';

    @Override
    public void save(User user, String password) throws UserDAOException {
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            Statement statement = connection.createStatement();

            statement.executeUpdate("INSERT INTO users VALUES(null" + SEPARATOR + STRINGMARKUP + user.getName() +
                    STRINGMARKUP + SEPARATOR + STRINGMARKUP + user.getNickname() + STRINGMARKUP + SEPARATOR +
                    STRINGMARKUP + user.getEmail() + STRINGMARKUP + SEPARATOR + STRINGMARKUP + password + STRINGMARKUP
                    + SEPARATOR + STRINGMARKUP + ((Client) user).getOriginPlanet() + STRINGMARKUP + SEPARATOR +
                    STRINGMARKUP + ((Client) user).getSpecies() + STRINGMARKUP + SEPARATOR + "null)");

            connection.close();
        } catch (SQLException e) {
            checkExisting(e, "User already exists!");
        }
    }

    @Override
    public User getByNickname(String nickname) throws UserDAOException {
        return getByStringValueWithField(nickname, "nickname");
    }

    @Override
    public User getByEmail(String email) throws UserDAOException {
        return getByStringValueWithField(email, "email");
    }

    public User getByStringValueWithField(String value, String field) throws UserDAOException {
        Client client = new Client();

        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            Statement statement = connection.createStatement();

            ResultSet results = statement
                    .executeQuery("SELECT * FROM users WHERE " + field + " = '" + value + "'");

            while(results.next()) {
                client.setId(results.getInt("id"));
                client.setName(results.getString("name"));
                client.setNickname(results.getString("nickname"));
                client.setEmail(results.getString("email"));
                client.setOriginPlanet(results.getString("origin_planet"));
                client.setSpecies(results.getString("species"));
            }

            connection.close();
        } catch (SQLException e) {
            throw new UserDAOException("User not found!", e);
        }
        return client;
    }

    public String getLicenseById(int id) throws UserDAOException {
        String license = "";

        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            Statement statement = connection.createStatement();

            ResultSet results = statement
                    .executeQuery("SELECT spacial_license FROM users WHERE id = '" + id + "'");

            while(results.next()) {
                license = results.getString("spacial_license");
            }
        } catch (SQLException e) {
            throw new UserDAOException("License not found", e);
        }

        if(license.equals("")) throw new UserDAOException("License not found!");

        return license;
    }

    public void deleteById(int id) throws UserDAOException {
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            Statement statement = connection.createStatement();

            statement
                    .executeUpdate("DELETE FROM Users WHERE id = '" + id + "'");
        } catch (SQLException e) {
            throw new UserDAOException("Couldn't delete user!", e);
        }
    }

    public void saveLicense(String license, int userId) throws UserDAOException {
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            Statement statement = connection.createStatement();

            statement
                    .executeUpdate("UPDATE users SET spacial_license = '" + license + "' WHERE id = " + userId);
        } catch (SQLException e) {
            checkExisting(e, "License already exists!");
        }
    }

    public void checkExisting(SQLException e, String errorMessage) throws UserDAOException {
        if(e.getErrorCode() == 19) throw new UserDAOException(errorMessage, e);
        e.printStackTrace();
    }

}
