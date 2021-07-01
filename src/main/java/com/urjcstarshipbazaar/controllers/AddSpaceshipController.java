package com.urjcstarshipbazaar.controllers;

import com.urjcstarshipbazaar.LoggedUser;
import com.urjcstarshipbazaar.models.builders.*;
import com.urjcstarshipbazaar.models.spaceships.SpaceshipType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AddSpaceshipController extends Controller {

    @FXML
    private Label crewNumText;

    @FXML
    private TextField registerNum;

    @FXML
    private TextField crewNum;

    @FXML
    private ComboBox<SpaceshipType> spaceshipType;

    @FXML
    private CheckBox isDefense;

    private SpaceshipBuilder spaceshipBuilder;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<SpaceshipType> spaceshipTypes = Arrays.asList(SpaceshipType.values());
        ObservableList<SpaceshipType> types = FXCollections.observableArrayList(spaceshipTypes);
        spaceshipType.setItems(types);
    }

    @FXML
    private void nextStep(ActionEvent event) {
        SpaceshipType type = spaceshipType.getValue();

        switch (type) {
            case CARGO: spaceshipBuilder = new CargoBuilder();
                break;
            case FIGHTER: spaceshipBuilder = new FighterBuilder();
                break;
            case DESTROYER: spaceshipBuilder = new DestroyerBuilder();
                break;
            case SPACIALSTATION: spaceshipBuilder = new SpacialStationBuilder();
        }

        if (!registerNum.getText().matches("[A-Z]\\d{4}[A-Z]{3}")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error en el formulario");
            alert.setContentText("El numero de registro debe seguir el siguiente formato: LNNNNLLL.");
            alert.show();

            return;
        }

        spaceshipBuilder.setOwnerId(LoggedUser.getInstance().getUser().getId());

        if(!crewNum.getText().isEmpty()) spaceshipBuilder.setCrewNum(Integer.parseInt(crewNum.getText()));
        else spaceshipBuilder.setCrewNum(1);

        spaceshipBuilder.setRegisterNum(registerNum.getText()).setIsDefense(isDefense.isSelected());

        AddPropellersController addPropellersController =
                (AddPropellersController) getMainController().load("addPropellers");
        addPropellersController.setBuilder(spaceshipBuilder);
        addPropellersController.setSpaceshipType(type);
    }

    public void checkFighter(ActionEvent event) {
        if(spaceshipType.getValue().equals(SpaceshipType.FIGHTER)) {
            crewNumText.setManaged(false);
            crewNumText.setVisible(false);
            crewNum.setManaged(false);
            crewNum.setVisible(false);
            VBox.setMargin(spaceshipType, new Insets(0, 0, 40, 0));
        } else {
            crewNumText.setManaged(true);
            crewNumText.setVisible(true);
            crewNum.setManaged(true);
            crewNum.setVisible(true);
            VBox.setMargin(spaceshipType, new Insets(0, 0, 14, 0));
        }
    }

}
