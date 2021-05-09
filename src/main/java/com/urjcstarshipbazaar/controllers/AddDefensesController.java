package com.urjcstarshipbazaar.controllers;

import com.urjcstarshipbazaar.models.builders.SpaceshipBuilder;
import com.urjcstarshipbazaar.models.spaceships.SpaceshipType;
import com.urjcstarshipbazaar.models.spaceships.components.DefenseSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddDefensesController extends Controller {

    private SpaceshipBuilder builder;

    private List<DefenseSystem> defenses;

    private SpaceshipType spaceshipType;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        defenses = new ArrayList<>();
    }

    public void setBuilder(SpaceshipBuilder builder) {
        this.builder = builder;
    }

    public void setSpaceshipType(SpaceshipType spaceshipType) {
        this.spaceshipType = spaceshipType;
    }

    public void setDefenses(List<DefenseSystem> defenses) {
        this.defenses = defenses;
    }

    @FXML
    public void addShield(ActionEvent event) {
        AddShieldController addShieldController =
                (AddShieldController) getMainController().load("addShield");
        addShieldController.setBuilder(builder);
        addShieldController.setDefenses(defenses);
        addShieldController.setSpaceshipType(spaceshipType);
    }

    @FXML
    public void addArmor(ActionEvent event) {
        AddArmorController addArmorController =
                (AddArmorController) getMainController().load("addArmor");
        addArmorController.setBuilder(builder);
        addArmorController.setDefenses(defenses);
        addArmorController.setSpaceshipType(spaceshipType);
    }
}
