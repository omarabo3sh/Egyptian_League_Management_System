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

public class UpdateMatchInformationController {

    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<String> team1NameComboBox;
    @FXML
    private ComboBox<String> team2NameComboBox;
    @FXML
    private ComboBox<String> refereeComboBox;
    @FXML
    private ComboBox<String> stadiumComboBox;
    @FXML
    private TextField scoreField;

    private MatchOperations matchOperations = new MatchOperations();
    private TeamOperations teamOperations = new TeamOperations();
    private RefreeOperations refereeOperations = new RefreeOperations();
    private StadiumOperations stadiumOperations = new StadiumOperations();

    private Match match;

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
    public void onBackClick(ActionEvent event) throws IOException {
        switchScene(event, "matchesManagement.fxml");
    }




    public void setMatch(Match match) {
        this.match = match;

        // Initialize UI with match data if match is not null
        if (match != null) {
            datePicker.setValue(LocalDate.parse(match.getDate()));
            scoreField.setText(String.valueOf(match.getScore()));

            // Set team names in ComboBoxes
            if (team1NameComboBox.getItems().contains(match.getTeams().get(0).getName())) {
                team1NameComboBox.setValue(match.getTeams().get(0).getName());
            }
            if (team2NameComboBox.getItems().contains(match.getTeams().get(1).getName())) {
                team2NameComboBox.setValue(match.getTeams().get(1).getName());
            }

            // Set referee and stadium names
            if (refereeComboBox.getItems().contains(match.getRefrees().get(0).getName())) {
                refereeComboBox.setValue(match.getRefrees().get(0).getName());
            }
            if (stadiumComboBox.getItems().contains(match.getStadium().getName())) {
                stadiumComboBox.setValue(match.getStadium().getName());
            }
        } else {
            System.out.println("Match object is null. Cannot populate fields.");
        }
    }

    @FXML
    private void onUpdateMatchClick() {
        try {
            // Ensure match is not null before updating
            if (match == null) {
                System.out.println("Match object is null. Cannot update.");
                return;
            }

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

            // Update the match object
            match.setDate(date.toString());
            match.setScore(score);

            // Get and set the stadium
            Stadium stadium = stadiumOperations.getStadiumByName(stadiumName);
            match.setStadium(stadium);

            // Update match in the database
            matchOperations.updateMatch(match);

            // Update team associations if teams are different
            if (!team1Name.equals(team2Name)) {
                Team team1 = teamOperations.getTeamByName(team1Name);
                Team team2 = teamOperations.getTeamByName(team2Name);
                Team_MatchOperation teamMatchOperation = new Team_MatchOperation();
                teamMatchOperation.update(new Team_Match(team1, match));
                teamMatchOperation.update(new Team_Match(team2, match));
            } else {
                System.out.println("Please select different teams.");
            }

            // Update referee association
            Refree referee = refereeOperations.getRefreeByName(refereeName);
            Refree_Match refereeMatch = new Refree_Match(referee, match);
            Refree_MatchOperations refereeMatchOperations = new Refree_MatchOperations();
            refereeMatchOperations.update(refereeMatch);

            // Close the window or handle navigation
            Stage stage = (Stage) datePicker.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number for score.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
