package com.urjcstarshipbazaar.dao;

import com.urjcstarshipbazaar.dao.exceptions.DAOException;
import com.urjcstarshipbazaar.models.Offer;
import com.urjcstarshipbazaar.models.spaceships.Spaceship;
import com.urjcstarshipbazaar.models.spaceships.SpaceshipType;

import java.util.List;

public interface OfferDAOInterface {

    List<Offer> getBySpaceshipType(SpaceshipType spaceshipType, int page) throws DAOException;
    void deleteById(int id) throws DAOException;
    void save(Offer offer) throws DAOException;
    Offer getByOfferId(int id) throws DAOException;
}
