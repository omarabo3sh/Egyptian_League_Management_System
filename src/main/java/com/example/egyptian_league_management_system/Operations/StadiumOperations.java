package com.example.egyptian_league_management_system.Operations;

import com.example.egyptian_league_management_system.Database.DatabaseManager;
import com.example.egyptian_league_management_system.Entities.Stadium;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            if (resultSet.next()) {
                Stadium stadium = new Stadium();
                stadium.setId(resultSet.getInt("id"));
                stadium.setName(resultSet.getString("name"));
                return stadium;
            }
            return null; // return null if stadium with given name not found
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
