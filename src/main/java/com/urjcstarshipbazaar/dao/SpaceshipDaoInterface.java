package com.urjcstarshipbazaar.dao;

import com.urjcstarshipbazaar.models.spaceships.Spaceship;

import java.util.List;

public interface SpaceshipDaoInterface {

    public void saveSpaceship(Spaceship spaceship);
    public List<Spaceship> getSpaceshipByUserid(int id) throws Exception;
    public void deleteSpaceshipByUserId(Spaceship spaceship);
    public Spaceship getSpaceshipById(int id);

}
