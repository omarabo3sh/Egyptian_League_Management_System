package com.example.egyptian_league_management_system.Controllers;

import javafx.event.ActionEvent;

import java.io.IOException;

import static com.example.egyptian_league_management_system.Application.switchScene;

public class UpdateMatchInformationController {


    public void onBackClick(ActionEvent event) throws IOException {
        switchScene(event, "matchesManagement.fxml");
    }

    public void onAddMatchClick(ActionEvent event) {
    }
}
