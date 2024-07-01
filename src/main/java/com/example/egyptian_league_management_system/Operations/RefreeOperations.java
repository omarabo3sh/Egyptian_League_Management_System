package com.example.egyptian_league_management_system.Operations;

import com.example.egyptian_league_management_system.Database.DatabaseManager ;
import com.example.egyptian_league_management_system.Entities.Refree ;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RefreeOperations {
    private DatabaseManager databaseManager = new DatabaseManager();

    public RefreeOperations() {
        databaseManager.startConnection();
    }

    public void insertRefree(Refree refree){
        String query = "insert into refree (name) values (?)";
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setString(1 , refree.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Refree getRefreeByName(String name){
        String query = "select * from refree where name = ?";
        Refree refree = new Refree();
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setString(1 , name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                refree.setName(resultSet.getString("Name"));
                refree.setId(resultSet.getInt("id"));
            }
            return refree;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Refree getRefreeById(int id){
        String query = "select * from refree where id = ?";
        Refree refree = new Refree();
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setInt(1 , id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                refree.setName(resultSet.getString("Name"));
                refree.setId(resultSet.getInt("id"));
            }
            return refree;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
public Refree getRefereeById(int refereeId) {
    String query = "SELECT * FROM refree WHERE id = ?";
    Refree referee = null;
    try {
        PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
        preparedStatement.setInt(1, refereeId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            referee = new Refree();
            referee.setId(resultSet.getInt("id"));
            referee.setName(resultSet.getString("name"));
            // Populate other referee attributes as needed
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return referee;
}
    public List<Refree> getAllReferees() {
        List<Refree> referees = new ArrayList<>();
        String query = "SELECT * FROM refree";
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Refree referee = new Refree();
                referee.setId(resultSet.getInt("id"));
                referee.setName(resultSet.getString("name"));

                referees.add(referee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return referees;
    }

}
