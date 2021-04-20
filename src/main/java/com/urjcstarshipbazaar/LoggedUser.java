package com.urjcstarshipbazaar;

import com.urjcstarshipbazaar.dao.UserDAO;
import com.urjcstarshipbazaar.models.User;

import java.util.Objects;

public class LoggedUser {

    private static LoggedUser instance;
    private User user;

    private LoggedUser() {

    }

    public static LoggedUser getInstance() {
        if (Objects.isNull(instance)) instance = new LoggedUser();
        return instance;
    }

    public void loadUser(User user) {
        this.user = user;
    }

    public void logOut() {
        user = null;
    }

    public User getUser() {
        return user;
    }

    public boolean isLogged() {
        return !Objects.isNull(user);
    }

}
