package test;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Objects;

public class Mainc {


    public Button teamManagementButton;

    @FXML
        private void handleTeamManagement() throws Exception {
            navigateTo("TeamManagement.fxml");
        }

        @FXML
        private void handlePlayerManagement() throws Exception {
            navigateTo("PlayerManagement.fxml");
        }

        @FXML
        private void handleMatchManagement() throws Exception {
            navigateTo("MatchManagement.fxml");
        }

        @FXML
        private void handleGeneralFunctions() throws Exception {
            navigateTo("GeneralFunctions.fxml");
        }

        private void navigateTo(String fxmlFile) throws Exception {
            Stage stage = (Stage) teamManagementButton.getScene().getWindow();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
            stage.setScene(new Scene(root));
        }
    }

