package com.urjcstarshipbazaar.services;

import com.urjcstarshipbazaar.LoggedUser;
import com.urjcstarshipbazaar.dao.SpaceshipDAO;
import com.urjcstarshipbazaar.dao.exceptions.DAOException;
import com.urjcstarshipbazaar.models.spaceships.Spaceship;

import java.util.ArrayList;
import java.util.List;

public class SpaceshipService {

    private SpaceshipDAO spaceshipDAO;

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

}
