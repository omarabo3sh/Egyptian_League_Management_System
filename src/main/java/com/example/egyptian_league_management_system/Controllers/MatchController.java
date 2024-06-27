package com.example.egyptian_league_management_system.Controllers;

import java.io.IOException;

import com.example.egyptian_league_management_system.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.geometry.Pos;

import static com.example.egyptian_league_management_system.Application.switchScene;

public class MatchController {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vbox;

    public void initialize() {
        int numberOfPanes = 5;

        vbox.setPrefHeight(numberOfPanes * 254);
        vbox.setAlignment(Pos.CENTER);

        for (int i = 1; i <= numberOfPanes; i++) {
            AnchorPane matchAnchorPane = createMatchAnchorPane("Team1", "Team2", "Referee",
                    "Score", "Stadium", "Date");
            matchAnchorPane.setPrefWidth(505);
            matchAnchorPane.setPrefHeight(244);
            vbox.getChildren().add(matchAnchorPane);
        }

        scrollPane.setContent(vbox);
    }

    private AnchorPane createMatchAnchorPane(String team1, String team2, String referee, String score,
                                             String stadium, String date) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefWidth(505);
        anchorPane.setPrefHeight(244);
        anchorPane.setStyle("-fx-background-color: #303030;");

        Label labelTeam1 = new Label(team1);
        labelTeam1.setLayoutX(56);
        labelTeam1.setLayoutY(36);
        labelTeam1.setPrefWidth(112);
        labelTeam1.setPrefHeight(60);
        labelTeam1.setTextFill(javafx.scene.paint.Color.WHITE);
        labelTeam1.setFont(new javafx.scene.text.Font("Agency FB Bold", 48));

        Label labelReferee = new Label("Referee: " + referee);
        labelReferee.setLayoutX(56);
        labelReferee.setLayoutY(149);
        labelReferee.setPrefWidth(112);
        labelReferee.setPrefHeight(42);
        labelReferee.setTextFill(javafx.scene.paint.Color.WHITE);
        labelReferee.setFont(new javafx.scene.text.Font("Agency FB", 24));

        Label labelScore = new Label("Score: " + score);
        labelScore.setLayoutX(56);
        labelScore.setLayoutY(107);
        labelScore.setPrefWidth(112);
        labelScore.setPrefHeight(42);
        labelScore.setTextFill(javafx.scene.paint.Color.WHITE);
        labelScore.setFont(new javafx.scene.text.Font("Agency FB", 24));

        Label labelStadium = new Label("Stadium: " + stadium);
        labelStadium.setLayoutX(186);
        labelStadium.setLayoutY(191);
        labelStadium.setPrefWidth(133);
        labelStadium.setPrefHeight(42);
        labelStadium.setTextFill(javafx.scene.paint.Color.WHITE);
        labelStadium.setFont(new javafx.scene.text.Font("Agency FB", 24));

        Label labelTeam2 = new Label(team2);
        labelTeam2.setLayoutX(345);
        labelTeam2.setLayoutY(37);
        labelTeam2.setPrefWidth(112);
        labelTeam2.setPrefHeight(42);
        labelTeam2.setTextFill(javafx.scene.paint.Color.WHITE);
        labelTeam2.setFont(new javafx.scene.text.Font("Agency FB Bold", 48));

        Label labelDate = new Label(date);
        labelDate.setLayoutX(221);
        labelDate.setLayoutY(56);
        labelDate.setTextFill(javafx.scene.paint.Color.WHITE);
        labelDate.setFont(new javafx.scene.text.Font("System Bold", 24));

        anchorPane.getChildren().addAll(labelTeam1, labelReferee, labelScore, labelStadium, labelTeam2, labelDate);

        return anchorPane;
    }

    public void onBackClick(ActionEvent event) throws IOException {
       switchScene(event, "Choose.fxml");
    }
}
