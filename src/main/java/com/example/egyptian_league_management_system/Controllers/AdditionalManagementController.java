package com.example.egyptian_league_management_system.Controllers;

import com.example.egyptian_league_management_system.Entities.Team;
import com.example.egyptian_league_management_system.Operations.TeamOperations;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.List;

import static com.example.egyptian_league_management_system.Application.switchScene;

public class AdditionalManagementController {
    public Label infoLabel;
    private TeamOperations teamOperations = new TeamOperations();

    public void onBackClick(ActionEvent event) throws IOException {
        switchScene(event, "choose.fxml");
    }

    public void onTeamsByPointsClick(ActionEvent event) {
        // Handle the event
    }

    public void onMatchOnCertainDateClick(ActionEvent event) {
        // Handle the event
    }

    public void onTeamsByAvgAgesClick() {
        List<Team> teams = teamOperations.getTeamAverageAgeSorted();

        if (teams.isEmpty()) {
            infoLabel.setText("No teams found.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Teams sorted by average age:\n");
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
            sb.append("Teams sorted by total goals:\n");
            for (Team team : teams) {
                sb.append("Team: ").append(team.getName()).append(", Total Goals: ").append(teamOperations.getTeamTotalScores(team)).append("\n");
            }
            infoLabel.setText(sb.toString());
        }
    }


    public void onTopGKByCleanSheetClick(ActionEvent event) {
        // Handle the event
    }

    public void onTopPlayersBygoalsClick(ActionEvent event) {
        // Handle the event
    }

    public void onTopPlayersByRankClick(ActionEvent event) {
        // Handle the event
    }
}
