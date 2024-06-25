package com.example.egyptian_league_management_system;

import java.io.IOException;
import com.example.egyptian_league_management_system.Entities.Player;
import com.example.egyptian_league_management_system.Entities.Team;
import com.example.egyptian_league_management_system.Operations.TeamOperations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class PlayerController {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vbox;

    private String teamName;

    public void setTeamName(String teamName) {
        this.teamName = teamName;
        initialize();
    }

    public void initialize() {
        if (teamName == null) {
            return;
        }
        TeamOperations teamOperations = new TeamOperations();
        Team team = teamOperations.getTeamByName(teamName);
        team = teamOperations.getTeamPlayers(team);
        for (Player player : team.getPlayers()) {
            try {
                AnchorPane playerPane = loadPlayerPane(player);
                vbox.getChildren().add(playerPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        scrollPane.setContent(vbox);
    }

    private AnchorPane loadPlayerPane(Player player) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerPane.fxml"));
        AnchorPane pane = loader.load();

        Label labelName = (Label) pane.lookup("#labelName");
        Label labelTeam = (Label) pane.lookup("#labelTeam");
        Label labelAge = (Label) pane.lookup("#labelAge");
        Label labelPosition = (Label) pane.lookup("#labelPosition");
        Label labelRank = (Label) pane.lookup("#labelRank");
        Label labelScore = (Label) pane.lookup("#labelScore");
        Label labelNumber = (Label) pane.lookup("#labelNumber");

        labelName.setText(player.getName());
        labelTeam.setText("Team: " + (player.getTeam() != null ? player.getTeam().getName() : "Unknown"));
        labelAge.setText("Age: " + player.getAge());
        labelPosition.setText("Position: " + player.getPosition());
        labelRank.setText("Rank: " + player.getRank());
        labelScore.setText("Score: " + player.getScore());
        labelNumber.setText("Number: " + player.getNumber());

        return pane;
    }

    public void onBackClick(ActionEvent event) throws IOException {
        Application.switchScene(event, "choice.fxml");
    }
}
