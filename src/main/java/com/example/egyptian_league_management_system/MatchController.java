package com.example.egyptian_league_management_system;
import java.io.IOException;
import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MatchController {




    public void onBackClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("choose.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /*
    private MatchService matchService;
    public MatchController() {
        matchService = new MatchService();
    }

    @FXML
    public void initialize() {
        btnShowMatches.setOnAction(event -> {
            List<Match> matches = matchService.getAllMatches();
            displayMatchDialogs(matches);
        });
    }

    public void displayMatchDialogs(List<Match> matches) {
        for (Match match : matches) {
            Platform.runLater(() -> {
                Dialog<Void> dialog = new Dialog<>();
                dialog.setTitle("Match Information");
                DialogPane dialogPane = createMatchDialogPane(match);
                dialog.setDialogPane(dialogPane);
                dialog.showAndWait();
            });
        }
    }

    public DialogPane createMatchDialogPane(Match match) {
        DialogPane dialogPane = new DialogPane();

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Match ID:"), 0, 0);
        grid.add(new Label(String.valueOf(match.getId())), 1, 0);

        grid.add(new Label("Date:"), 0, 1);
        grid.add(new Label(match.getDate().toString()), 1, 1);

        grid.add(new Label("Team 1:"), 0, 2);
        grid.add(new Label(match.getTeam1().getName()), 1, 2);

        grid.add(new Label("Team 2:"), 0, 3);
        grid.add(new Label(match.getTeam2().getName()), 1, 3);

        grid.add(new Label("Referee:"), 0, 4);
        grid.add(new Label(match.getFootballReferee()), 1, 4);

        grid.add(new Label("Score:"), 0, 5);
        grid.add(new Label(match.getScore()), 1, 5);

        grid.add(new Label("Stadium:"), 0, 6);
        grid.add(new Label(match.getStadiumName()), 1, 6);

        dialogPane.setContent(grid);
        dialogPane.getButtonTypes().addAll(ButtonType.OK);

        return dialogPane;

    }
  */
}

