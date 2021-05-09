package com.urjcstarshipbazaar.controllers;

import com.urjcstarshipbazaar.LoggedUser;
import com.urjcstarshipbazaar.models.Client;
import com.urjcstarshipbazaar.models.User;
import com.urjcstarshipbazaar.models.spaceships.Spaceship;
import com.urjcstarshipbazaar.services.SpaceshipService;
import com.urjcstarshipbazaar.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProfileController extends Controller {

    @FXML
    private Label nickname;

    @FXML
    private Label name;

    @FXML
    private Label specie;

    @FXML
    private Label originPlanet;

    @FXML
    private VBox spaceshipPicker;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User user=LoggedUser.getInstance().getUser();
        nickname.setText(user.getNickname());
        name.setText(user.getName());
        specie.setText(((Client) user).getSpecies());
        originPlanet.setText(((Client) user).getOriginPlanet());

        initializeSpaceships();
    }

    private void initializeSpaceships() {
        SpaceshipService spaceshipService = new SpaceshipService();
        List <Spaceship> userSpaceships = spaceshipService.getLoggedUserSpaceships();
        List<Pane> spaceshipViews = new ArrayList<>();

        for (Spaceship currentSpaceship : userSpaceships) {
            VBox spaceshipData = new VBox(10);

            Label spaceshipType = new Label(currentSpaceship.getClass().getSimpleName());
            spaceshipType.getStyleClass().add("text");
            spaceshipType.getStyleClass().add("type");

            Label spaceshipRegisterNum = new Label(currentSpaceship.getRegisterNum());
            spaceshipRegisterNum.getStyleClass().add("text");
            spaceshipRegisterNum.getStyleClass().add("num");

            HBox spaceshipBox = new HBox();
            spaceshipBox.setAlignment(Pos.CENTER_LEFT);
            spaceshipBox.getStyleClass().add("ship-box");

            spaceshipData.getChildren().add(spaceshipType);
            spaceshipData.getChildren().add(spaceshipRegisterNum);
            spaceshipBox.getChildren().add(spaceshipData);

            spaceshipViews.add(spaceshipBox);
        }

        spaceshipPicker.getChildren().setAll(spaceshipViews);
    }

}
