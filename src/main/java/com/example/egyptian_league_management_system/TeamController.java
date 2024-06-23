package com.example.egyptian_league_management_system;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.image.ImageView;

public class TeamController {



    @FXML
    private ImageView team1ImageView;
    @FXML
    private ImageView team2ImageView;





    public void initialize() {


      //  Image team1Image = new Image(Objects.requireNonNull(getClass().getResource("ahly.jpg")).toString());
       // Image team2Image = new Image(Objects.requireNonNull(getClass().getResource("zamalek.png")).toString());

        //team1ImageView.setImage(team1Image);
        //team2ImageView.setImage(team2Image);



    }

    public void onBackClick(ActionEvent event) throws IOException {
        Application.switchScene(event,"choice.fxml");
    }

    public void onTeamClick(ActionEvent event) throws IOException {
        Application.switchScene(event,"player.fxml");
    }


}
