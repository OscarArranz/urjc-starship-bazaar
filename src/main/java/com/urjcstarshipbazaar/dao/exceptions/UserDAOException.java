package com.urjcstarshipbazaar.dao.exceptions;

public class UserDAOException extends Exception {

    public UserDAOException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public UserDAOException(String message) {
        super(message);
    }

}
