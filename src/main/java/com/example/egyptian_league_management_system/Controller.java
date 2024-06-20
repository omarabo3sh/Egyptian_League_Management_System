package com.example.egyptian_league_management_system;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class Controller {


    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane scene1AnchorPane;


    @FXML
    public void startBtnClick(ActionEvent actionEvent) throws IOException, IOException {
        new Switch(scene1AnchorPane, "Player.fxml");
    }

}

