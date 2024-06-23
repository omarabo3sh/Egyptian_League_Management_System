package com.example.egyptian_league_management_system;

import java.io.IOException;
import java.util.Objects;

import javafx.animation.ScaleTransition;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WelcomePageController {




    @FXML

        public void startBtnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("choice.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
            stage.setScene(scene);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("Styles.css")).toExternalForm());

        stage.show();
        }
    @FXML
    private Button startBtn;

    @FXML
    public void initialize() {
        setupButtonEffects();
    }

    public void setupButtonEffects() {
        ScaleTransition hoverEnter = new ScaleTransition(Duration.millis(150), startBtn);
        hoverEnter.setToX(1.125);
        hoverEnter.setToY(1.125);

        ScaleTransition hoverExit = new ScaleTransition(Duration.millis(150), startBtn);
        hoverExit.setToX(1);
        hoverExit.setToY(1);

        ScaleTransition pressEnter = new ScaleTransition(Duration.millis(150), startBtn);
        pressEnter.setToX(1.025);
        pressEnter.setToY(1.025);

        ScaleTransition pressExit = new ScaleTransition(Duration.millis(150), startBtn);
        pressExit.setToX(1.125);
        pressExit.setToY(1.125);

        startBtn.setOnMouseEntered(e -> hoverEnter.playFromStart());
        startBtn.setOnMouseExited(e -> hoverExit.playFromStart());
        startBtn.setOnMousePressed(e -> pressEnter.playFromStart());
        startBtn.setOnMouseReleased(e -> pressExit.playFromStart());
    }

}

