package com.urjcstarshipbazaar.dao;

import com.urjcstarshipbazaar.models.Offer;
import com.urjcstarshipbazaar.models.spaceships.Spaceship;
import com.urjcstarshipbazaar.models.spaceships.SpaceshipType;

public interface OfferDaoInterface {

    public Offer getBySpaceshipType(Offer offer, SpaceshipType spaceshipType);
    public void deleteById(Offer offer);
    public void save(Offer offer);
}
