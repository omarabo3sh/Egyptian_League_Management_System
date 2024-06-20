package com.example.egyptian_league_management_system;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {


    private ResourceBundle resources;
    private Stage stage;
    private Scene scene;
    private Parent root;



    @FXML

        public void startBtnClick(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("choose.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }

}

