package com.example.egyptian_league_management_system;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("welcomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

       // String css = Objects.requireNonNull(this.getClass().getResource("Styles.css")).toExternalForm();
        //scene.getStylesheets().add(css);

        stage.setResizable(false);
        stage.setTitle("Egyptian League Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
