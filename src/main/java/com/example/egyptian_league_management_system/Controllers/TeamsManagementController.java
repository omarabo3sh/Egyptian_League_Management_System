package com.example.egyptian_league_management_system.Controllers;

import com.example.egyptian_league_management_system.Application;
import javafx.event.ActionEvent;

import java.io.IOException;

public class TeamsManagementController {
    public void onBackClick(ActionEvent event) throws IOException {
        Application.switchScene(event, "choose.fxml");
    }

    public void onEnterTeamInformationClick(ActionEvent event)  throws IOException {
        Application.switchScene(event,"enterTeamInformation.fxml");

    }

    public void onUpdateTeamInformationClick(ActionEvent event)  throws IOException {
        Application.switchScene(event,"updateTeamInformation.fxml");

    }
    public void onDisplayTeamInformationClick(ActionEvent event)  throws IOException {
        Application.switchScene(event,"displayTeamInformation.fxml");

    }
    public void onDisplayTeamIMatchesClick(ActionEvent event)  throws IOException {
        Application.switchScene(event,"displayTeamMatches.fxml");

    }
    public void onDisplayTeamDetailedScoresClick(ActionEvent event)  throws IOException {
        Application.switchScene(event,"displayTeamDetailedScores.fxml");

    }
}
