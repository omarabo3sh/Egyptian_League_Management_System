package com.example.egyptian_league_management_system.Operations;

import org.example.Database.DatabaseManager;
import org.example.Entities.Refree_Match;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Refree_MatchOperations {
    private DatabaseManager databaseManager = new DatabaseManager();

    public Refree_MatchOperations() {
        databaseManager.startConnection();
    }

    public void insert(Refree_Match refreeMatch){
        String query = "insert into refree_match (refree_id , match_iddd) values (?,?)";
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setInt(1 , refreeMatch.getRefree().getId());
            preparedStatement.setInt(2 , refreeMatch.getMatch().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
