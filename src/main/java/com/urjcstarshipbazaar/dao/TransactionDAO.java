package com.urjcstarshipbazaar.dao;

import com.urjcstarshipbazaar.dao.exceptions.DAOException;
import com.urjcstarshipbazaar.models.Transaction;
import com.urjcstarshipbazaar.models.spaceships.Spaceship;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO implements TransactionDAOInterface {

    private final String CONNECTION_URL = "jdbc:sqlite:database.db";
    private final char SEPARATOR = ',';

    @Override
    public List<Transaction> getByVendorId(int id) throws DAOException {
        return getByUserIdWithRole(id, "vendor");
    }

    @Override
    public List<Transaction> getByBuyerId(int id) throws DAOException {
        return getByUserIdWithRole(id, "buyer");
    }

    public List<Transaction> getByUserIdWithRole(int id, String userRole) throws DAOException {
        List<Transaction> transactions = new ArrayList<>();
        UserDAO userDAO = new UserDAO();

        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            Statement statement = connection.createStatement();

            ResultSet results = statement
                    .executeQuery("SELECT * FROM reviews WHERE " + userRole.concat("_id") + " = '" + id + "'");

            while(results.next()) {
                Transaction transaction = new Transaction();

                transaction.setId(results.getInt("id"));
                transaction.setVendor(userDAO.getById(results.getInt("vendor_id")));
                transaction.setBuyer(userDAO.getById(results.getInt("buyer_id")));
                transaction.setPriceCents(results.getInt("price_cents"));
                // SpaceshipDAO needed to set transaction spaceships

                transactions.add(transaction);
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DAOException("Transactions not found", e);
        }

        return transactions;
    }

    @Override
    public void save(Transaction transaction) throws DAOException {
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            String sql = "INSERT INTO transactions VALUES(null, " + transaction.getVendor().getId()
                    + SEPARATOR + transaction.getBuyer().getId() + SEPARATOR + transaction.getPriceCents() + ")";
            PreparedStatement transactionStatement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            Statement spaceshipStatement = connection.createStatement();

            transactionStatement.executeUpdate();

            for (Spaceship spaceship : transaction.getSpaceships()) {
                try {
                    spaceshipStatement.executeUpdate("INSERT INTO transaction_spaceship VALUES("
                            + transactionStatement.getGeneratedKeys() + SEPARATOR + spaceship.getId() + ")");
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage(), e);
                }
            }

            transactionStatement.close();
            spaceshipStatement.close();
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

            statement.executeUpdate("DELETE FROM transactions WHERE id = " + id);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

}
