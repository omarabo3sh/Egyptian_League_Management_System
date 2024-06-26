package com.example.egyptian_league_management_system.Controllers;

import com.example.egyptian_league_management_system.Application;
import com.example.egyptian_league_management_system.Entities.Player;
import com.example.egyptian_league_management_system.Entities.Team;
import com.example.egyptian_league_management_system.Operations.PlayerOperations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UpdatePlayerInformationController {

    @FXML
    private TextField idField;
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
        Application.switchScene(event, "playerManagement.fxml");
    }

    public void onUpdateClick(ActionEvent event) {
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        String number = numberField.getText();
        String position = positionField.getText();
        int age = Integer.parseInt(ageField.getText());
        int score = Integer.parseInt(scoreField.getText());
        int rank = Integer.parseInt(rankField.getText());
        //int teamId = Integer.parseInt(teamField.getText());

        Player player = new Player();
        player.setId(id);
        player.setName(name);
        player.setNumber(number);
        player.setPosition(position);
        player.setAge(age);
        player.setScore(score);
        player.setRank(rank);
       // player.setTeam(new Team(teamId));

        playerOperations.updatePlayer(player);

        idField.clear();
        nameField.clear();
        numberField.clear();
        positionField.clear();
        ageField.clear();
        scoreField.clear();
        rankField.clear();
        teamField.clear();
    }
}
