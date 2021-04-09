package com.urjcstarshipbazaar.dao;

import com.urjcstarshipbazaar.dao.exceptions.UserDAOException;
import com.urjcstarshipbazaar.models.User;

public interface UserDAOInterface {

    void save(User user, String password) throws UserDAOException;
    User getByNickname(String nickname) throws UserDAOException;
    User getByEmail(String email) throws UserDAOException;
    void deleteById(int id) throws UserDAOException;

}
