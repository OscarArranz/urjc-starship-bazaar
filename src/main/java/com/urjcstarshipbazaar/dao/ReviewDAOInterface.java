package com.urjcstarshipbazaar.dao;

import com.urjcstarshipbazaar.dao.exceptions.DAOException;
import com.urjcstarshipbazaar.models.Review;

import java.util.List;

public interface ReviewDAOInterface {

    List<Review> getByVendorId(int id) throws DAOException;
    void save(Review review) throws DAOException;
    void delete(int id) throws DAOException;

}
