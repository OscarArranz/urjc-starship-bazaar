package com.urjcstarshipbazaar.dao;

import com.urjcstarshipbazaar.dao.exceptions.DAOException;
import com.urjcstarshipbazaar.models.spaceships.Spaceship;

import java.util.List;

public interface SpaceshipDAOInterface {

    void saveSpaceship(Spaceship spaceship) throws DAOException;
    List<Spaceship> getSpaceshipsByUserId(int id) throws Exception;
    void deleteSpaceshipByRegisterNum(String registerNum);
    Spaceship getSpaceshipByRegisterNum(String registerNum) throws DAOException;

}
