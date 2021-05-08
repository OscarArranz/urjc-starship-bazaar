package com.urjcstarshipbazaar.services;

import com.urjcstarshipbazaar.LoggedUser;
import com.urjcstarshipbazaar.dao.OfferDAO;
import com.urjcstarshipbazaar.dao.exceptions.DAOException;
import com.urjcstarshipbazaar.models.Offer;
import com.urjcstarshipbazaar.models.spaceships.Spaceship;

import java.util.Date;
import java.util.List;

public class OfferService {

    private OfferDAO offerDAO;

    public OfferService() {
        offerDAO = new OfferDAO();
    }

    public boolean publish(List<Spaceship> spaceships, int price, Date expirationDate) {
        Offer offer = new Offer(spaceships, LoggedUser.getInstance().getUser(), price * 100, expirationDate);

        try {
            offerDAO.save(offer);
            return true;
        } catch (DAOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
