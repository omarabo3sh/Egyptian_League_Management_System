package com.example.egyptian_league_management_system.Controllers;

import com.example.egyptian_league_management_system.Entities.Match;
import com.example.egyptian_league_management_system.Entities.Team;
import com.example.egyptian_league_management_system.Entities.Team_Match;
import com.example.egyptian_league_management_system.Operations.MatchOperations;
import com.example.egyptian_league_management_system.Operations.Team_MatchOperation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.example.egyptian_league_management_system.Application.showAlert;
import static com.example.egyptian_league_management_system.Application.switchScene;

public class AddNewMatchController {
    @FXML private DatePicker datePicker;
    @FXML private TextField scoreField;

    private MatchOperations matchOperations = new MatchOperations();
    private Team_MatchOperation teamMatchOperation = new Team_MatchOperation();

    @FXML private TextField stadiumField;
    @FXML private TextField refereeField;

    @FXML
    private void onAddMatchClick() {
        try {
            LocalDate date = datePicker.getValue();
            if (date == null) {
                showAlert("error", "Please select a date.");
                return;
            }

            String dateString = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            int score = Integer.parseInt(scoreField.getText().trim());

            Match match = new Match();
            match.setDate(dateString);
            match.setScore(score);
           // match.setStadium(stadiumField.getText()); // Assuming stadiumField is the FXML TextField for stadium info
           // match.setReferee(refereeField.getText()); // Assuming refereeField is the FXML TextField for referee info

            matchOperations.insertMatch(match);

            Team_Match teamMatch = new Team_Match();
            teamMatchOperation.insert(teamMatch);

            showAlert("info", "Match added successfully.");

            datePicker.setValue(null);
            scoreField.clear();
        } catch (NumberFormatException e) {
            showAlert("error", "Score must be a valid number.");
        } catch (Exception e) {
            showAlert("error", "Error occurred while adding match: " + e.getMessage());
        }
    }



    public void onBackClick(ActionEvent event) throws IOException {
        switchScene(event, "matchesManagement.fxml");
    }
}
