package com.urjcstarshipbazaar.controllers;

import com.urjcstarshipbazaar.models.builders.CargoBuilder;
import com.urjcstarshipbazaar.models.builders.SpaceshipBuilder;
import com.urjcstarshipbazaar.models.builders.SpacialStationBuilder;
import com.urjcstarshipbazaar.models.spaceships.Spaceship;
import com.urjcstarshipbazaar.models.spaceships.SpaceshipType;
import com.urjcstarshipbazaar.models.spaceships.components.Propeller;
import com.urjcstarshipbazaar.models.spaceships.components.PropellerType;
import com.urjcstarshipbazaar.services.SpaceshipService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AddSpacialStationController extends Controller {

    @FXML
    private TextField maxPassengers;

    @FXML
    private VBox spaceshipPicker;

    private List<Spaceship> userSpaceships;

    private List<CheckBox> checkBoxes;

    private SpaceshipBuilder builder;

    private List<Spaceship> spaceships;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeSpaceships();
    }

    public void setBuilder(SpaceshipBuilder builder) {
        this.builder = builder;
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
    public void nextStep(ActionEvent event) {
        SpaceshipService spaceshipService = new SpaceshipService();

        List<Spaceship> selectedSpaceships = new ArrayList<>();

        for (int i = 0; i < checkBoxes.size() && checkBoxes.get(i).isSelected(); i++) {
            selectedSpaceships.add(userSpaceships.get(i));
        }

        ((SpacialStationBuilder) builder).setMaxPassengers(Integer.parseInt(maxPassengers.getText()));
        ((SpacialStationBuilder) builder).setSpaceships(selectedSpaceships);

        boolean save = spaceshipService.save(((SpacialStationBuilder) builder).getSpaceship());

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
