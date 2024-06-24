package com.example.egyptian_league_management_system;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class PlayerController {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vbox;

    public void initialize() {
        int numberOfPanes = 11;
        for (int i = 1; i <= numberOfPanes; i++) {
            AnchorPane playerPane = createPlayerPane("Name " + i, "Team " + i, "Age " + i, "Position " + i, "Rank " + i, "Score " + i, "Number " + i);
            vbox.getChildren().add(playerPane);
        }
        scrollPane.setContent(vbox);
    }

    private AnchorPane createPlayerPane(String name, String team, String age, String position, String rank, String score, String number) {
        AnchorPane pane = new AnchorPane();
        pane.setPrefWidth(480);
        pane.setPrefHeight(244);
        pane.setStyle("-fx-background-color: #303030;");

        Label labelName = new Label(name);
        labelName.setLayoutX(56);
        labelName.setLayoutY(36);
        labelName.setPrefWidth(112);
        labelName.setPrefHeight(60);
        labelName.setTextFill(javafx.scene.paint.Color.WHITE);
        labelName.setFont(new javafx.scene.text.Font("Agency FB Bold", 48));

        Label labelTeam = new Label("Team: " + team);
        labelTeam.setLayoutX(56);
        labelTeam.setLayoutY(106);
        labelTeam.setPrefWidth(112);
        labelTeam.setPrefHeight(42);
        labelTeam.setTextFill(javafx.scene.paint.Color.WHITE);
        labelTeam.setFont(new javafx.scene.text.Font("Agency FB", 24));

        Label labelAge = new Label("Age: " + age);
        labelAge.setLayoutX(56);
        labelAge.setLayoutY(146);
        labelAge.setPrefWidth(112);
        labelAge.setPrefHeight(42);
        labelAge.setTextFill(javafx.scene.paint.Color.WHITE);
        labelAge.setFont(new javafx.scene.text.Font("Agency FB", 24));

        Label labelPosition = new Label("Position: " + position);
        labelPosition.setLayoutX(56);
        labelPosition.setLayoutY(188);
        labelPosition.setPrefWidth(133);
        labelPosition.setPrefHeight(42);
        labelPosition.setTextFill(javafx.scene.paint.Color.WHITE);
        labelPosition.setFont(new javafx.scene.text.Font("Agency FB", 24));

        Label labelRank = new Label("Rank: " + rank);
        labelRank.setLayoutX(281);
        labelRank.setLayoutY(110);
        labelRank.setTextFill(javafx.scene.paint.Color.WHITE);
        labelRank.setFont(new javafx.scene.text.Font("Agency FB", 24));

        Label labelScore = new Label("Score: " + score);
        labelScore.setLayoutX(281);
        labelScore.setLayoutY(150);
        labelScore.setPrefWidth(56);
        labelScore.setPrefHeight(31);
        labelScore.setTextFill(javafx.scene.paint.Color.WHITE);
        labelScore.setFont(new javafx.scene.text.Font("Agency FB", 24));

        Label labelNumber = new Label("Number: " + number);
        labelNumber.setLayoutX(281);
        labelNumber.setLayoutY(192);
        labelNumber.setTextFill(javafx.scene.paint.Color.WHITE);
        labelNumber.setFont(new javafx.scene.text.Font("Agency FB", 24));

        pane.getChildren().addAll(labelName, labelTeam, labelAge, labelPosition, labelRank, labelScore, labelNumber);

        return pane;
    }

    public void onBackClick(ActionEvent event) throws IOException {
        Application.switchScene(event, "team.fxml");
    }
}
