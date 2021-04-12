package com.urjcstarshipbazaar.dao;

import com.urjcstarshipbazaar.dao.exceptions.DAOException;
import com.urjcstarshipbazaar.models.Client;
import com.urjcstarshipbazaar.models.Review;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReviewDAOTest {

    @Test
    public void saveAndGet() {

        Client vendor = new Client("Robin", "robiin", "robiin@email.com", "Mars",
                "Martian");

        Client buyer = new Client("Pedro", "pedrito28", "pedrito28@email.com", "Pluto",
                "Plutonian");

        UserDAO userDAO = new UserDAO();

        Client retrievedVendor = new Client();
        Client retrievedBuyer = new Client();

        try {
            userDAO.save(vendor, "123");
            userDAO.save(buyer, "456");

            retrievedVendor = (Client) userDAO.getByNickname("robiin");
            retrievedBuyer = (Client) userDAO.getByNickname("pedrito28");
        } catch (DAOException e) {
            e.printStackTrace();
        }

        Review review = new Review("Very good seller!", 5, retrievedVendor, retrievedBuyer);
        ReviewDAO reviewDAO = new ReviewDAO();

        try {
            reviewDAO.save(review);

            Review retrievedReview = reviewDAO.getByVendorId(retrievedVendor.getId()).get(0);

            System.out.println(review.getId() + " " + retrievedReview.getId());

            assertEquals(review, retrievedReview);

            // Clean up
            reviewDAO.delete(retrievedReview.getId());
            userDAO.deleteById(retrievedVendor.getId());
            userDAO.deleteById(retrievedBuyer.getId());
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}