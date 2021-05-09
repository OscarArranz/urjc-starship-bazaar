package com.urjcstarshipbazaar.controllers;

import com.urjcstarshipbazaar.LoggedUser;
import com.urjcstarshipbazaar.dao.UserDAO;
import com.urjcstarshipbazaar.dao.exceptions.DAOException;
import com.urjcstarshipbazaar.models.Client;
import com.urjcstarshipbazaar.models.Review;
import com.urjcstarshipbazaar.models.User;
import com.urjcstarshipbazaar.services.ReviewService;
import com.urjcstarshipbazaar.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReviewsController implements Initializable {

    @FXML
    private TextArea textArea;

    @FXML
    private TextField score;

    @FXML
    private TextField username;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void addReview(ActionEvent event) {
        try {
            Review review = new Review();
            review.setBuyer(LoggedUser.getInstance().getUser());
            User user = new UserDAO().getByStringValueWithField(username.getText(),"name");
            review.setComment(username.getText());
            review.setVendor(user);
            review.setScore(Double.parseDouble(score.getText()));
            review.setComment(textArea.getText());

            ReviewService service = new ReviewService();
            service.addReview(review);
        }
        catch (DAOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return;
        }
    }
}
