package com.example.egyptian_league_management_system.Controllers;

import com.example.egyptian_league_management_system.Application;
import com.example.egyptian_league_management_system.Entities.Match;
import com.example.egyptian_league_management_system.Entities.Team;
import com.example.egyptian_league_management_system.Operations.TeamOperations;
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

    private TeamOperations teamOperations = new TeamOperations();

    public void onDisplayTeamInformationClick(ActionEvent event) {
        String teamName = teamNameField.getText();
        Team team = teamOperations.getTeamByName(teamName);

        String information;
        if (team != null && team.getName() != null) {
            information = "Team Name: " + team.getName() + "\n" +
                    "Captain: " + team.getCaptainName() + "\n" +
                    "Total Score: " + team.getTotalScore();
        } else {
            information = "Team not found.";
        }

        infoLabel.setText(information);
        teamNameField.clear();
    }

    public void onBackClick(ActionEvent event) throws IOException {
        Application.switchScene(event, "choose.fxml");
    }

    public void onAddNewTeamClick(ActionEvent event) throws IOException {
        Application.switchScene(event, "AddNewTeam.fxml");
    }

    public void onUpdateTeamInformationClick(ActionEvent event) throws IOException {
        Application.switchScene(event, "updateTeamInformation.fxml");
    }

    public void onSearchForTeamIClick(ActionEvent event) {
        String teamName = teamNameField.getText().trim();
        if (teamName.isEmpty()) {
            showAlert("Input Error", "Please enter a team name.");
            return;
        }

        Team team = teamOperations.getTeamByName(teamName);

        if (team != null && team.getName() != null && team.getName().equals(teamName)) {
            showAlert("Team Found", "Team found!");
        } else {
            showAlert("Team Not Found", "Team not found!");
        }
    }

    public void onDisplayTeamMatchesClick(ActionEvent event) {
        String teamName = teamNameField.getText().trim();
        if (teamName.isEmpty()) {
            showAlert("Input Error", "Please enter a team name.");
            return;
        }

        Team team = teamOperations.getTeamByName(teamName);

        if (team != null && team.getName() != null && team.getName().equals(teamName)) {
            team = teamOperations.getTeamMatches(team);

            List<Match> matches = team.getMatches();

            if (matches != null && !matches.isEmpty()) {
                StringBuilder matchInfo = new StringBuilder();
                matchInfo.append("Matches for Team: ").append(team.getName()).append("\n");

                for (Match match : matches) {
                    matchInfo.append("Match ID: ").append(match.getId()).append("\n")
                            .append("Date: ").append(match.getDate()).append("\n");
                }

                infoLabel.setText(matchInfo.toString());
            } else {
                infoLabel.setText("No matches found for team: " + team.getName());
            }
        } else {
            showAlert("Team Not Found", "Team not found!");
        }
    }

    public void onDisplayTeamDetailedScoresClick(ActionEvent event) {
        String teamName = teamNameField.getText().trim();
        if (teamName.isEmpty()) {
            showAlert("Input Error", "Please enter a team name.");
            return;
        }

        Team team = teamOperations.getTeamByName(teamName);

        if (team != null && team.getName() != null && team.getName().equals(teamName)) {
            team = teamOperations.getTeamPlayers(team);

            List<Team> teamsWithPlayers = teamOperations.getTeamsSorted();

            StringBuilder playersScores = new StringBuilder();
            playersScores.append("Detailed Scores for Team: ").append(team.getName()).append("\n");

            for (Team t : teamsWithPlayers) {
                if (t.getName().equals(team.getName())) {
                    for (int i = 0; i < t.getPlayers().size(); i++) {
                        playersScores.append("Player Name: ").append(t.getPlayers().get(i).getName()).append("\n")
                                .append("Player Score: ").append(t.getPlayers().get(i).getScore()).append("\n");
                    }
                }
            }

            infoLabel.setText(playersScores.toString());
        } else {
            showAlert("Team Not Found", "Team not found!");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
