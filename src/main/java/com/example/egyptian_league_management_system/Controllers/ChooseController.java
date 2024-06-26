package com.example.egyptian_league_management_system.Controllers;

import com.example.egyptian_league_management_system.Application;
import javafx.event.ActionEvent;

import java.io.IOException;

public class ChooseController {

    public void onShowTeamsClick(ActionEvent event) throws IOException {
        Application.switchScene(event,"team.fxml");
    }


    public void onBackClick(ActionEvent event) throws IOException {
        Application.switchScene(event,"welcomePage.fxml");
    }
    public void onTeamsManagementClick(ActionEvent event) throws IOException {
        Application.switchScene(event,"teamsManagement.fxml");

    }


    public void onMatchesManagementClick(ActionEvent event) throws IOException{
       Application.switchScene(event,"matchesManagement.fxml");

    }

    public void onPlayerManagementClick(ActionEvent event) throws IOException{
        Application.switchScene(event,"playerManagement.fxml");

    }

    public void onAdditionalManagementClick(ActionEvent event) throws IOException {
        Application.switchScene(event,"additionalManagement.fxml");
    }
}
