package com.urjcstarshipbazaar.dao;

import com.urjcstarshipbazaar.dao.exceptions.DAOException;
import com.urjcstarshipbazaar.models.Client;
import com.urjcstarshipbazaar.models.Offer;
import com.urjcstarshipbazaar.models.builders.CargoBuilder;
import com.urjcstarshipbazaar.models.spaceships.Cargo;
import com.urjcstarshipbazaar.models.spaceships.Spaceship;
import com.urjcstarshipbazaar.models.spaceships.SpaceshipType;
import com.urjcstarshipbazaar.models.spaceships.components.Armor;
import com.urjcstarshipbazaar.models.spaceships.components.DefenseSystem;
import com.urjcstarshipbazaar.models.spaceships.components.Propeller;
import com.urjcstarshipbazaar.models.spaceships.components.PropellerType;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OfferDaoTest {

    @Test
    public void saveGetByIdAndDelete() {
        OfferDao offerDao = new OfferDao();
        List<Spaceship> spaceships = new ArrayList<>();
        List<Propeller> propellers = new ArrayList<>();
        propellers.add(new Propeller(PropellerType.CURVATURE_ENGINE, 800));
        propellers.add(new Propeller(PropellerType.FTL_ENGINE, 500));
        DefenseSystem defense = new Armor(30, "Graphite", 10);
        Cargo cargo = new CargoBuilder()
                .setRegisterNum("A1111AAA")
                .setOwnerId(1)
                .setPropellers(propellers)
                .setCrewNum(8)
                .setMaxLoadTons(20)
                .setDefense(defense)
                .getSpaceship();
        spaceships.add(cargo);
        Client vendor = new Client("Robin", "robiin", "robiin@email.com", "Mars",
                "Martian");
        Offer offer = null;
        try {
            offer = new Offer(spaceships, vendor, 1800000, new SimpleDateFormat("YYYY-MM-DD")
                    .parse("2021-07-25"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            offerDao.save(offer);

            Offer retrievedOffer = offerDao.getByOfferId(0);

            assertEquals(offer, retrievedOffer);

            // Clean up

            offerDao.deleteById(offer.getId());
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveGetBySpaceshipTypeAndDelete() {
        OfferDao offerDao = new OfferDao();
        SpaceshipDao spaceshipDao = new SpaceshipDao();
        List<Spaceship> spaceships = new ArrayList<>();
        List<Propeller> propellers = new ArrayList<>();
        propellers.add(new Propeller(PropellerType.CURVATURE_ENGINE, 800));
        propellers.add(new Propeller(PropellerType.FTL_ENGINE, 500));
        DefenseSystem defense = new Armor(30, "Graphite", 10);
        Cargo cargo = new CargoBuilder()
                .setRegisterNum("A1111AAA")
                .setOwnerId(1)
                .setPropellers(propellers)
                .setCrewNum(8)
                .setMaxLoadTons(20)
                .setDefense(defense)
                .getSpaceship();
        spaceships.add(cargo);
        Client vendor = new Client("Robin", "robiin", "robiin@email.com", "Mars",
                "Martian");
        Offer offer = null;
        try {
            offer = new Offer(spaceships, vendor, 1800000, new SimpleDateFormat("YYYY-MM-DD")
                    .parse("2021-07-25"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            offerDao.save(offer);
            spaceshipDao.saveSpaceship(cargo);

            Offer retrievedOffer = offerDao.getBySpaceshipType(SpaceshipType.CARGO, 1).get(0);

            assertEquals(offer, retrievedOffer);

            // Clean up

            spaceshipDao.deleteSpaceshipByRegisterNum(cargo.getRegisterNum());
            offerDao.deleteById(offer.getId());
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

}