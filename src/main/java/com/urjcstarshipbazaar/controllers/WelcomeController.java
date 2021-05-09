package com.urjcstarshipbazaar.controllers;

import com.urjcstarshipbazaar.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController extends Controller {

    @FXML
    private TextField emailInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Label loginError;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginError.setVisible(false);
        loginError.setManaged(false);
    }

    @FXML
    private void login(ActionEvent event) {
        UserService userService = new UserService();
        if (
                !emailInput.getText().isEmpty() && !passwordInput.getText().isEmpty()
                && userService.login(emailInput.getText(), passwordInput.getText())
        ) {
            loginError.setVisible(false);
            loginError.setManaged(false);
            getMainController().loadProfile();
        } else {
            loginError.setVisible(true);
            loginError.setManaged(true);
        }
    }

    @FXML
    private void register(ActionEvent event) {
        getMainController().loadRegister();
    }

}
