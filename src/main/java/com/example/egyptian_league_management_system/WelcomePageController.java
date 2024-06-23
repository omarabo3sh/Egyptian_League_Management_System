package com.example.egyptian_league_management_system;

import java.io.IOException;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


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





}

