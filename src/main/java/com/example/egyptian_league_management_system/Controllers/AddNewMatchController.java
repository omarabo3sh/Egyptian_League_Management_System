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

    private final MatchOperations matchOperations = new MatchOperations();
    private final TeamOperations teamOperations = new TeamOperations();
    private final RefreeOperations refereeOperations = new RefreeOperations();
    private final StadiumOperations stadiumOperations = new StadiumOperations();

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

            LocalDate date = datePicker.getValue();
            String team1Name = team1NameComboBox.getValue();
            String team2Name = team2NameComboBox.getValue();
            String refereeName = refereeComboBox.getValue();
            String stadiumName = stadiumComboBox.getValue();
            int score = Integer.parseInt(scoreField.getText());


            if (date == null || team1Name == null || team2Name == null ||
                    refereeName == null || stadiumName == null || scoreField.getText().isEmpty()) {
                System.out.println("Please fill in all fields.");
                return;
            }


            Match match = new Match();
            match.setDate(date.toString());
            match.setScore(score);
            match.setishelded(false);


            int matchId = matchOperations.insertMatch(match);
            match.setId(matchId);


            Team team1 = teamOperations.getTeamByName(team1Name);
            Team team2 = teamOperations.getTeamByName(team2Name);
            Team_Match teamMatch1 = new Team_Match(team1, match);
            Team_Match teamMatch2 = new Team_Match(team2, match);
            Team_MatchOperation teamMatchOperation = new Team_MatchOperation();
            teamMatchOperation.insert(teamMatch1);
            teamMatchOperation.insert(teamMatch2);

            Refree referee = refereeOperations.getRefreeByName(refereeName);
            Refree_Match refereeMatch = new Refree_Match(referee, match);
            Refree_MatchOperations refereeMatchOperations = new Refree_MatchOperations();
            refereeMatchOperations.insert(refereeMatch);


            Stadium stadium = stadiumOperations.getStadiumByName(stadiumName);
            match.setStadium(stadium);
            matchOperations.updateMatch(match);



            datePicker.setValue(null);
            team1NameComboBox.getSelectionModel().clearSelection();
            team2NameComboBox.getSelectionModel().clearSelection();
            refereeComboBox.getSelectionModel().clearSelection();
            stadiumComboBox.getSelectionModel().clearSelection();
            scoreField.clear();
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
