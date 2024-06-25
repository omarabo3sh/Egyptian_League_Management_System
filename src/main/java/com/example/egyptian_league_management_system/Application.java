package com.example.egyptian_league_management_system;


import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;


import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Objects;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("welcomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setTitle("Egyptian League Management System");
        stage.setScene(scene);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("Styles.css")).toExternalForm());

        stage.show();
    }
    public static void switchScene(Event event, String fxmlFile) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource(fxmlFile)));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void switchSceneWithParams(Event event, String fxmlFile, String teamName) throws IOException {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource(fxmlFile));
        Parent root = loader.load();

        PlayerController controller = loader.getController();
        controller.setTeamName(teamName);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
