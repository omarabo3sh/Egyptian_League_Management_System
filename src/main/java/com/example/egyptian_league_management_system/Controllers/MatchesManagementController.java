package com.example.egyptian_league_management_system.Controllers;

import javafx.event.ActionEvent;

import java.io.IOException;

import static com.example.egyptian_league_management_system.Application.switchScene;

public class MatchesManagementController {
    public void onBackClick(ActionEvent event) throws IOException {
        switchScene(event,"choose.fxml");
    }

    public void onUpdateCertainMatchClick(ActionEvent event) throws IOException {
        switchScene(event,"updateMatchInformation.fxml");
    }

    public void onAddNewMatchClick(ActionEvent event) throws IOException {
        switchScene(event,"addNewMatch.fxml");
    }

    public void onDisplayMatchesClick(ActionEvent event) throws IOException {
         switchScene(event,"match.fxml");
    }
    public void onDisplayHeldMatchesClick(ActionEvent event) throws IOException {
       switchScene(event,"heldedMatch.fxml");
    }

    public void onDisplayToBeHeldMatchesClick(ActionEvent event) throws IOException {
        switchScene(event,"toBeHeldMatch.fxml");

    }
}
