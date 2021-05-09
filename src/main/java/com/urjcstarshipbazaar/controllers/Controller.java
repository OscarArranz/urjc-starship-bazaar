package com.urjcstarshipbazaar.controllers;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class Controller implements Initializable {

    private NavigationController mainController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setMainController(NavigationController mainController) {
        this.mainController = mainController;
    }

    public NavigationController getMainController() {
        return mainController;
    }

}
