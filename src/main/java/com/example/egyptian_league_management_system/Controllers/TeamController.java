package com.example.egyptian_league_management_system.Controllers;

import java.io.IOException;
import java.util.Objects;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import static com.example.egyptian_league_management_system.Application.switchScene;


public class TeamController {

    @FXML
    public ImageView team1ImageView;
    public ImageView team2ImageView;
    public ImageView team3ImageView;
    public ImageView team4ImageView;
    public ImageView team5ImageView;

    public void initialize() {
        loadImage(team1ImageView, "/AlAhly.png");
        loadImage(team2ImageView, "/ELZamalek.png");
        loadImage(team3ImageView, "/ELIsmaily.png");
        loadImage(team4ImageView, "/ELMasry.png");
        loadImage(team5ImageView, "/ELMokawloon.png");

    }

    public void onBackClick(ActionEvent event) throws IOException {
      switchScene(event, "choose.fxml");
    }

    public void onImage1Click(MouseEvent mouseEvent) throws IOException {
    switchScene(mouseEvent, "player.fxml", "AlAhly");
    }

    public void onImage2Click(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "player.fxml", "ELZamalek");
    }

    public void onImage3Click(MouseEvent mouseEvent) throws IOException {
      switchScene(mouseEvent, "player.fxml", "ELIsmaily");
    }

    public void onImage4Click(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "player.fxml", "ELMasry");
    }

    public void onImage5Click(MouseEvent mouseEvent) throws IOException {
        switchScene(mouseEvent, "player.fxml", "ELMokawloon");
    }



    private void loadImage(ImageView imageView, String imageName) {
        Image image = new Image(Objects.requireNonNull(getClass().getResource(imageName)).toString());
        imageView.setImage(image);
    }
}
