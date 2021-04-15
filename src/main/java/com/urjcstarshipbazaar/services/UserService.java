package com.urjcstarshipbazaar.services;

import com.urjcstarshipbazaar.dao.UserDAO;
import com.urjcstarshipbazaar.dao.exceptions.DAOException;
import com.urjcstarshipbazaar.models.User;

public class UserService {

    UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    public ServiceMessage register(User user, String password, String repeatedPassword) {
        if (!password.equals(repeatedPassword)) return new ServiceMessage(1, "Las contraseñas no coinciden");
        if (password.length() < 8) return new ServiceMessage(2,
                "La contraseña debe ser de al menos 8 caracteres de longitud");
        try {
            userDAO.save(user, password);
            return new ServiceMessage(0);
        } catch (DAOException e) {
            return new ServiceMessage(3, "El registro falló");
        }
    }

}
