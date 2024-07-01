package com.example.egyptian_league_management_system.Operations;

import com.example.egyptian_league_management_system.Database.DatabaseManager;
import com.example.egyptian_league_management_system.Entities.Team_Match;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Team_MatchOperation {
    private DatabaseManager databaseManager = new DatabaseManager();

    public Team_MatchOperation() {
        databaseManager.startConnection();
    }

    public void insert(Team_Match teamMatch) {
        String query = "INSERT INTO team_match (team_id, match_id) VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, teamMatch.getTeam().getId());
            preparedStatement.setInt(2, teamMatch.getMatch().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to update a team match relationship
    public void update(Team_Match teamMatch) {
        String query = "UPDATE team_match SET team_id = ? WHERE match_id = ?";
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, teamMatch.getTeam().getId());
            preparedStatement.setInt(2, teamMatch.getMatch().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
