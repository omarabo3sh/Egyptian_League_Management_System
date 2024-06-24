package com.example.egyptian_league_management_system;

import javafx.event.ActionEvent;

import java.io.IOException;

public class InsertController {



    public void onBackClick(ActionEvent event) throws IOException {
        Application.switchScene(event, "choice.fxml");
    }
}
