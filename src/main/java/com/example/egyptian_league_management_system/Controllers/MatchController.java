package com.example.egyptian_league_management_system.Controllers;

import com.example.egyptian_league_management_system.Entities.Match;
import com.example.egyptian_league_management_system.Entities.Refree;
import com.example.egyptian_league_management_system.Entities.Stadium;
import com.example.egyptian_league_management_system.Entities.Team;
import com.example.egyptian_league_management_system.Operations.MatchOperations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

import static com.example.egyptian_league_management_system.Application.switchScene;

public class MatchController {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vbox;

    private final MatchOperations matchOperations = new MatchOperations();

    public void initialize() {
        List<Match> matches = matchOperations.getAllMatches();
        vbox.getChildren().clear();



        for (Match match : matches) {
            try {
                Match completeMatch = matchOperations.getCompleteMatchDetails(match);

                List<Team> teams = completeMatch.getTeams();
                List<Refree> referees = completeMatch.getRefrees();
                Stadium stadium = completeMatch.getStadium();

                if (teams.size() == 2 && !referees.isEmpty() && stadium != null) {
                    Team team1 = teams.get(0);
                    Team team2 = teams.get(1);
                    AnchorPane matchPane = loadMatchPane(match, team1, team2, referees, stadium);
                    vbox.getChildren().add(matchPane);

                }
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        scrollPane.setContent(vbox);
    }

    private AnchorPane loadMatchPane(Match match, Team team1, Team team2, List<Refree> referees, Stadium stadium) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/egyptian_league_management_system/MatchPane.fxml"));
        AnchorPane pane = loader.load();

        MatchPaneController controller = loader.getController();
        String refereeNames = getRefereeNames(referees);
        controller.setMatchDetails(team1.getName(), team2.getName(),
                refereeNames, String.valueOf(match.getScore()), stadium.getName(), match.getDate());

        return pane;
    }

    private String getRefereeNames(List<Refree> referees) {
        StringBuilder names = new StringBuilder();
        for (Refree referee : referees) {
            if (!names.isEmpty()) {
                names.append(", ");
            }
            names.append(referee.getName());
        }
        return names.toString();
    }

    public void onBackClick(ActionEvent event) throws IOException {
        switchScene(event, "matchesManagement.fxml");
    }
}
