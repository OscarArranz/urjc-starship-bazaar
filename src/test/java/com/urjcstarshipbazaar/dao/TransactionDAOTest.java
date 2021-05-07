package com.urjcstarshipbazaar.dao;

import com.urjcstarshipbazaar.dao.exceptions.DAOException;
import com.urjcstarshipbazaar.models.Client;
import com.urjcstarshipbazaar.models.Transaction;
import com.urjcstarshipbazaar.models.builders.CargoBuilder;
import com.urjcstarshipbazaar.models.spaceships.Cargo;
import com.urjcstarshipbazaar.models.spaceships.Spaceship;
import com.urjcstarshipbazaar.models.spaceships.components.Armor;
import com.urjcstarshipbazaar.models.spaceships.components.DefenseSystem;
import com.urjcstarshipbazaar.models.spaceships.components.Propeller;
import com.urjcstarshipbazaar.models.spaceships.components.PropellerType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TransactionDAOTest {

    @Test
    public void saveGetAndDelete() {
        TransactionDAO transactionDAO = new TransactionDAO();
        SpaceshipDAO spaceshipDao = new SpaceshipDAO();
        UserDAO userDAO = new UserDAO();

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

        Client vendor = new Client(1, "Robin", "robiin", "robiin@email.com", "Mars",
                "Martian");
        Client buyer = new Client(2, "Jorge", "Bush", "notthepresident@email.com", "Earth",
                "Human");

        Transaction transaction = new Transaction(spaceships, vendor, buyer, 1800000);

        try {
            transactionDAO.save(transaction);
            userDAO.save(vendor, "123");
            userDAO.save(buyer, "123");
            spaceshipDao.saveSpaceship(cargo);

            Transaction retrievedTransactionByVendor = transactionDAO.getByVendorId(vendor.getId()).get(0);
            Transaction retrievedTransactionByBuyer = transactionDAO.getByBuyerId(buyer.getId()).get(0);

            assertEquals(retrievedTransactionByVendor, transaction);
            assertEquals(retrievedTransactionByBuyer, transaction);


            // Clean up
            transactionDAO.delete(retrievedTransactionByVendor.getId());
            userDAO.deleteById(vendor.getId());
            userDAO.deleteById(buyer.getId());
            spaceshipDao.deleteSpaceshipByRegisterNum(cargo.getRegisterNum());
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }

}