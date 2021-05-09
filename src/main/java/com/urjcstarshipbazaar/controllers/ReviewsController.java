package com.urjcstarshipbazaar.controllers;

import com.urjcstarshipbazaar.LoggedUser;
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
    private Button button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void addReview(ActionEvent event) {
        Review review = new Review();
        review.setBuyer(LoggedUser.getInstance().getUser());
        //A quién está haciendo la review?? Cómo lo obtengo??
        //review.setVendor(user);
        review.setScore(Double.parseDouble(score.getText()));
        review.setComment(textArea.getText());

        ReviewService sevice = new ReviewService();
        sevice.addReview(review);
    }
}
