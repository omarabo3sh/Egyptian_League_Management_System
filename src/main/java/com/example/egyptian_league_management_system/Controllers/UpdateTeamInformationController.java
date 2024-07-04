package com.example.egyptian_league_management_system.Controllers;

import com.example.egyptian_league_management_system.Entities.Team;
import com.example.egyptian_league_management_system.Operations.TeamOperations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

import static com.example.egyptian_league_management_system.Application.showAlert;
import static com.example.egyptian_league_management_system.Application.switchScene;

public class UpdateTeamInformationController {

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField captainNameField;
    @FXML
    private TextField totalScoreField;

    private final TeamOperations teamOperations = new TeamOperations();

    public void onBackClick(ActionEvent event) throws IOException {
    switchScene(event, "teamsManagement.fxml");
    }

    public void onUpdateClick( ) {
try {
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
            idField.clear();
        nameField.clear();
        captainNameField.clear();
        totalScoreField.clear();
    showAlert("Success", "Team updated successfully: "+name);
} catch (NumberFormatException e) {
    showAlert("Input Error", "Please enter valid data.");
}
    }


}
