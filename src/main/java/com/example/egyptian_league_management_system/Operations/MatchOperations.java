package com.example.egyptian_league_management_system.Operations;


import com.example.egyptian_league_management_system.Database.DatabaseManager ;
import com.example.egyptian_league_management_system.Entities.Match;
import com.example.egyptian_league_management_system.Entities.Refree;
import com.example.egyptian_league_management_system.Entities.Stadium;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatchOperations {
    private DatabaseManager databaseManager = new DatabaseManager();
    public MatchOperations(){
        databaseManager.startConnection();
    }

    public void insertMatch(Match match){
        String query = "insert into `match` (Date , Score , helded) values (? , ? , ?)";
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setString(1 , match.getDate());
            preparedStatement.setInt(2 , match.getScore());
            preparedStatement.setBoolean(3 , match.isIshelded());
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
               match.setIshelded(resultSet.getBoolean("helded"));
            }
            return match;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Match> getMatchByDate(String date){
        String query = "select * from `match` where Date  = ?";
        List<Match> matches = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setString(1 , date);
            System.out.println("Executing query: " + preparedStatement.toString()); // Debugging line
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Match match = new Match() ;
                match.setId(resultSet.getInt("id"));
                match.setDate(resultSet.getString("Date"));
                match.setScore(resultSet.getInt("Score"));
                match.setIshelded(resultSet.getBoolean("helded"));
                matches.add(match);
            }
            return matches;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateMatch(Match match){
        String query = "update `match` set Date = ? , Score = ? , helded =? where id = ? ";
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setString(1,match.getDate());
            preparedStatement.setInt(2 , match.getScore());
            preparedStatement.setBoolean(3 , match.isIshelded());
            preparedStatement.setInt(4 , match.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Match getMatchRefree(Match match){
        String query = "select * from refree_match where refree_match.match_iddd = ?";
        List<Refree> refrees = new ArrayList<>();
        RefreeOperations refreeOperations = new RefreeOperations();
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setInt(1 , match.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Refree refree = refreeOperations.getRefreeById(resultSet.getInt("refree_id"));
                refrees.add(refree);
            }
            match.setRefrees(refrees);

            return match;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Match getMatchStadium(Match match){
       String query = "select * from stadium inner join `match` on stadium.id = `match`.stadiumId where `match`.id = ?";
        try {
            PreparedStatement preparedStatement =  databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setInt(1 , match.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                StadiumOperations stadiumOperations = new StadiumOperations();
                Stadium stadium = stadiumOperations.getStadiumById(resultSet.getInt("id"));
                match.setStadium(stadium);
                return match;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
