package com.urjcstarshipbazaar.dao;

import com.urjcstarshipbazaar.dao.exceptions.DAOException;
import com.urjcstarshipbazaar.models.Client;
import com.urjcstarshipbazaar.models.User;

import java.sql.*;

public class UserDAO implements UserDAOInterface {

    private final String CONNECTION_URL = "jdbc:sqlite:database.db";
    private final char SEPARATOR = ',';
    private final char STRINGMARKUP = '\'';

    @Override
    public void save(User user, String password) throws DAOException {
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            Statement statement = connection.createStatement();

            statement.executeUpdate("INSERT INTO users VALUES(null" + SEPARATOR + STRINGMARKUP + user.getName() +
                    STRINGMARKUP + SEPARATOR + STRINGMARKUP + user.getNickname() + STRINGMARKUP + SEPARATOR +
                    STRINGMARKUP + user.getEmail() + STRINGMARKUP + SEPARATOR + STRINGMARKUP + password + STRINGMARKUP
                    + SEPARATOR + STRINGMARKUP + ((Client) user).getOriginPlanet() + STRINGMARKUP + SEPARATOR +
                    STRINGMARKUP + ((Client) user).getSpecies() + STRINGMARKUP + SEPARATOR + "null)");

            statement.close();
            connection.close();
        } catch (SQLException e) {
            checkExisting(e, "User already exists!");
        }
    }

    @Override
    public User getById(int id) throws DAOException {
        Client client = new Client();

        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            Statement statement = connection.createStatement();

            ResultSet results = statement
                    .executeQuery("SELECT * FROM users WHERE id = " + id);

            while(results.next()) {
                client.setId(results.getInt("id"));
                client.setName(results.getString("name"));
                client.setNickname(results.getString("nickname"));
                client.setEmail(results.getString("email"));
                client.setOriginPlanet(results.getString("origin_planet"));
                client.setSpecies(results.getString("species"));
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DAOException("User not found!", e);
        }
        return client;
    }

    @Override
    public User getByNickname(String nickname) throws DAOException {
        return getByStringValueWithField(nickname, "nickname");
    }

    @Override
    public User getByEmail(String email) throws DAOException {
        return getByStringValueWithField(email, "email");
    }

    private User getByStringValueWithField(String value, String field) throws DAOException {
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

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DAOException("User not found!", e);
        }
        return client;
    }

    public String getLicenseById(int id) throws DAOException {
        String license = "";

        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            Statement statement = connection.createStatement();

            ResultSet results = statement
                    .executeQuery("SELECT spacial_license FROM users WHERE id = '" + id + "'");

            while(results.next()) {
                license = results.getString("spacial_license");
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DAOException("License not found", e);
        }

        if(license.equals("")) throw new DAOException("License not found!");

        return license;
    }

    public void deleteById(int id) throws DAOException {
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            Statement statement = connection.createStatement();

            statement.execute("PRAGMA foreign_keys = ON");
            statement.executeUpdate("DELETE FROM Users WHERE id = '" + id + "'");

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DAOException("Couldn't delete user!", e);
        }
    }

    @Override
    public String getPasswordByEmail(String email) throws DAOException {
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            Statement statement = connection.createStatement();

            ResultSet results = statement
                    .executeQuery("SELECT * FROM users WHERE email = '" + email + "'");

            while(results.next()) {
                password = results.getString("password");
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DAOException("User not found!", e);
        }
        return password;
    }

    public void saveLicense(String license, int userId) throws DAOException {
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            Statement statement = connection.createStatement();

            statement
                    .executeUpdate("UPDATE users SET spacial_license = '" + license + "' WHERE id = " + userId);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            checkExisting(e, "License already exists!");
        }
    }

    public void checkExisting(SQLException e, String errorMessage) throws DAOException {
        if(e.getErrorCode() == 19) throw new DAOException(errorMessage, e);
        e.printStackTrace();
    }

}
