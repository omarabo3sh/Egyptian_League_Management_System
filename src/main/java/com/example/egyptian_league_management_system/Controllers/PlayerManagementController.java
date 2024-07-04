package com.example.egyptian_league_management_system.Controllers;

import com.example.egyptian_league_management_system.Entities.Player;

import com.example.egyptian_league_management_system.Operations.PlayerOperations;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.example.egyptian_league_management_system.Application.showAlert;
import static com.example.egyptian_league_management_system.Application.switchScene;

public class PlayerManagementController {
    @FXML
    private Label infoLabel;

    @FXML
    private TextField playerNameField;
    private final PlayerOperations playerOperations = new PlayerOperations();

    public void onSearchForPlayerClick( ) {
        String playerName = playerNameField.getText().trim();
        if (playerName.isEmpty()) {
            showAlert("Input Error", "Please enter a player name.");
            return;
        }

        Player player = playerOperations.getPlayerByName(playerName);

        if (player != null && player.getName() != null && player.getName().equals(playerName)) {
            showAlert("Player Found", "Player found!");
        } else {
            showAlert("Player Not Found", "Player not found!");
        }
        playerNameField.clear();
    }

    public void onDisplayPlayerInformationClick( ) {
        String playerName = playerNameField.getText();
        Player player = playerOperations.getPlayerByName(playerName);

        if (playerName.isEmpty()) {
            showAlert("Input Error", "Please enter a player name.");
            return;
        }

        if (player != null) {
            String data = "Player Name: " + player.getName() + " " +
                    "Number: " + player.getNumber() + " " +
                    "Position: " + player.getPosition() + "\n" +
                    "Age: " + player.getAge() + " " +
                    "Score: " + player.getScore() + " " +
                    "Rank: " + player.getRank();

            infoLabel.setText(data);
        } else {
            infoLabel.setText("Player not found.");
        }
        playerNameField.clear();
    }


    public void onDisplayPlayerPositionClick( ) {
        String playerName = playerNameField.getText();
        Player player = playerOperations.getPlayerByName(playerName);
        if (playerName.isEmpty()) {
            showAlert("Input Error", "Please enter a player name.");
            return;
        }
        if (player != null) {
            String data = "Position: " + player.getPosition();
            infoLabel.setText(data);
        } else {
            infoLabel.setText("Player not found.");
        }
        playerNameField.clear();
    }


    public void onBackClick(ActionEvent event) throws  IOException {
        switchScene(event, "choose.fxml");
    }

    public void onAddNewPlayerClick(ActionEvent event) throws IOException {
        switchScene(event,"addNewPlayer.fxml");

    }

    public void onUpdatePlayerInformationClick(ActionEvent event) throws IOException {
        switchScene(event, "updatePlayerInformation.fxml");

    }






}

