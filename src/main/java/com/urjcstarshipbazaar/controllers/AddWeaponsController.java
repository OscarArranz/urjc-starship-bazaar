package com.urjcstarshipbazaar.controllers;

import com.urjcstarshipbazaar.models.builders.DestroyerBuilder;
import com.urjcstarshipbazaar.models.builders.FighterBuilder;
import com.urjcstarshipbazaar.models.builders.SpaceshipBuilder;
import com.urjcstarshipbazaar.models.spaceships.Spaceship;
import com.urjcstarshipbazaar.models.spaceships.SpaceshipType;
import com.urjcstarshipbazaar.models.spaceships.components.PropellerType;
import com.urjcstarshipbazaar.models.spaceships.components.Weapon;
import com.urjcstarshipbazaar.models.spaceships.components.WeaponType;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AddWeaponsController extends Controller {

    @FXML
    private TextField potency;

    @FXML
    private ComboBox weaponType;

    @FXML
    private Button addMore;

    private SpaceshipBuilder builder;

    private List<Weapon> weapons;

    private SpaceshipType spaceshipType;

    public AddWeaponsController() {
        weapons = new ArrayList<>();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<WeaponType> weaponTypes = Arrays.asList(WeaponType.values());
        ObservableList<WeaponType> types = FXCollections.observableArrayList(weaponTypes);
        weaponType.setItems(types);
    }

    public void setBuilder(SpaceshipBuilder builder) {
        this.builder = builder;
    }

    public void setSpaceshipType(SpaceshipType spaceshipType) {
        this.spaceshipType = spaceshipType;

        switch (spaceshipType) {
            case DESTROYER:
                addMore.setManaged(false);
                addMore.setVisible(false);
                break;
            case FIGHTER:
                if(weapons.size() > 1) {
                    addMore.setManaged(false);
                    addMore.setVisible(false);
                }
        }
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    private void addWeapon() {
        Weapon weapon = new Weapon();
        weapon.setPotencyGj(Double.parseDouble(potency.getText()));
        weapon.setWeaponType((WeaponType) weaponType.getValue());

        switch (spaceshipType) {
            case DESTROYER: ((DestroyerBuilder) builder).setWeapon(weapon);
                break;
            case FIGHTER:
                weapons.add(weapon);
                ((FighterBuilder) builder).setWeapons(weapons);
        }
    }

    @FXML
    public void nextStep(ActionEvent event) {
        addWeapon();

        Spaceship spaceship = null;
        SpaceshipService spaceshipService = new SpaceshipService();

        switch (spaceshipType) {
            case DESTROYER: spaceship = ((DestroyerBuilder) builder).getSpaceship();
                break;
            case FIGHTER: spaceship = ((FighterBuilder) builder).getSpaceship();
        }

        boolean save = spaceshipService.save(spaceship);

        if(!save) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al guardar nave");
            alert.setContentText("Ha ocurrido un error al guardar la nave, por favor vuelva a intentarlo.");
            alert.show();

            return;
        }

        getMainController().load("addSpaceship");
    }

    @FXML
    public void addMore(ActionEvent event) {
        addWeapon();

        AddWeaponsController addWeaponsController = (AddWeaponsController) getMainController().load("addWeapons");
        addWeaponsController.setBuilder(builder);
        addWeaponsController.setWeapons(weapons);
        addWeaponsController.setSpaceshipType(spaceshipType);
    }
}
