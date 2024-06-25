package test;

import com.example.egyptian_league_management_system.Application;
import com.example.egyptian_league_management_system.Entities.Player;

import com.example.egyptian_league_management_system.Operations.PlayerOperations;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class PlayerManagementController {
    @FXML
    private Label infoLabel;

    @FXML
    private TextField teamNameField;
    private PlayerOperations playerOperations = new PlayerOperations();

        public void onDisplayPlayerInformationClick(ActionEvent event) {
            String playerName = teamNameField.getText();
            Player player = playerOperations.getPlayerByName(playerName);

            if (player != null) {
                String data = "Player Name: " + player.getName() + "\n" +
                        "Number: " + player.getNumber() + "\n" +
                        "Position: " + player.getPosition() + "\n" +
                        "Age: " + player.getAge() + "\n" +
                        "Score: " + player.getScore() + "\n" +
                        "Rank: " + player.getRank();

                infoLabel.setText(data);
            } else {
                infoLabel.setText("Player not found.");
            }
        }


        public void onDisplayPlayerPositionClick(ActionEvent event) {
            String  playerName = teamNameField.getText();
            Player player = playerOperations.getPlayerByName(playerName);

            if (player != null) {
                String data = "Position: " + player.getPosition();
                infoLabel.setText(data);
            } else {
                infoLabel.setText("Player not found.");
            }
        }


        public void onBackClick(ActionEvent event) throws IOException, IOException {
            Application.switchScene(event, "choose.fxml");
        }

        public void onEnterPlayerInformationClick(ActionEvent event)  throws IOException {
           Application.switchScene(event,"enterPlayerInformation.fxml");

        }

        public void onUpdatePlayerInformationClick(ActionEvent event) throws IOException, IOException {
          Application.switchScene(event,"updatePlayerInformation.fxml");

        }

        public void onSearchForPlayerClick(ActionEvent event) {
           String  playerName = teamNameField.getText();
            Player player = playerOperations.getPlayerByName(playerName);

            if (player != null) {
                String data = "Player Name: " + player.getName() + "\n" +
                        "Number: " + player.getNumber() + "\n" +
                        "Position: " + player.getPosition() + "\n" +
                        "Age: " + player.getAge() + "\n" +
                        "Score: " + player.getScore() + "\n" +
                        "Rank: " + player.getRank();

                infoLabel.setText(data);
            } else {
                infoLabel.setText("Player not found.");
            }
        }
        }

