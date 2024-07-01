package com.example.egyptian_league_management_system.Controllers;

import com.example.egyptian_league_management_system.Entities.Match;
import com.example.egyptian_league_management_system.Entities.Refree;
import com.example.egyptian_league_management_system.Entities.Refree_Match;
import com.example.egyptian_league_management_system.Entities.Stadium;
import com.example.egyptian_league_management_system.Entities.Team;
import com.example.egyptian_league_management_system.Entities.Team_Match;
import com.example.egyptian_league_management_system.Operations.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.example.egyptian_league_management_system.Application.showAlert;
import static com.example.egyptian_league_management_system.Application.switchScene;

public class UpdateMatchInformationController {
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField scoreField;
    @FXML
    private TextField stadiumField;
    @FXML
    private TextField refereeField;
    @FXML
    private TextField team1Field;
    @FXML
    private TextField team2Field;
    @FXML
    private TextField matchIdField;

    private final MatchOperations matchOperations = new MatchOperations();
    private final Team_MatchOperation teamMatchOperation = new Team_MatchOperation();
    private final Refree_MatchOperations refereeMatchOperation = new Refree_MatchOperations();
    private final StadiumOperations stadiumOperations = new StadiumOperations();


    public void onUpdateMatchClick() {
        try {
            int matchId = Integer.parseInt(matchIdField.getText().trim());
            Match match = matchOperations.getMatchById(matchId);

            if (match == null) {
                showAlert("error", "Match not found.");
                return;
            }

            LocalDate date = datePicker.getValue();
            if (date == null) {
                showAlert("error", "Please select a date.");
                return;
            }

            String dateString = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            int score = Integer.parseInt(scoreField.getText().trim());

            match.setDate(dateString);
            match.setScore(score);

            // Update stadium details
            Stadium stadium = stadiumOperations.getStadiumByName(stadiumField.getText().trim());
            if (stadium != null) {
                match.setStadium(stadium);
            } else {
                showAlert("error", "Stadium not found.");
                return;
            }

            // Update referee details
            Refree refree = refereeMatchOperation.getRefreeByName(refereeField.getText().trim());
            if (refree != null) {
                Refree_Match refereeMatch = new Refree_Match();
                refereeMatch.setMatch(match);
                refereeMatch.setRefree(refree);
                refereeMatchOperation.update(refereeMatch);  // Call the update method here
            } else {
                showAlert("error", "Referee not found.");
                return;
            }

            // Update team details
            Team team1 = new Team();
            team1.setName(team1Field.getText().trim());

            Team team2 = new Team();
            team2.setName(team2Field.getText().trim());

            Team_Match teamMatch1 = new Team_Match(team1, match);
            Team_Match teamMatch2 = new Team_Match(team2, match);

            teamMatchOperation.update(teamMatch1);
            teamMatchOperation.update(teamMatch2);

            matchOperations.updateMatch(match);

            showAlert("info", "Match updated successfully.");

            datePicker.setValue(null);
            scoreField.clear();
            stadiumField.clear();
            refereeField.clear();
            team1Field.clear();
            team2Field.clear();
            matchIdField.clear();
        } catch (NumberFormatException e) {
            showAlert("error", "Invalid input. Please check your entries.");
        } catch (Exception e) {
            showAlert("error", "Error occurred while updating match: " + e.getMessage());
        }
    }


    public void onBackClick(ActionEvent event) throws IOException {
        switchScene(event, "matchesManagement.fxml");
    }
}
