package com.urjcstarshipbazaar.models.builders;

import com.urjcstarshipbazaar.models.spaceships.components.Propeller;

import java.util.List;

public interface SpaceshipBuilder {

    SpaceshipBuilder setRegisterNum(String registerNum) throws Exception;

    SpaceshipBuilder setOwnerId(int ownerId);

    SpaceshipBuilder setPropellers(List<Propeller> propellers);

    SpaceshipBuilder setCrewNum(int crewNum);

}