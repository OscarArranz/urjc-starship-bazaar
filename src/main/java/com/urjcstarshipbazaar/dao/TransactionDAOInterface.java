package com.urjcstarshipbazaar.dao;

import com.urjcstarshipbazaar.dao.exceptions.DAOException;
import com.urjcstarshipbazaar.models.Transaction;

import java.util.List;

public interface TransactionDAOInterface {

    List<Transaction> getByVendorId(int id) throws DAOException;
    List<Transaction> getByBuyerId(int id) throws DAOException;
    void save(Transaction transaction) throws DAOException;
    void delete(int id) throws DAOException;

}
