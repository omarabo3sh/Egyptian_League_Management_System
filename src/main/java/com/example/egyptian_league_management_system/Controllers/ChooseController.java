package com.example.egyptian_league_management_system.Controllers;

import com.example.egyptian_league_management_system.Application;
import javafx.event.ActionEvent;

import java.io.IOException;

import static com.example.egyptian_league_management_system.Application.switchScene;

public class ChooseController {

    public void onShowTeamsClick(ActionEvent event) throws IOException {
        switchScene(event,"team.fxml");
    }


    public void onBackClick(ActionEvent event) throws IOException {
        switchScene(event,"welcomePage.fxml");
    }
    public void onTeamsManagementClick(ActionEvent event) throws IOException {
        switchScene(event,"teamsManagement.fxml");

    }


    public void onMatchesManagementClick(ActionEvent event) throws IOException{
      switchScene(event,"matchesManagement.fxml");

    }

    public void onPlayerManagementClick(ActionEvent event) throws IOException{
        switchScene(event,"playerManagement.fxml");

    }

    public void onAdditionalManagementClick(ActionEvent event) throws IOException {
        switchScene(event,"additionalManagement.fxml");
    }
}
