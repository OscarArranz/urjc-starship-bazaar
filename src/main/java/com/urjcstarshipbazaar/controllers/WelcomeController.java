package com.urjcstarshipbazaar.controllers;

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

public class WelcomeController implements Initializable {

    @FXML
    private TextField emailInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Label loginError;

    private NavigationController mainController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginError.setVisible(false);
        loginError.setManaged(false);
    }

    @FXML
    public void login(ActionEvent event) {
        UserService userService = new UserService();
        if (userService.login(emailInput.getText(), passwordInput.getText())) {
            loginError.setVisible(false);
            loginError.setManaged(false);
            mainController.loadProfile();
        } else {
            loginError.setVisible(true);
            loginError.setManaged(true);
        }
    }

    @FXML
    public void register(ActionEvent event) {
        mainController.loadRegister();
    }

    public void setMainController(NavigationController controller) {
        mainController = controller;
    }

}
