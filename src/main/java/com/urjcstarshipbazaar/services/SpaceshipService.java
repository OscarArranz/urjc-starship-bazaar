package com.urjcstarshipbazaar.services;

import com.urjcstarshipbazaar.LoggedUser;
import com.urjcstarshipbazaar.dao.SpaceshipDAO;
import com.urjcstarshipbazaar.dao.exceptions.DAOException;
import com.urjcstarshipbazaar.models.spaceships.Spaceship;

import java.util.ArrayList;
import java.util.List;

public class SpaceshipService {

    private final SpaceshipDAO spaceshipDAO;

    public SpaceshipService() {
        spaceshipDAO = new SpaceshipDAO();
    }

    public List<Spaceship> getSpaceshipsByUserId(int id) {
        List<Spaceship> spaceships;
        try {
            spaceships = spaceshipDAO.getSpaceshipsByUserId(id);
        } catch (DAOException e) {
            e.printStackTrace();
            return null;
        }
        return spaceships;
    }

    public List<Spaceship> getLoggedUserSpaceships() {
        return getSpaceshipsByUserId(LoggedUser.getInstance().getUser().getId());
    }

    public boolean save(Spaceship spaceship) {
        try {
            spaceshipDAO.saveSpaceship(spaceship);
            return true;
        } catch (DAOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setIsDefense(Spaceship spaceship, boolean isDefense) {
        try {
            spaceshipDAO.setIsDefense(spaceship.getRegisterNum(), isDefense);
            return true;
        } catch (DAOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
