package com.example.egyptian_league_management_system;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MatchController {
    AnchorPane scene2AnchorPane;
    public void matchOnClick(ActionEvent actionEvent) throws IOException {
        new Switch(scene2AnchorPane, "Player.fxml");
    }
}
