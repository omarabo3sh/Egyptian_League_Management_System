package com.example.egyptian_league_management_system.Operations;

import org.example.Database.DatabaseManager;
import org.example.Entities.Match;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MatchOperations {
    private DatabaseManager databaseManager = new DatabaseManager();
    public MatchOperations(){
        databaseManager.startConnection();
    }

    public void insertMatch(Match match){
        String query = "insert into `match` (Date , Score) values (? , ?)";
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setString(1 , match.getDate());
            preparedStatement.setInt(2 , match.getScore());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Match getMatchById(int id){
        String query = "select * from `match` where id = ? ";
        Match match = new Match();
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setInt(1 , id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                match.setDate(resultSet.getString("Date"));
               match.setScore(resultSet.getInt("Score"));
               match.setId(resultSet.getInt("id"));
            }
            return match;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
