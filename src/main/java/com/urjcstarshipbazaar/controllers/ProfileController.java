package com.urjcstarshipbazaar.controllers;

import com.urjcstarshipbazaar.LoggedUser;
import com.urjcstarshipbazaar.models.Client;
import com.urjcstarshipbazaar.models.User;
import com.urjcstarshipbazaar.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private Label nickname;

    @FXML
    private Label name;

    @FXML
    private Label specie;

    @FXML
    private Label originPlanet;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User user=LoggedUser.getInstance().getUser();
        nickname.setText(user.getNickname());
        name.setText(user.getName());
        specie.setText(((Client) user).getSpecies());
        originPlanet.setText(((Client) user).getOriginPlanet());
    }
}
