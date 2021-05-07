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
    private final char STRINGMARKUP = '\'';

    @Override
    public List<Transaction> getByVendorId(int id) throws DAOException {
        return getByUserIdWithRole(id, "vendor");
    }

    @Override
    public List<Transaction> getByBuyerId(int id) throws DAOException {
        return getByUserIdWithRole(id, "buyer");
    }

    private List<Transaction> getByUserIdWithRole(int id, String userRole) throws DAOException {
        List<Transaction> transactions = new ArrayList<>();
        UserDAO userDAO = new UserDAO();

        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            Statement statement = connection.createStatement();

            ResultSet results = statement
                    .executeQuery("SELECT * FROM transactions WHERE " + userRole.concat("_id") + " = " + id);

            while(results.next()) {
                Transaction transaction = new Transaction();

                transaction.setId(results.getInt("id"));
                transaction.setVendor(userDAO.getById(results.getInt("vendor_id")));
                transaction.setBuyer(userDAO.getById(results.getInt("buyer_id")));
                transaction.setPriceCents(results.getInt("price_cents"));
                transaction.setSpaceships(getSpaceshipsByTransactionId(statement, transaction.getId()));

                transactions.add(transaction);
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DAOException("Transactions not found", e);
        }

        return transactions;
    }

    private List<Spaceship> getSpaceshipsByTransactionId(Statement statement, int transactionId) throws DAOException {
        List<Spaceship> spaceships = new ArrayList<>();
        SpaceshipDAO spaceshipDao = new SpaceshipDAO();

        try {
            ResultSet results = statement.executeQuery(
                    "SELECT spaceship_register_num from transaction_spaceship where transaction_id = "
                    + transactionId
            );

            while (results.next()) {
                spaceships.add(
                        spaceshipDao.getSpaceshipByRegisterNum(results.getString("spaceship_register_num"))
                );
            }
        } catch (SQLException throwable) {
            throw new DAOException("Couldn't get spaceships from transaction", throwable);
        }

        return spaceships;
    }

    @Override
    public void save(Transaction transaction) throws DAOException {
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL);
            String sql = "INSERT INTO transactions VALUES(null, " + transaction.getVendor().getId()
                    + SEPARATOR + transaction.getBuyer().getId() + SEPARATOR + transaction.getPriceCents() + ")";
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);

            ResultSet resultSet = statement.executeQuery("select last_insert_rowid()");

            for (Spaceship spaceship : transaction.getSpaceships()) {
                try {
                    statement.executeUpdate("INSERT INTO transaction_spaceship VALUES("
                            + resultSet.getInt("last_insert_rowid()") + SEPARATOR + STRINGMARKUP +
                            spaceship.getRegisterNum() + STRINGMARKUP + ")");
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage(), e);
                }
            }

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
            statement.executeUpdate("DELETE FROM transactions WHERE id = " + id);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

}
