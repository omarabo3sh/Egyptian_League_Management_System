package com.example.egyptian_league_management_system.Controllers;

import java.io.IOException;

import com.example.egyptian_league_management_system.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import static com.example.egyptian_league_management_system.Application.switchScene;


public class WelcomePageController {

    @FXML

        public void startBtnClick(ActionEvent event) throws IOException {
    switchScene(event,"Choose.fxml");
        }


}

