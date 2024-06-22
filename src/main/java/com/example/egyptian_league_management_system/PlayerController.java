package com.example.egyptian_league_management_system;
import java.io.IOException;
import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class PlayerController {

    public void onBackClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("team.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
/*
    private Button btnShowPlayers;

    private PlayerService playerService;

    public TeamController() {
        playerService = new PlayerService();
    }

    @FXML
    public void initialize() {
        btnShowPlayers.setOnAction(event -> {
            List<Player> players = playerService.getAllPlayers();
            displayPlayerDialogs(players);
        });
    }

    // The displayPlayerDialogs method from above
    public void displayPlayerDialogs(List<Player> players) {
        for (Player player : players) {
            Platform.runLater(() -> {
                Dialog<Void> dialog = new Dialog<>();
                dialog.setTitle("Player Information");
                DialogPane dialogPane = createPlayerDialogPane(player);
                dialog.setDialogPane(dialogPane);
                dialog.showAndWait();
            });
        }
    }

    // The createPlayerDialogPane method from above
    public DialogPane createPlayerDialogPane(Player player) {
        DialogPane dialogPane = new DialogPane();

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Name:"), 0, 0);
        grid.add(new Label(player.getName()), 1, 0);

        grid.add(new Label("Number:"), 0, 1);
        grid.add(new Label(String.valueOf(player.getNumber())), 1, 1);

        grid.add(new Label("Team:"), 0, 2);
        grid.add(new Label(player.getTeam().getName()), 1, 2);

        grid.add(new Label("Age:"), 0, 3);
        grid.add(new Label(String.valueOf(player.getAge())), 1, 3);

        grid.add(new Label("Score:"), 0, 4);
        grid.add(new Label(String.valueOf(player.getScore())), 1, 4);

        grid.add(new Label("Rank:"), 0, 5);
        grid.add(new Label(String.valueOf(player.getRank())), 1, 5);

        grid.add(new Label("Role:"), 0, 6);
        grid.add(new Label(player.getRole()), 1, 6);

        dialogPane.setContent(grid);
        dialogPane.getButtonTypes().addAll(ButtonType.OK);

        return dialogPane;
    }
*/

}
