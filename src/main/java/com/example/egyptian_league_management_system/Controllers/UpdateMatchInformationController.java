package com.example.egyptian_league_management_system.Controllers;

import com.example.egyptian_league_management_system.Entities.*;
import com.example.egyptian_league_management_system.Operations.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static com.example.egyptian_league_management_system.Application.showAlert;
import static com.example.egyptian_league_management_system.Application.switchScene;

public class UpdateMatchInformationController {

    @FXML
    private TextField matchIdTextField;
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
    @FXML
    private CheckBox heldCheckBox;

    private final MatchOperations matchOperations = new MatchOperations();
    private final TeamOperations teamOperations = new TeamOperations();
    private final RefreeOperations refereeOperations = new RefreeOperations();
    private final StadiumOperations stadiumOperations = new StadiumOperations();

    @FXML
    public void initialize() {
        loadComboBoxes();
    }

    private void loadComboBoxes() {
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
    private void onUpdateMatchClick() {
        try {
            int matchId = Integer.parseInt(matchIdTextField.getText().trim());

            Match updatedMatch = matchOperations.getMatchById(matchId);
            if (updatedMatch == null) {
                System.out.println("Match not found in the database.");
                return;
            }

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

            updatedMatch.setDate(date.toString());
            updatedMatch.setScore(score);
            updatedMatch.setishelded(heldCheckBox.isSelected());

            Stadium stadium = stadiumOperations.getStadiumByName(stadiumName);
            updatedMatch.setStadium(stadium);

            matchOperations.updateMatch(updatedMatch);

            if (!team1Name.equals(team2Name)) {
                Team team1 = teamOperations.getTeamByName(team1Name);
                Team team2 = teamOperations.getTeamByName(team2Name);
                Team_MatchOperation teamMatchOperation = new Team_MatchOperation();
                teamMatchOperation.update(new Team_Match(team1, updatedMatch));
                teamMatchOperation.update(new Team_Match(team2, updatedMatch));
            } else {
                System.out.println("Please select different teams.");
            }

            Refree referee = refereeOperations.getRefreeByName(refereeName);
            Refree_Match refereeMatch = new Refree_Match(referee, updatedMatch);
            Refree_MatchOperations refereeMatchOperations = new Refree_MatchOperations();
            refereeMatchOperations.update(refereeMatch);

            matchIdTextField.clear();
            datePicker.setValue(null);
            team1NameComboBox.getSelectionModel().clearSelection();
            team2NameComboBox.getSelectionModel().clearSelection();
            refereeComboBox.getSelectionModel().clearSelection();
            stadiumComboBox.getSelectionModel().clearSelection();
            scoreField.clear();
            heldCheckBox.setSelected(false);


            showAlert("Success", "Match updated successfully.");
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid data.");
        }
    }

    @FXML
    public void onBackClick(ActionEvent event) throws IOException {
        switchScene(event, "matchesManagement.fxml");
    }
}
