package com.urjcstarshipbazaar.controllers;

import com.urjcstarshipbazaar.models.builders.CargoBuilder;
import com.urjcstarshipbazaar.models.builders.SpaceshipBuilder;
import com.urjcstarshipbazaar.models.spaceships.SpaceshipType;
import com.urjcstarshipbazaar.models.spaceships.components.Propeller;
import com.urjcstarshipbazaar.models.spaceships.components.PropellerType;
import com.urjcstarshipbazaar.services.SpaceshipService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AddCargoController extends Controller {

    @FXML
    private TextField maxWeight;

    private SpaceshipBuilder builder;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setBuilder(SpaceshipBuilder builder) {
        this.builder = builder;
    }

    @FXML
    public void nextStep(ActionEvent event) {
        SpaceshipService spaceshipService = new SpaceshipService();

        ((CargoBuilder) builder).setMaxLoadTons(Double.parseDouble(maxWeight.getText()));

        boolean save = spaceshipService.save(((CargoBuilder) builder).getSpaceship());

        if(!save) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al guardar nave");
            alert.setContentText("Ha ocurrido un error al guardar la nave, por favor vuelva a intentarlo.");
            alert.show();

            return;
        }

        getMainController().load("addSpaceship");
    }

}
