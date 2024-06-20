package com.example.egyptian_league_management_system;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ChooseController {

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void onTeamClick(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("match.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    public void onMatchClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("team.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onBackClick(ActionEvent event)  throws IOException {
        root = FXMLLoader.load(getClass().getResource("welcomePage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
