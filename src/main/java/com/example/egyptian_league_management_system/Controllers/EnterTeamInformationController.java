package com.example.egyptian_league_management_system.Controllers;

import com.example.egyptian_league_management_system.Application;
import com.example.egyptian_league_management_system.Entities.Team;
import com.example.egyptian_league_management_system.Operations.TeamOperations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class EnterTeamInformationController {

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField captainNameField;
    @FXML
    private TextField totalScoreField;
    @FXML
    private TextField matchesField;

    private TeamOperations teamOperations = new TeamOperations();

    public void onBackClick(ActionEvent event) throws IOException {
        Application.switchScene(event, "teamsManagement.fxml");
    }

    public void onEnterClick(ActionEvent event) {
        String id = idField.getText();
        String name = nameField.getText();
        String captainName = captainNameField.getText();
        String totalScore = totalScoreField.getText();
        String matches = matchesField.getText();

        Team newTeam = new Team();
        newTeam.setName(name);
        newTeam.setCaptainName(captainName);
        newTeam.setTotalScore(Integer.parseInt(totalScore));

        teamOperations.insertTeam(newTeam);

        idField.clear();
        nameField.clear();
        captainNameField.clear();
        totalScoreField.clear();
        matchesField.clear();
    }
}
