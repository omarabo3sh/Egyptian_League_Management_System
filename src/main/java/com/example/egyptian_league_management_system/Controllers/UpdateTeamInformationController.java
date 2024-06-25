package com.example.egyptian_league_management_system.Controllers;

import com.example.egyptian_league_management_system.Application;
import com.example.egyptian_league_management_system.Entities.Team;
import com.example.egyptian_league_management_system.Operations.TeamOperations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UpdateTeamInformationController {

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField captainNameField;
    @FXML
    private TextField totalScoreField;

    private TeamOperations teamOperations = new TeamOperations();

    public void onBackClick(ActionEvent event) throws IOException {
        Application.switchScene(event, "teamsManagement.fxml");
    }

    public void onUpdateClick(ActionEvent event) {

            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String captainName = captainNameField.getText();
            int totalScore = Integer.parseInt(totalScoreField.getText());

            Team team = new Team();
            team.setId(id);
            team.setName(name);
            team.setCaptainName(captainName);
            team.setTotalScore(totalScore);

            teamOperations.updateTeam(team);


    }
}