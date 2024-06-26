package com.example.egyptian_league_management_system.Controllers;

import com.example.egyptian_league_management_system.Application;
import com.example.egyptian_league_management_system.Entities.Player;
import com.example.egyptian_league_management_system.Entities.Team;
import com.example.egyptian_league_management_system.Operations.PlayerOperations;
import com.example.egyptian_league_management_system.Operations.TeamOperations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.example.egyptian_league_management_system.Application.showAlert;

public class AddNewPlayerController {


    @FXML
    private TextField nameField;
    @FXML
    private TextField numberField;
    @FXML
    private TextField positionField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField scoreField;
    @FXML
    private TextField rankField;
    @FXML
    private TextField teamField;

    PlayerOperations playerOperations =new PlayerOperations();
    public void onBackClick(ActionEvent event) throws IOException {
    Application.switchScene(event, "playerManagement.fxml");
}
    public void onEnterClick(ActionEvent event) {
        try {
            String name = nameField.getText();
            String number = numberField.getText();
            String position = positionField.getText();
            int age = Integer.parseInt(ageField.getText());
            int score = Integer.parseInt(scoreField.getText());
            int rank = Integer.parseInt(rankField.getText());
            int teamId = Integer.parseInt(teamField.getText());

            TeamOperations teamOperations = new TeamOperations();
            Team team = teamOperations.getTeamById(teamId);

            if (team == null) {
                showAlert("Error", "Team not found.");
                return;
            }

            Player newPlayer = new Player();
            newPlayer.setName(name);
            newPlayer.setNumber(number);
            newPlayer.setPosition(position);
            newPlayer.setAge(age);
            newPlayer.setScore(score);
            newPlayer.setRank(rank);
            newPlayer.setTeam(team);

            playerOperations.insertPlayer(newPlayer);

            nameField.clear();
            numberField.clear();
            positionField.clear();
            ageField.clear();
            scoreField.clear();
            rankField.clear();
            teamField.clear();

            showAlert("Success", "Player information added successfully.");
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid numerical values for age, score, rank, and team ID.");
        }
    }
}
