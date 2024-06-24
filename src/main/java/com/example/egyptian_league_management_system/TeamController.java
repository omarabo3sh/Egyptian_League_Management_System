package com.example.egyptian_league_management_system;

import java.io.IOException;
import java.util.Objects;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class TeamController {


    @FXML
    public ImageView team2ImageView;

    @FXML
    private ImageView team1ImageView;






    public void initialize() {

        loadImage(team1ImageView, "/AlAhly.png");
        loadImage(team2ImageView,"/ALZamalek.png");
    }


    public void onBackClick(ActionEvent event) throws IOException {
        Application.switchScene(event,"choice.fxml");
    }


    public void onImage1Click(MouseEvent mouseEvent) throws IOException {
        //PlayerController playerController = loader.getController();
        //playerController.generatePlayers();
        Application.switchScene( mouseEvent,"player.fxml");
    }

    public void onImage2Click(MouseEvent mouseEvent) throws IOException {

        Application.switchScene( mouseEvent,"player.fxml");
    }

    private void loadImage(ImageView imageView, String imageName) {

            Image image = new Image( Objects.requireNonNull(getClass().getResource(imageName)).toString());
            imageView.setImage(image);

    }

}
