package com.example.egyptian_league_management_system.Controllers;

import com.example.egyptian_league_management_system.Entities.Match;
import com.example.egyptian_league_management_system.Entities.Refree_Match;
import com.example.egyptian_league_management_system.Entities.Stadium;
import com.example.egyptian_league_management_system.Entities.Team;
import com.example.egyptian_league_management_system.Entities.Team_Match;
import com.example.egyptian_league_management_system.Operations.MatchOperations;
import com.example.egyptian_league_management_system.Operations.Refree_MatchOperation;
import com.example.egyptian_league_management_system.Operations.StadiumOperations;
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
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField scoreField;

    private final MatchOperations matchOperations = new MatchOperations();
    private final Team_MatchOperation teamMatchOperation = new Team_MatchOperation();
    private final Refree_MatchOperation refereeMatchOperation = new Refree_MatchOperation();
    private final StadiumOperations stadiumOperations = new StadiumOperations();

    @FXML
    private TextField stadiumField;
    @FXML
    private TextField refereeField;
    @FXML
    private TextField team1Field;
    @FXML
    private TextField team2Field;

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

            // Create new match object
            Match match = new Match();
            match.setDate(dateString);
            match.setScore(score);

            // Insert match into database
            matchOperations.insertMatch(match);

            // Retrieve the generated match ID
            int matchId = match.getId();

            // Insert stadium details
            Stadium stadium = stadiumOperations.getStadiumByName(stadiumField.getText().trim());
            if (stadium != null) {
                match.setStadium(stadium);
                matchOperations.updateMatch(match);
            } else {
                showAlert("error", "Stadium not found.");
                return;
            }

            // Insert referee details
            Refree_Match refereeMatch = new Refree_Match();
            refereeMatch.setMatch(match);
            refereeMatchOperation.insert(refereeMatch);

            // Insert team details
            Team team1 = new Team();
            team1.setName(team1Field.getText().trim());

            Team team2 = new Team();
            team2.setName(team2Field.getText().trim());

            Team_Match teamMatch1 = new Team_Match(team1, match);
            Team_Match teamMatch2 = new Team_Match(team2, match);

            teamMatchOperation.insert(teamMatch1);
            teamMatchOperation.insert(teamMatch2);

            showAlert("info", "Match added successfully.");

            datePicker.setValue(null);
            scoreField.clear();
            stadiumField.clear();
            refereeField.clear();
            team1Field.clear();
            team2Field.clear();
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
