package test;

import com.example.egyptian_league_management_system.Application;
import javafx.event.ActionEvent;

import java.io.IOException;

public class AddNewTeam {
    public void onBackClick(ActionEvent event) throws IOException, IOException {
        Application.switchScene(event, "teamsManagement.fxml");
    }
}
