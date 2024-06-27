package com.example.egyptian_league_management_system.Controllers;

import com.example.egyptian_league_management_system.Entities.Player;
import com.example.egyptian_league_management_system.Entities.Team;
import com.example.egyptian_league_management_system.Operations.PlayerOperations;
import com.example.egyptian_league_management_system.Operations.TeamOperations;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.List;

import static com.example.egyptian_league_management_system.Application.switchScene;

public class AdditionalManagementController {
    public Label infoLabel;
    private TeamOperations teamOperations = new TeamOperations();
    private PlayerOperations playerOperations = new PlayerOperations();

    public void onBackClick(ActionEvent event) throws IOException {
        switchScene(event, "choose.fxml");
    }

    public void onTeamsByPointsClick(ActionEvent event) {
        List<Team> teams = teamOperations.getTeamsSorted();

        if (teams.isEmpty()) {
            infoLabel.setText("No teams found.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Teams sorted by total points:  ");
            for (Team team : teams) {
                sb.append("Team: ").append(team.getName()).append(", Total Points: ").append(team.getTotalScore()).append("\n");
            }
            infoLabel.setText(sb.toString());
        }
    }

    public void onTeamsByAvgAgesClick(ActionEvent event) {
        List<Team> teams = teamOperations.getTeamAverageAgeSorted();

        if (teams.isEmpty()) {
            infoLabel.setText("No teams found.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Teams sorted by average age:  ");
            for (Team team : teams) {
                sb.append("Team: ").append(team.getName()).append(", Average Age: ").append(teamOperations.calculateAverageAge(team)).append("\n");
            }
            infoLabel.setText(sb.toString());
        }
    }

    public void onTeamsByGoalsClick(ActionEvent event) {
        List<Team> teams = teamOperations.getTeamsGoalSorted();

        if (teams.isEmpty()) {
            infoLabel.setText("No teams found.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Teams sorted by total goals:  ");
            for (Team team : teams) {
                sb.append("Team: ").append(team.getName()).append(", Total Goals: ").append(teamOperations.getTeamTotalScores(team)).append("\n");
            }
            infoLabel.setText(sb.toString());
        }
    }

    public void onTopPlayersByGoalsClick(ActionEvent event) {
        List<Player> topPlayers = playerOperations.getTopScoredPlayers();
        StringBuilder sb = new StringBuilder();
        sb.append("Top Scored Players:  ");
        for (Player player : topPlayers) {
            sb.append(player.getName()).append(" - Score: ").append(player.getScore()).append("\n");
        }
        infoLabel.setText(sb.toString());
    }

    public void onTopPlayersByRankClick(ActionEvent event) {
        List<Player> topRankedPlayers = playerOperations.getTopRankedPlayers();

        StringBuilder sb = new StringBuilder();
        sb.append("Top Ranked Players:  ");
        for (Player player : topRankedPlayers) {
            sb.append(player.getName()).append(" - Rank: ").append(player.getRank()).append("\n");
        }
        infoLabel.setText(sb.toString());
    }

    public void onTopGKByCleanSheetClick(ActionEvent event) {
        List<Player> fewestScoredGKs = playerOperations.getFewestScoredGKs();

        StringBuilder sb = new StringBuilder();
        sb.append("Top Clean Sheets Goalkeepers:  ");
        for (Player player : fewestScoredGKs) {
            sb.append("Name: ").append(player.getName()).append(", ")
                    .append("Position: ").append(player.getPosition()).append(", ")
                    .append("Score: ").append(player.getScore()).append("\n");
        }
        infoLabel.setText(sb.toString());
    }

    public void onMatchOnCertainDateClick(ActionEvent event) {
        // Handle the event
    }
}
