package com.urjcstarshipbazaar.models.builders;

import com.urjcstarshipbazaar.models.spaceships.components.Propeller;

import java.util.List;

public interface SpaceshipBuilder {

    SpaceshipBuilder setRegisterNum(String registerNum);

    SpaceshipBuilder setOwnerId(int ownerId);

    SpaceshipBuilder setPropellers(List<Propeller> propellers);

    SpaceshipBuilder setCrewNum(int crewNum);

    SpaceshipBuilder setId(int id);

    SpaceshipBuilder setIsDefense(boolean isDefense);

}