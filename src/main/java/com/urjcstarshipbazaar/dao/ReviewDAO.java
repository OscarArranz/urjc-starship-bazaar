package com.urjcstarshipbazaar.dao;

import com.urjcstarshipbazaar.dao.exceptions.DAOException;
import com.urjcstarshipbazaar.models.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO implements ReviewDAOInterface {

    private final String CONNECTION_URL = "jdbc:sqlite:database.db";
    private final char SEPARATOR = ',';
    private final char STRINGMARKUP = '\'';

    @Override
    public List<Review> getByVendorId(int id) throws DAOException {
        List<Review> reviews = new ArrayList<>();
        UserDAO userDAO = new UserDAO();

        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            Statement statement = connection.createStatement();

            ResultSet results = statement
                    .executeQuery("SELECT * FROM reviews WHERE vendor_id = '" + id + "'");

            while(results.next()) {
                Review review = new Review();

                review.setId(results.getInt("id"));
                review.setComment((results.getString("comment")));
                review.setScore(results.getDouble("score"));
                review.setVendor(userDAO.getById(results.getInt("vendor_id")));
                review.setBuyer(userDAO.getById(results.getInt("buyer_id")));

                reviews.add(review);
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DAOException("Reviews not found", e);
        }

        return reviews;
    }

    @Override
    public void save(Review review) throws DAOException {
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            Statement statement = connection.createStatement();

            statement.executeUpdate("INSERT INTO reviews VALUES(null, " + STRINGMARKUP + review.getComment()
                    + STRINGMARKUP + SEPARATOR + review.getScore() + SEPARATOR + review.getVendor().getId()
                    + SEPARATOR + review.getBuyer().getId() + ")");

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            Statement statement = connection.createStatement();

            statement.execute("PRAGMA foreign_keys = ON");
            statement.executeUpdate("DELETE FROM reviews WHERE id = " + id);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

}
