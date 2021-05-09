package com.urjcstarshipbazaar.controllers;

import com.urjcstarshipbazaar.models.spaceships.Spaceship;
import com.urjcstarshipbazaar.services.OfferService;
import com.urjcstarshipbazaar.services.SpaceshipService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PublishOfferController extends Controller {

    @FXML
    private TextField price;

    @FXML
    private TextField expirationDate;

    @FXML
    private VBox spaceshipPicker;
    
    private List<Spaceship> userSpaceships;
    
    private List<CheckBox> checkBoxes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeSpaceships();
    }

    private void initializeSpaceships() {
        checkBoxes = new ArrayList<>();

        SpaceshipService spaceshipService = new SpaceshipService();
        userSpaceships = spaceshipService.getLoggedUserSpaceships();
        List<CheckBox> createdCheckboxes = new ArrayList<>();
        List<Pane> spaceshipViews = new ArrayList<>();

        for (Spaceship currentSpaceship : userSpaceships) {
            VBox spaceshipData = new VBox(10);
            Label spaceshipType = new Label(currentSpaceship.getClass().getSimpleName());
            spaceshipType.getStyleClass().add("text");
            spaceshipType.getStyleClass().add("type");
            Label spaceshipRegisterNum = new Label(currentSpaceship.getRegisterNum());
            spaceshipRegisterNum.getStyleClass().add("text");
            spaceshipRegisterNum.getStyleClass().add("num");
            CheckBox selectSpaceship = new CheckBox();
            Region region = new Region();
            HBox.setHgrow(region, Priority.ALWAYS);
            HBox spaceshipBox = new HBox();
            spaceshipBox.setAlignment(Pos.CENTER_LEFT);
            spaceshipBox.getStyleClass().add("ship-box");

            createdCheckboxes.add(selectSpaceship);
            spaceshipViews.add(spaceshipBox);

            spaceshipData.getChildren().add(spaceshipType);
            spaceshipData.getChildren().add(spaceshipRegisterNum);
            spaceshipBox.getChildren().add(spaceshipData);
            spaceshipBox.getChildren().add(region);
            spaceshipBox.getChildren().add(selectSpaceship);
        }

        checkBoxes.clear();
        checkBoxes.addAll(createdCheckboxes);
        spaceshipPicker.getChildren().setAll(spaceshipViews);
    }

    @FXML
    private void publishOffer(ActionEvent event) {
        OfferService offerService = new OfferService();

        Date expirationDate = new Date();

        try {
            expirationDate = new SimpleDateFormat("dd/mm/yyyy").parse(this.expirationDate.getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Spaceship> selectedSpaceships = new ArrayList<>();

        for (int i = 0; i < checkBoxes.size() && checkBoxes.get(i).isSelected(); i++) {
            selectedSpaceships.add(userSpaceships.get(i));
        }

        offerService.publish(selectedSpaceships, Integer.parseInt(price.getText()), expirationDate);
    }

}
