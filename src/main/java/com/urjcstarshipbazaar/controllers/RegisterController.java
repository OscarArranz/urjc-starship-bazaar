package com.urjcstarshipbazaar.controllers;

import com.urjcstarshipbazaar.dao.exceptions.DAOException;
import com.urjcstarshipbazaar.models.Client;
import com.urjcstarshipbazaar.models.User;
import com.urjcstarshipbazaar.services.ServiceMessage;
import com.urjcstarshipbazaar.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    TextField name;

    @FXML
    TextField nickname;

    @FXML
    TextField email;

    @FXML
    TextField password;

    @FXML
    TextField repeatedPassword;

    @FXML
    TextField originPlanet;

    @FXML
    TextField species;

    @FXML
    Label errorPassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorPassword.setManaged(false);
    }

    @FXML
    public void register(ActionEvent event) {
        UserService userService = new UserService();
        User user = new Client(name.getText(), nickname.getText(), email.getText(), originPlanet.getText(),
                species.getText());

        errorPassword.setVisible(false);
        errorPassword.setManaged(false);

        ServiceMessage serviceMessage = userService.register(user, password.getText(), repeatedPassword.getText());
        int code = serviceMessage.getCode();

        if(code != 0) {
            if(code == 3) {
                //
            } else {
                errorPassword.setVisible(true);
                errorPassword.setManaged(true);
                errorPassword.setText(serviceMessage.getMessage());
            }
        }
    }

    @FXML
    public void cancel(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/welcome.fxml"));
            Parent welcome = loader.load();
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            stage.setScene(new Scene(welcome));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
