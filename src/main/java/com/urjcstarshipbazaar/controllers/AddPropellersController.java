package com.urjcstarshipbazaar.controllers;

import com.urjcstarshipbazaar.models.builders.*;
import com.urjcstarshipbazaar.models.spaceships.SpaceshipType;
import com.urjcstarshipbazaar.models.spaceships.components.Propeller;
import com.urjcstarshipbazaar.models.spaceships.components.PropellerType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AddPropellersController extends Controller {

    @FXML
    private TextField maxSpeed;

    @FXML
    private ComboBox propellerType;

    @FXML
    private Button addMore;

    private SpaceshipBuilder builder;

    private List<Propeller> propellers;

    private SpaceshipType spaceshipType;

    public AddPropellersController() {
        propellers = new ArrayList<>();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<PropellerType> propellerTypes = Arrays.asList(PropellerType.values());
        ObservableList<PropellerType> types = FXCollections.observableArrayList(propellerTypes);
        propellerType.setItems(types);
    }

    public void setBuilder(SpaceshipBuilder builder) {
        this.builder = builder;
    }

    public void setPropellers(List<Propeller> propellers) {
        this.propellers = propellers;

        if(propellers.size() > 0) {
            addMore.setManaged(false);
            addMore.setVisible(false);
        }
    }

    public void setSpaceshipType(SpaceshipType spaceshipType) {
        this.spaceshipType = spaceshipType;
    }

    private void addPropeller() {
        Propeller propeller = new Propeller();
        propeller.setPropellerType((PropellerType) propellerType.getValue());
        propeller.setMaxSpeedKmh(Double.parseDouble(maxSpeed.getText()));

        propellers.add(propeller);

        builder.setPropellers(propellers);
    }

    @FXML
    public void nextStep(ActionEvent event) {
        addPropeller();
        AddDefensesController addDefensesController = (AddDefensesController) getMainController().load("addDefenses");
        addDefensesController.setBuilder(builder);
        addDefensesController.setSpaceshipType(spaceshipType);
    }

    @FXML
    public void addMore(ActionEvent event) {
        addPropeller();
        AddPropellersController addPropellersController = (AddPropellersController) getMainController().load("addPropellers");
        addPropellersController.setBuilder(builder);
        addPropellersController.setSpaceshipType(spaceshipType);
        addPropellersController.setPropellers(propellers);
    }
}
