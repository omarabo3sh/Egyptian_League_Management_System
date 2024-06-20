package com.example.egyptian_league_management_system;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        /*Load FXML file
        Parent root = FXMLLoader.load(getClass().getResource("welcomePage.fxml"));

        Scene scene = new Scene(root);*/

        FXMLLoader fxmlLoader=new FXMLLoader(Application.class.getResource("welcomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setTitle("Egyptian League Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
