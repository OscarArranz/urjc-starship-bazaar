package com.urjcstarshipbazaar.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class NavigationController implements Initializable {

    private Stage stage;

    @FXML
    private HBox windowBar;

    @FXML
    private Label windowTitle;

    @FXML
    private VBox sideBar;

    @FXML
    private Pane appContent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeWindowBar();
        toggleSidebar(false);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/welcome.fxml"));
            AnchorPane view = loader.load();
            WelcomeController welcomeController = loader.getController();
            welcomeController.setMainController(this);
            appContent.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initializeWindowBar() {
        windowTitle.setText("URJC Starship Bazaar");

        windowBar.setOnMousePressed(pressEvent -> windowBar.setOnMouseDragged(dragEvent -> {
            stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
            stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
        }));
    }

    public void toggleSidebar(boolean visible) {
        sideBar.setVisible(visible);
        sideBar.setManaged(visible);
    }

    public void initializeSideBar() {
        toggleSidebar(true);

        Map<String, String> sideBarOptions = new LinkedHashMap<>();
        sideBarOptions.put("Perfil", "profile.fxml");
        sideBarOptions.put("Comprar", "buy.fxml");
        sideBarOptions.put("Vender", "sell.fxml");
        sideBarOptions.put("Valoraciones", "reviews.fxml");

        List<Button> optionButtons = new ArrayList<>();
        sideBarOptions.forEach((text, resource) -> {
            Button button = new Button(text);
            button.setOnAction(event -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/" + resource));
                    VBox view = loader.load();
                    appContent.getChildren().setAll(view);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            button.getStyleClass().add("sidebar-option");
            button.setPrefWidth(Double.MAX_VALUE);
            button.setContentDisplay(ContentDisplay.CENTER);
            optionButtons.add(button);
        });

        sideBar.getChildren().setAll(optionButtons);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void closeWindow(ActionEvent event) {
        stage.close();
    }

    public void minimizeWindow(ActionEvent event) {
        stage.setIconified(true);
    }

    public void loadProfile() {
        initializeSideBar();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/profile.fxml"));
            Pane view = loader.load();
            appContent.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadWelcome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/welcome.fxml"));
            AnchorPane view = loader.load();
            WelcomeController welcomeController = loader.getController();
            welcomeController.setMainController(this);
            appContent.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadRegister() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/register.fxml"));
            AnchorPane view = loader.load();
            RegisterController registerController = loader.getController();
            registerController.setMainController(this);
            appContent.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
