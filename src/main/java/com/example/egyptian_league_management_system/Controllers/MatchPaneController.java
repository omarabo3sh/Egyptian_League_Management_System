package com.example.egyptian_league_management_system.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class MatchPaneController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label labelTeam1;

    @FXML
    private Label labelTeam2;

    @FXML
    private Label labelReferee;

    @FXML
    private Label labelScore;

    @FXML
    private Label labelStadium;

    @FXML
    private Label labelDate;

    public void setMatchDetails(String team1, String team2, String referee, String score, String stadium, String date) {
        labelTeam1.setText(team1);
        labelTeam2.setText(team2);
        labelReferee.setText("Referee: " + referee);
        labelScore.setText("Score: " + score);
        labelStadium.setText("Stadium: " + stadium);
        labelDate.setText(date);
    }
}
