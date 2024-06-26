package com.example.egyptian_league_management_system.Controllers;

import com.example.egyptian_league_management_system.Application;
import com.example.egyptian_league_management_system.Entities.Match;
import com.example.egyptian_league_management_system.Entities.Team;
import com.example.egyptian_league_management_system.Operations.TeamOperations;

import com.example.egyptian_league_management_system.Operations.Team_MatchOperation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

import java.util.List;

public class TeamsManagementController {

    @FXML
    private Label infoLabel;

    @FXML
    private TextField teamNameField;


    private Team_MatchOperation teamMatchOperation = new Team_MatchOperation();


    private TeamOperations teamOperations = new TeamOperations();

    public void onDisplayTeamInformationClick(ActionEvent event) {
        String teamName = teamNameField.getText();
        Team team = teamOperations.getTeamByName(teamName);

        String information;
        if (team != null && team.getName() != null) {

            if (team.getPlayers() != null) {
              return;
            }

            information = "Team Name: " + team.getName() + "  " +
                    "Captain: " + team.getCaptainName() + "  " +
                    "Total Score: " + team.getTotalScore() ;
        } else {
            information = "Team not found.";
        }

        infoLabel.setText(information);
        teamNameField.clear();
    }









public void onBackClick(ActionEvent event) throws IOException, IOException {
    Application.switchScene(event, "choose.fxml");
}

public void onAddNewTeamClick(ActionEvent event)  throws IOException {
    Application.switchScene(event,"AddNewTeam.fxml");

}

public void onUpdateTeamInformationClick(ActionEvent event) throws IOException, IOException {
    Application.switchScene(event,"updateTeamInformation.fxml");

}




    public void onSearchForTeamIClick(ActionEvent event) throws IOException {
        String teamName = teamNameField.getText().trim();
        if (teamName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a team name.");
            alert.showAndWait();
            return;
        }

        Team team = teamOperations.getTeamByName(teamName);

        if (team != null && team.getName() != null && team.getName().equals(teamName)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Team Found");
            alert.setHeaderText(null);
            alert.setContentText("Team found!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Team Not Found");
            alert.setHeaderText(null);
            alert.setContentText("Team not found!");
            alert.showAndWait();
        }

    }
    public void onDisplayTeamMatchesClick(ActionEvent event) {
        String teamName = teamNameField.getText().trim();
        if (teamName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a team name.");
            alert.showAndWait();
            return;
        }

        Team team = teamOperations.getTeamByName(teamName);

        if (team != null && team.getName() != null && team.getName().equals(teamName)) {
            team = teamOperations.getTeamMatches(team);

            List<Match> matches = team.getMatches();

            if (matches != null && !matches.isEmpty()) {
                StringBuilder matchInfo = new StringBuilder();
                matchInfo.append("Matches for Team: ").append(team.getName()).append(" ");

                for (Match match : matches) {
                    matchInfo.append("Match ID: ").append(match.getId()).append(" ")
                            .append("Date: ").append(match.getDate()).append(" ");
                }

                infoLabel.setText(matchInfo.toString());
            } else {
                infoLabel.setText("No matches found for team: " + team.getName());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Team Not Found");
            alert.setHeaderText(null);
            alert.setContentText("Team not found!");
            alert.showAndWait();
        }
    }


    public void onDisplayTeamDetailedScoresClick(ActionEvent event) {

        // onDisplayTeamInformationClick( event);
    }
}


