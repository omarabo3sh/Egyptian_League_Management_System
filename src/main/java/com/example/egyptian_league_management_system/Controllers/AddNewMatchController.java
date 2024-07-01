package com.example.egyptian_league_management_system.Controllers;

import com.example.egyptian_league_management_system.Entities.*;
import com.example.egyptian_league_management_system.Operations.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static com.example.egyptian_league_management_system.Application.switchScene;

public class AddNewMatchController {

    @FXML private DatePicker datePicker;
    @FXML private ComboBox<String> team1NameComboBox;
    @FXML private ComboBox<String> team2NameComboBox;
    @FXML private ComboBox<String> refereeComboBox;
    @FXML private ComboBox<String> stadiumComboBox;
    @FXML private TextField scoreField;

    private MatchOperations matchOperations = new MatchOperations();
    private TeamOperations teamOperations = new TeamOperations();
    private RefreeOperations refereeOperations = new RefreeOperations();
    private StadiumOperations stadiumOperations = new StadiumOperations();

    @FXML
    public void initialize() {
        try {
            List<Team> teams = teamOperations.getAll();
            ObservableList<String> teamNames = FXCollections.observableArrayList();
            for (Team team : teams) {
                teamNames.add(team.getName());
            }
            team1NameComboBox.setItems(teamNames);
            team2NameComboBox.setItems(teamNames);


            List<Refree> referees = refereeOperations.getAllReferees();
            ObservableList<String> refereeNames = FXCollections.observableArrayList();
            for (Refree referee : referees) {
                refereeNames.add(referee.getName());
            }
            refereeComboBox.setItems(refereeNames);


            List<Stadium> stadiums = stadiumOperations.getAllStadiums();
            ObservableList<String> stadiumNames = FXCollections.observableArrayList();
            for (Stadium stadium : stadiums) {
                stadiumNames.add(stadium.getName());
            }
            stadiumComboBox.setItems(stadiumNames);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onAddMatchClick() {
        try {
            // Retrieve input values from UI components
            LocalDate date = datePicker.getValue();
            String team1Name = team1NameComboBox.getValue();
            String team2Name = team2NameComboBox.getValue();
            String refereeName = refereeComboBox.getValue();
            String stadiumName = stadiumComboBox.getValue();
            int score = Integer.parseInt(scoreField.getText());

            // Validate inputs
            if (date == null || team1Name == null || team2Name == null ||
                    refereeName == null || stadiumName == null || scoreField.getText().isEmpty()) {
                System.out.println("Please fill in all fields.");
                return;
            }

            // Create a new match object
            Match match = new Match();
            match.setDate(date.toString());
            match.setScore(score);
            match.setishelded(false); // Assuming the match is not yet held

            // Insert match into database and get generated ID
            int matchId = matchOperations.insertMatch(match);
            match.setId(matchId);

            // Associate teams with the match
            Team team1 = teamOperations.getTeamByName(team1Name);
            Team team2 = teamOperations.getTeamByName(team2Name);
            Team_Match teamMatch1 = new Team_Match(team1, match);
            Team_Match teamMatch2 = new Team_Match(team2, match);
            Team_MatchOperation teamMatchOperation = new Team_MatchOperation();
            teamMatchOperation.insert(teamMatch1);
            teamMatchOperation.insert(teamMatch2);

            // Associate referee with the match
            Refree referee = refereeOperations.getRefreeByName(refereeName); // Assuming you have a method to fetch referee by name
            Refree_Match refereeMatch = new Refree_Match(referee, match);
            Refree_MatchOperations refereeMatchOperations = new Refree_MatchOperations();
            refereeMatchOperations.insert(refereeMatch);

            // Associate stadium with the match
            Stadium stadium = stadiumOperations.getStadiumByName(stadiumName); // Assuming you have a method to fetch stadium by name
            match.setStadium(stadium);
            matchOperations.updateMatch(match);

            // Close the window or handle navigation
            Stage stage = (Stage) datePicker.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number for score.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onBackClick(ActionEvent event) throws IOException {
        switchScene(event, "matchesManagement.fxml");
    }
}
