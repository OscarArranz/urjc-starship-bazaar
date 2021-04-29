package com.urjcstarshipbazaar.dao;

import com.urjcstarshipbazaar.dao.exceptions.DAOException;
import com.urjcstarshipbazaar.models.Offer;
import com.urjcstarshipbazaar.models.spaceships.Spaceship;
import com.urjcstarshipbazaar.models.spaceships.SpaceshipType;

import java.util.List;

public interface OfferDaoInterface {

    public List<Offer> getBySpaceshipType(Offer offer, SpaceshipType spaceshipType) throws DAOException;
    public void deleteById(Offer offer);
    public void save(Offer offer);
    public Offer getByOfferId(int id) throws DAOException;
}
