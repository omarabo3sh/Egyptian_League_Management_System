package com.example.egyptian_league_management_system;

import com.example.egyptian_league_management_system.Controllers.PlayerController;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Application extends javafx.application.Application {
    private static final String STYLESHEET_PATH = "Styles.css";

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("welcomePage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(STYLESHEET_PATH)).toExternalForm());
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/epl.jpg"))));

        stage.setResizable(false);
        stage.setTitle("Egyptian League Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void switchScene(Event event, String fxmlFile) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource(fxmlFile)));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(Application.class.getResource(STYLESHEET_PATH)).toExternalForm());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public static void switchSceneWithParams(Event event, String fxmlFile, String teamName) throws IOException {
        FXMLLoader loader = new FXMLLoader(Application.class.getResource(fxmlFile));
        Parent root = loader.load();

        PlayerController controller = loader.getController();
        controller.setTeamName(teamName);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(Application.class.getResource(STYLESHEET_PATH)).toExternalForm());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public static void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
