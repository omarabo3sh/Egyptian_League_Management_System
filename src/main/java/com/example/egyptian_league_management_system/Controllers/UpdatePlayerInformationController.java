package com.example.egyptian_league_management_system.Controllers;

import com.example.egyptian_league_management_system.Entities.Player;
import com.example.egyptian_league_management_system.Entities.Team;
import com.example.egyptian_league_management_system.Operations.PlayerOperations;
import com.example.egyptian_league_management_system.Operations.TeamOperations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.example.egyptian_league_management_system.Application.switchScene;

public class UpdatePlayerInformationController {


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

    private PlayerOperations playerOperations = new PlayerOperations();

    public void onBackClick(ActionEvent event) throws IOException {
      switchScene(event, "playerManagement.fxml");
    }

    public void onUpdateClick(ActionEvent event) {
        try {

            String name = nameField.getText();
            String number = numberField.getText();
            String position = positionField.getText();
            int age = Integer.parseInt(ageField.getText());
            int score = Integer.parseInt(scoreField.getText());
            int rank = Integer.parseInt(rankField.getText());
            int teamId = Integer.parseInt(teamField.getText());

            Player player = playerOperations.getPlayerByName(name);
            if (player == null) {
                System.err.println("Player not found: " + name);
                return;
            }


            TeamOperations teamOperations = new TeamOperations();
            Team team = teamOperations.getTeamById(teamId);
            if (team == null) {
                System.err.println("Team not found: " + teamId);
                return;
            }

            player.setNumber(number);
            player.setPosition(position);
            player.setAge(age);
            player.setScore(score);
            player.setRank(rank);
            player.setTeam(team);

            playerOperations.updatePlayer(player);

            nameField.clear();
            numberField.clear();
            positionField.clear();
            ageField.clear();
            scoreField.clear();
            rankField.clear();
            teamField.clear();

            System.out.println("Player updated successfully: " + name);

        } catch (NumberFormatException e) {
            System.err.println("Invalid input: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
