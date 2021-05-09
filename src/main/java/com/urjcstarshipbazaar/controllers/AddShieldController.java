package com.urjcstarshipbazaar.controllers;

import com.urjcstarshipbazaar.models.builders.*;
import com.urjcstarshipbazaar.models.spaceships.SpaceshipType;
import com.urjcstarshipbazaar.models.spaceships.components.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddShieldController extends Controller {

    @FXML
    private TextField damage;

    @FXML
    private TextField energy;

    @FXML
    private Button addMore;

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

        switch (spaceshipType) {
            case CARGO:
            case FIGHTER:
                addMore.setVisible(false);
                addMore.setManaged(false);
                break;
            case DESTROYER:
                if(defenses.size() > 0) {
                    addMore.setVisible(false);
                    addMore.setManaged(false);
                }
                break;
            case SPACIALSTATION:
                if(defenses.size() > 1) {
                    addMore.setVisible(false);
                    addMore.setManaged(false);
                }
        }
    }

    public void setDefenses(List<DefenseSystem> defenses) {
        this.defenses = defenses;
    }

    private void addDefense() {
        Shield shield = new Shield();
        shield.setMaxDamageGj(Double.parseDouble(damage.getText()));
        shield.setRequiredEnergyGj(Double.parseDouble(energy.getText()));

        if(Objects.isNull(defenses)) {
            defenses = new ArrayList<>();
        }

        switch (spaceshipType) {
            case CARGO: ((CargoBuilder) builder).setDefense(shield);
                break;
            case FIGHTER: ((FighterBuilder) builder).setDefense(shield);
                break;
            case DESTROYER:
                defenses.add(shield);
                ((DestroyerBuilder) builder).setDefenses(defenses);
                break;
            case SPACIALSTATION:
                defenses.add(shield);
                ((SpacialStationBuilder) builder).setDefenses(defenses);
        }
    }

    @FXML
    public void nextStep(ActionEvent event) {
        addDefense();

        switch (spaceshipType) {
            case CARGO:
                AddCargoController addCargoController =
                        (AddCargoController) getMainController().load("addCargo");
                addCargoController.setBuilder(builder);
                break;
            case SPACIALSTATION:
                AddSpacialStationController addSpacialStationController =
                        (AddSpacialStationController) getMainController().load("addSpacialStation");
                addSpacialStationController.setBuilder(builder);
                break;
            case FIGHTER:
            case DESTROYER:
                AddWeaponsController addWeaponsController =
                        (AddWeaponsController) getMainController().load("addWeapons");
                addWeaponsController.setBuilder(builder);
                addWeaponsController.setSpaceshipType(spaceshipType);
        }
    }

    @FXML
    public void addMore(ActionEvent event) {
        addDefense();
        AddDefensesController addDefensesController =
                (AddDefensesController) getMainController().load("addDefenses");
        addDefensesController.setBuilder(builder);
        addDefensesController.setDefenses(defenses);
        addDefensesController.setSpaceshipType(spaceshipType);
    }
}
