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
    public ImageView team1ImageView;
    public ImageView team2ImageView;
    public ImageView team3ImageView;
    public ImageView team4ImageView;
    public ImageView team5ImageView;
    public ImageView team6ImageView;
    public ImageView team7ImageView;
    public ImageView team8ImageView;

    public void initialize() {
        loadImage(team1ImageView, "/AlAhly.png");
        loadImage(team2ImageView, "/ALZamalek.png");
        loadImage(team3ImageView, "/ALZamalek.png");
        loadImage(team4ImageView, "/ALZamalek.png");
        loadImage(team5ImageView, "/ALZamalek.png");
        loadImage(team6ImageView, "/ALZamalek.png");
        loadImage(team7ImageView, "/ALZamalek.png");
        loadImage(team8ImageView, "/ALZamalek.png");
    }

    public void onBackClick(ActionEvent event) throws IOException {
        Application.switchScene(event, "choice.fxml");
    }

    public void onImage1Click(MouseEvent mouseEvent) throws IOException {
        Application.switchSceneWithParams(mouseEvent, "player.fxml", "Barcelona");
    }

    public void onImage2Click(MouseEvent mouseEvent) throws IOException {
        Application.switchSceneWithParams(mouseEvent, "player.fxml", "Real Madrid");
    }

    public void onImage3Click(MouseEvent mouseEvent) throws IOException {
        Application.switchSceneWithParams(mouseEvent, "player.fxml", "");
    }

    public void onImage4Click(MouseEvent mouseEvent) throws IOException {
        Application.switchSceneWithParams(mouseEvent, "player.fxml", "");
    }

    public void onImage5Click(MouseEvent mouseEvent) throws IOException {
        Application.switchSceneWithParams(mouseEvent, "player.fxml", "");
    }

    public void onImage6Click(MouseEvent mouseEvent) throws IOException {
        Application.switchSceneWithParams(mouseEvent, "player.fxml", "");
    }

    public void onImage7Click(MouseEvent mouseEvent) throws IOException {
        Application.switchSceneWithParams(mouseEvent, "player.fxml", "");
    }

    public void onImage8Click(MouseEvent mouseEvent) throws IOException {
        Application.switchSceneWithParams(mouseEvent, "player.fxml", "");
    }

    private void loadImage(ImageView imageView, String imageName) {
        Image image = new Image(Objects.requireNonNull(getClass().getResource(imageName)).toString());
        imageView.setImage(image);
    }
}
