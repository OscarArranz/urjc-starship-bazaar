package com.urjcstarshipbazaar.dao;

import com.urjcstarshipbazaar.dao.exceptions.DAOException;
import com.urjcstarshipbazaar.models.User;

public interface UserDAOInterface {

    void save(User user, String password) throws DAOException;
    User getById(int id) throws DAOException;
    User getByNickname(String nickname) throws DAOException;
    User getByEmail(String email) throws DAOException;
    void deleteById(int id) throws DAOException;

}
