package com.example.egyptian_league_management_system.Controllers;

import com.example.egyptian_league_management_system.Application;
import com.example.egyptian_league_management_system.Entities.Team;
import com.example.egyptian_league_management_system.Operations.TeamOperations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class TeamsManagementController {

    @FXML
    private Label infoLabel;


    @FXML
    private TextField teamNameField;

    private TeamOperations teamOperations = new TeamOperations();

    public void onDisplayTeamInformationClick(ActionEvent event) {
        String teamName = teamNameField.getText();
        Team team = teamOperations.getTeamByName(teamName);

        String data ;
        if (team != null && team.getName() != null) {
            data = "Team Name: " + team.getName() + "\n" +
                    "Captain: " + team.getCaptainName() + "\n" +
                    "Total Score: " + team.getTotalScore();
        } else {
            data = "Team not found.";
        }

        infoLabel.setText(data);
    }


    public void onDisplayTeamMatchesClick(ActionEvent event) {
        String data="";
        infoLabel.setText(data);
    }

    public void onDisplayTeamDetailedScoresClick(ActionEvent event) {

        String data="";

        infoLabel.setText(data);
    }


public void onBackClick(ActionEvent event) throws IOException, IOException {
    Application.switchScene(event, "choose.fxml");
}

public void onEnterTeamInformationClick(ActionEvent event)  throws IOException {
    Application.switchScene(event,"enterTeamInformation.fxml");

}

public void onUpdateTeamInformationClick(ActionEvent event) throws IOException, IOException {
    Application.switchScene(event,"updateTeamInformation.fxml");

}

    public void onSearchForTeamIClick(ActionEvent event) {
        String teamName = teamNameField.getText();
        Team team = teamOperations.getTeamByName(teamName);

        String data ;
        if (team != null && team.getName() != null) {
            data = "Team Name: " + team.getName() + "\n" +
                    "Captain: " + team.getCaptainName() + "\n" +
                    "Total Score: " + team.getTotalScore();
        } else {
            data = "Team not found.";
        }

        infoLabel.setText(data);
    }
}