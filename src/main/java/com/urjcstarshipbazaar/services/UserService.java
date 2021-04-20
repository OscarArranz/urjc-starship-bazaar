package com.urjcstarshipbazaar.services;

import com.urjcstarshipbazaar.LoggedUser;
import com.urjcstarshipbazaar.dao.UserDAO;
import com.urjcstarshipbazaar.dao.exceptions.DAOException;
import com.urjcstarshipbazaar.models.User;

public class UserService {

    UserDAO userDAO;

    public UserService() {
        userDAO= new UserDAO();
    }

    public boolean register(User user, String password, String repeatedPassword) {
        try {
            userDAO.save(user, password);
            return true;
        } catch (DAOException e) {
            return false;
        }
    }

    public boolean login(String email, String password) {
        try {
            if (password.equals(userDAO.getPasswordByEmail(email))) {
                User user = userDAO.getByEmail(email);
                LoggedUser.getInstance().loadUser(user);
                return true;
            } else {
                return false;
            }
        } catch (DAOException e) {
            return false;
        }
    }

}
