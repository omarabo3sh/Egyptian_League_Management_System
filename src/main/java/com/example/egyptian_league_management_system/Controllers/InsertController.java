package com.example.egyptian_league_management_system.Controllers;

import com.example.egyptian_league_management_system.Application;
import javafx.event.ActionEvent;

import java.io.IOException;

public class InsertController {



    public void onBackClick(ActionEvent event) throws IOException {
        Application.switchScene(event, "choice.fxml");
    }

    public void onAddPlayerClick(ActionEvent event)  throws IOException {

    }

    public void onAddTeamClick(ActionEvent event) {
    }
}
