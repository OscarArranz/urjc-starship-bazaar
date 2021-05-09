package com.urjcstarshipbazaar.services;

import com.urjcstarshipbazaar.dao.UserDAO;
import com.urjcstarshipbazaar.dao.exceptions.DAOException;
import com.urjcstarshipbazaar.dao.ReviewDAO;
import com.urjcstarshipbazaar.models.Review;

public class ReviewService {

    ReviewDAO reviewDAO;

    public ReviewService() {
        reviewDAO= new ReviewDAO();
    }

    public boolean addReview(Review review){
        try {
            reviewDAO.save(review);
            return true;
        }
        catch (DAOException e){
            return false;
        }
    }
}
