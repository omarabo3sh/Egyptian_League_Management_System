package com.example.egyptian_league_management_system;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;





public class WelcomePageController {

    @FXML

        public void startBtnClick(ActionEvent event) throws IOException {
        Application.switchScene(event,"choice.fxml");
        }


}

