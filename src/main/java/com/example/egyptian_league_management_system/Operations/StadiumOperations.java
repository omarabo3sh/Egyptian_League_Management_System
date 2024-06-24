package com.example.egyptian_league_management_system.Operations;



import com.example.egyptian_league_management_system.Database.DatabaseManager ;
import com.example.egyptian_league_management_system.Entities.Stadium ;


import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StadiumOperations {
    private DatabaseManager databaseManager = new DatabaseManager();

    public StadiumOperations() {
    }

    public void insertStadium(Stadium stadium){
        String query = "insert into stadium (name) values (?)";
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setString(1 , stadium.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
