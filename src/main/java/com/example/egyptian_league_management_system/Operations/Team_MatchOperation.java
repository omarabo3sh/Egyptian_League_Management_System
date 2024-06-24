package com.example.egyptian_league_management_system.Operations;

import org.example.Database.DatabaseManager;
import org.example.Entities.Team_Match;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Team_MatchOperation {
    private DatabaseManager databaseManager = new DatabaseManager();

    public Team_MatchOperation() {
        databaseManager.startConnection();
    }

    public void insert(Team_Match teamMatch){
        String query = "insert into team_match (team_idd , match_idd) values (? ,?)";
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setInt(1 , teamMatch.getTeam().getId());
            preparedStatement.setInt(2 , teamMatch.getMatch().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
