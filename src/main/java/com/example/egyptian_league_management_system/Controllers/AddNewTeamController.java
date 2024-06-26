package com.example.egyptian_league_management_system.Controllers;

import com.example.egyptian_league_management_system.Application;
import com.example.egyptian_league_management_system.Entities.Team;
import com.example.egyptian_league_management_system.Operations.TeamOperations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddNewTeamController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField captainNameField;
    @FXML
    private TextField totalScoreField;

    private final TeamOperations teamOperations = new TeamOperations();

    public void onBackClick(ActionEvent event) throws IOException {
        Application.switchScene(event, "teamsManagement.fxml");
    }

    public void onEnterClick(ActionEvent event) {

        String name = nameField.getText();
        String captainName = captainNameField.getText();
        String totalScore = totalScoreField.getText();

        Team newTeam = new Team();
        newTeam.setName(name);
        newTeam.setCaptainName(captainName);
        newTeam.setTotalScore(Integer.parseInt(totalScore));
        teamOperations.insertTeam(newTeam);

        nameField.clear();
        captainNameField.clear();
        totalScoreField.clear();

    }
}