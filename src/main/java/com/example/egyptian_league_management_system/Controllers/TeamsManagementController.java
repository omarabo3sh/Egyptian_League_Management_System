package com.example.egyptian_league_management_system.Controllers;

import com.example.egyptian_league_management_system.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class TeamsManagementController {

    @FXML
    private Label infoLabel;



    public void onDisplayTeamInformationClick(ActionEvent event) {
        String data="";
        infoLabel.setText(data);
    }

    public void onDisplayTeamMatchesClick(ActionEvent event) { String data="";
        infoLabel.setText(data);
    }

    public void onDisplayTeamDetailedScoresClick(ActionEvent event) { String data="";
        infoLabel.setText(data);
    }


public void onBackClick(ActionEvent event) throws IOException, IOException {
    Application.switchScene(event, "choose.fxml");
}

public void onEnterTeamInformationClick(ActionEvent event)  throws IOException {
    Application.switchScene(event,"enterTeamInformation.fxml");

}

public void onUpdateTeamInformationClick(ActionEvent event) throws IOException, IOException {
    Application.switchScene(event,"updateTeamInformation.fxml");

}}