package com.example.egyptian_league_management_system;

import java.io.IOException;
import javafx.event.ActionEvent;


public class ChoiceController {




    public void onTeamsClick(ActionEvent event) throws IOException {
        Application.switchScene(event,"team.fxml");
    }

    public void onMatchesClick(ActionEvent event) throws IOException {
        Application.switchScene(event,"match.fxml");
    }

    public void onBackClick(ActionEvent event) throws IOException {
        Application.switchScene(event,"welcomePage.fxml");
    }
}
