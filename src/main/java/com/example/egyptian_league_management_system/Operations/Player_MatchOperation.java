package com.example.egyptian_league_management_system.Operations;



import com.example.egyptian_league_management_system.Database.DatabaseManager ;
import com.example.egyptian_league_management_system.Entities.Player;
import com.example.egyptian_league_management_system.Entities.Player_Match;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.example.egyptian_league_management_system.Operations.PlayerOperations.getAllPlayers;

public class Player_MatchOperation {
    private DatabaseManager databaseManager = new DatabaseManager();
    public Player_MatchOperation(){
        databaseManager.startConnection();
    }

    public void insert(Player_Match playerMatch){
        String query = "insert into player_match (player_id , match_id) values (? , ?)";
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setInt(1 , playerMatch.getPlayer().getId());
            preparedStatement.setInt(2 , playerMatch.getMatch().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
