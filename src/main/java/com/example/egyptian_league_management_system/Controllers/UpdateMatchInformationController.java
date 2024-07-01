package com.example.egyptian_league_management_system.Controllers;

import com.example.egyptian_league_management_system.Entities.Match;
import com.example.egyptian_league_management_system.Entities.Refree;
import com.example.egyptian_league_management_system.Entities.Refree_Match;
import com.example.egyptian_league_management_system.Entities.Stadium;
import com.example.egyptian_league_management_system.Entities.Team;
import com.example.egyptian_league_management_system.Entities.Team_Match;
import com.example.egyptian_league_management_system.Operations.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.example.egyptian_league_management_system.Application.showAlert;
import static com.example.egyptian_league_management_system.Application.switchScene;

public class UpdateMatchInformationController {
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField scoreField;
    @FXML
    private ComboBox<String> stadiumComboBox;
    @FXML
    private ComboBox<String> refereeComboBox;
    @FXML
    private ComboBox<String> team1ComboBox;
    @FXML
    private ComboBox<String> team2ComboBox;

    private final MatchOperations matchOperations = new MatchOperations();
    private final Team_MatchOperation teamMatchOperation = new Team_MatchOperation();
    private final Refree_MatchOperations refereeMatchOperation = new Refree_MatchOperations();
    private final StadiumOperations stadiumOperations = new StadiumOperations();
    private final TeamOperations teamOperations = new TeamOperations();
    private final RefreeOperations refereeOperations = new RefreeOperations();

    @FXML
    public void initialize() {
        // Populate ComboBoxes
        try {
            // Populate stadium ComboBox
            List<Stadium> stadiums = stadiumOperations.getAllStadiums();
            ObservableList<String> stadiumNames = FXCollections.observableArrayList();
            for (Stadium stadium : stadiums) {
                stadiumNames.add(stadium.getName());
            }
            stadiumComboBox.setItems(stadiumNames);

            // Populate referee ComboBox
            List<Refree> referees = refereeOperations.getAllReferees();
            ObservableList<String> refereeNames = FXCollections.observableArrayList();
            for (Refree referee : referees) {
                refereeNames.add(referee.getName());
            }
            refereeComboBox.setItems(refereeNames);

            // Populate team ComboBoxes
            List<Team> teams = teamOperations.getAll();
            ObservableList<String> teamNames = FXCollections.observableArrayList();
            for (Team team : teams) {
                teamNames.add(team.getName());
            }
            team1ComboBox.setItems(teamNames);
            team2ComboBox.setItems(teamNames);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onUpdateMatchClick() {
        try {
            // Get selected team names
            String selectedTeam1 = team1ComboBox.getValue();
            String selectedTeam2 = team2ComboBox.getValue();

            // Find match based on teams
            Match match = matchOperations.getMatchByTeams(selectedTeam1, selectedTeam2);

            if (match == null) {
                showAlert("error", "Match not found for selected teams.");
                return;
            }

            // Update date
            LocalDate date = datePicker.getValue();
            if (date == null) {
                showAlert("error", "Please select a date.");
                return;
            }
            String dateString = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            match.setDate(dateString);

            // Update score if provided
            String scoreText = scoreField.getText().trim();
            if (!scoreText.isEmpty()) {
                try {
                    int score = Integer.parseInt(scoreText);
                    match.setScore(score);
                } catch (NumberFormatException e) {
                    showAlert("error", "Invalid score format. Please enter a valid number.");
                    return;
                }
            }

            // Update stadium details
            String selectedStadium = stadiumComboBox.getValue();
            Stadium stadium = stadiumOperations.getStadiumByName(selectedStadium);
            if (stadium != null) {
                match.setStadium(stadium);
            } else {
                showAlert("error", "Stadium not found.");
                return;
            }

            // Update referee details
            String selectedReferee = refereeComboBox.getValue();
            Refree referee = refereeOperations.getRefreeByName(selectedReferee);
            if (referee != null) {
                Refree_Match refreeMatch = new Refree_Match();
                refreeMatch.setMatch(match);
                refreeMatch.setRefree(referee);
                refereeMatchOperation.update(refreeMatch);
            } else {
                showAlert("error", "Referee not found.");
                return;
            }

            // Update team details
            Team team1 = teamOperations.getTeamByName(selectedTeam1);
            Team team2 = teamOperations.getTeamByName(selectedTeam2);
            if (team1 != null && team2 != null) {
                Team_Match teamMatch1 = new Team_Match(team1, match);
                Team_Match teamMatch2 = new Team_Match(team2, match);
                teamMatchOperation.update(teamMatch1);
                teamMatchOperation.update(teamMatch2);
            } else {
                showAlert("error", "One or more teams not found.");
                return;
            }

            // Update the match in the database
            matchOperations.updateMatch(match);

            showAlert("info", "Match updated successfully.");

            // Clear fields after update
            datePicker.setValue(null);
            scoreField.clear();
            stadiumComboBox.setValue(null);
            refereeComboBox.setValue(null);
            team1ComboBox.setValue(null);
            team2ComboBox.setValue(null);

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
