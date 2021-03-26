package com.urjcstarshipbazaar.dao;

import com.urjcstarshipbazaar.models.spaceships.Spaceship;

import java.util.List;

public interface SpaceshipDaoInterface {

    public void saveSpaceship(Spaceship spaceship);
    public List<Spaceship> getSpaceshipByUserid(int id);
    public void deleteSpaceshipByUserId(Spaceship spaceship);

}
