package com.urjcstarshipbazaar.dao;

import com.urjcstarshipbazaar.dao.exceptions.DAOException;
import com.urjcstarshipbazaar.models.spaceships.Spaceship;

import java.util.List;

public interface SpaceshipDaoInterface {

    void saveSpaceship(Spaceship spaceship) throws DAOException;
    List<Spaceship> getSpaceshipsByUserid(int id) throws Exception;
    void deleteSpaceshipByRegisterNum(String registerNum);
    Spaceship getSpaceshipByRegisterNum(String registerNum) throws DAOException;

}
