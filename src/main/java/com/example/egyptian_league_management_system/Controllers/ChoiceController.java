package com.example.egyptian_league_management_system.Controllers;

import java.io.IOException;
import java.time.LocalDate;

import com.example.egyptian_league_management_system.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;


public class ChoiceController {

    public AnchorPane matchAnchorPane;
    @FXML
private DatePicker datePicker;

    public void onTeamsClick(ActionEvent event) throws IOException {
        Application.switchScene(event,"team.fxml");
    }

    public void onMatchesClick(ActionEvent event) throws IOException {
        Application.switchScene(event,"match.fxml");
    }

    public void onBackClick(ActionEvent event) throws IOException {
        Application.switchScene(event,"welcomePage.fxml");
    }
    @FXML
    public void handleDateSelection(ActionEvent event)throws IOException {
        matchAnchorPane.setVisible(true);
            LocalDate selectedDate = datePicker.getValue();
            //if (selectedDate != null && selectedDate.equals(class.matchDate))
        //add date var from their class

        }
    public void onAddNewClick(ActionEvent event) throws IOException {
        Application.switchScene(event,"insert.fxml");

    }

}
