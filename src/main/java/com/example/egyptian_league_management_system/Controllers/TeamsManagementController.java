package com.example.egyptian_league_management_system.Controllers;

import com.example.egyptian_league_management_system.Application;
import com.example.egyptian_league_management_system.Entities.Team;
import com.example.egyptian_league_management_system.Operations.TeamOperations;
import javafx.css.Match;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class TeamsManagementController {

    @FXML
    private Label infoLabel;

    @FXML
    private TextField teamNameField;



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
    }

    public void onDisplayTeamMatchesClick(ActionEvent event) {
        String teamName = teamNameField.getText();
        Team team = teamOperations.getTeamByName(teamName);

        if (team != null && team.getName() != null) {
            team = teamOperations.getTeamMatches(team); // Fetch team matches
            StringBuilder matchesInfo = new StringBuilder("Matches:\n");
            for (Match match : team.getMatches()) {
                matchesInfo.append("Match ID: ").append(match.getId()).append(", ")
                        .append("Opponent: ").append(match.getOpponent()).append(", ")
                        .append("Date: ").append(match.getDate()).append("\n");
            }
            infoLabel.setText(matchesInfo.toString());
        } else {
            infoLabel.setText("Team not found.");
        }
    }

    public void onDisplayTeamDetailedScoresClick(ActionEvent event) {

        String data="";

        infoLabel.setText(data);
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





        private TeamOperations teamOperations = new TeamOperations();

        public void onSearchForTeamIClick(ActionEvent event) throws IOException {
            String teamName = teamNameField.getText();
            Team team = teamOperations.getTeamByName(teamName);

            if (team != null && team.getName() != null) {
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




}