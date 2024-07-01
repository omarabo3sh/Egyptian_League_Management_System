package com.example.egyptian_league_management_system.Operations;

import com.example.egyptian_league_management_system.Database.DatabaseManager;
import com.example.egyptian_league_management_system.Entities.Refree;
import com.example.egyptian_league_management_system.Entities.Refree_Match;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Refree_MatchOperations {
    private DatabaseManager databaseManager = new DatabaseManager();

    public Refree_MatchOperations() {
        databaseManager.startConnection();
    }

    // Method to fetch a referee by name
    public Refree getRefreeByName(String name) {
        String query = "SELECT * FROM refree WHERE name = ?";
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Refree refree = new Refree();
                refree.setId(resultSet.getInt("id"));
                refree.setName(resultSet.getString("name"));
                return refree;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // Method to insert a referee match relationship
    public void insert(Refree_Match refereeMatch) {
        String query = "INSERT INTO refree_match (match_id, refree_id) VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, refereeMatch.getMatch().getId());
            preparedStatement.setInt(2, refereeMatch.getRefree().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to update a referee match relationship
    public void update(Refree_Match refereeMatch) {
        String query = "UPDATE refree_match SET refree_id = ? WHERE match_id = ?";
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, refereeMatch.getRefree().getId());
            preparedStatement.setInt(2, refereeMatch.getMatch().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
