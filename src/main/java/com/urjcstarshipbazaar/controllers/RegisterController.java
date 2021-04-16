package com.urjcstarshipbazaar.controllers;

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
import javafx.scene.control.Alert;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void register(ActionEvent event) {
        UserService userService = new UserService();
        User user = new Client(name.getText(), nickname.getText(), email.getText(), originPlanet.getText(),
                species.getText());

        if(validateInput())
            if (!userService.register(user, password.getText(), repeatedPassword.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("User creation error");
                alert.setContentText("Error al crear el usuario, por favor inténtelo de nuevo más tarde.");
                alert.show();
            } else {
                // Go to application
            }
    }

    public boolean validateInput() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error en el formulario");

        if(name.getText().isEmpty()) {
            alert.setContentText("Debe insertar un nombre para crear un usuario.");
            alert.show();
            return false;
        }
        if(name.getText().length() < 3) {
            alert.setContentText("El nombre insertado debe tener al menos 3 caracteres de longitud.");
            alert.show();
            return false;
        }
        if(nickname.getText().isEmpty()) {
            alert.setContentText("Debe insertar un nickname para crear un usuario.");
            alert.show();
            return false;
        }
        if(nickname.getText().length() < 5) {
            alert.setContentText("El nickname insertado debe tener al menos 3 caracteres de longitud.");
            alert.show();
            return false;
        }
        if(email.getText().isEmpty()) {
            alert.setContentText("Debe insertar un email para crear un usuario.");
            alert.show();
            return false;
        }
        if(!email.getText().matches("^(.+)@(.+)$")) {
            alert.setContentText("El email insertado no es valido.");
            alert.show();
            return false;
        }
        if(password.getText().isEmpty()) {
            alert.setContentText("Debe insertar una contraseña para crear un usuario.");
            alert.show();
            return false;
        }
        if(password.getText().length() < 6) {
            alert.setContentText("La contraseña debe tener al menos 6 caracteres de longitud.");
            alert.show();
            return false;
        }
        if(!password.getText().equals(repeatedPassword.getText())) {
            alert.setContentText("Las contraseñas no coinciden.");
            alert.show();
            return false;
        }
        if(originPlanet.getText().isEmpty()) {
            alert.setContentText("Debe insertar un planeta de origen para crear un usuario.");
            alert.show();
            return false;
        }
        if(species.getText().isEmpty()) {
            alert.setContentText("Debe insertar una especie para crear un usuario.");
            alert.show();
            return false;
        }
        return true;
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
