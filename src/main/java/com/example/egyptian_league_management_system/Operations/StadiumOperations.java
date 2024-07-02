package com.example.egyptian_league_management_system.Operations;

import com.example.egyptian_league_management_system.Database.DatabaseManager;
import com.example.egyptian_league_management_system.Entities.Stadium;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StadiumOperations {
    private DatabaseManager databaseManager = new DatabaseManager();

    public StadiumOperations() {
    }

    public void insertStadium(Stadium stadium) {
        String query = "insert into stadium (name) values (?)";
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setString(1, stadium.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Stadium getStadiumByName(String name) {
        String query = "select * from stadium where name = ?";
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            Stadium stadium = new Stadium();
           if (resultSet.next()) {

                stadium.setId(resultSet.getInt("id"));
                stadium.setName(resultSet.getString("name"));
                return stadium;

            }
           return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Stadium getStadiumById(int stadiumId) {
        String query = "SELECT * FROM stadium WHERE id = ?";
        Stadium stadium = null;
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, stadiumId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                stadium = new Stadium();
                stadium.setId(resultSet.getInt("id"));
                stadium.setName(resultSet.getString("name"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stadium;
    }
    public List<Stadium> getAllStadiums() {
        List<Stadium> stadiums = new ArrayList<>();
        String query = "SELECT * FROM stadium";
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Stadium stadium = new Stadium();
                stadium.setId(resultSet.getInt("id"));
                stadium.setName(resultSet.getString("name"));

                stadiums.add(stadium);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stadiums;
    }
}
