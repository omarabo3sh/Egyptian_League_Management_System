package com.example.egyptian_league_management_system;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

public class PlayerController {

    public AnchorPane anchorPane;

    public void onBackClick(ActionEvent event) throws IOException {
        Application.switchScene(event,"team.fxml");
    }


}
