package com.example.egyptian_league_management_system.Operations;

import org.example.Database.DatabaseManager;
import org.example.Entities.Player;
import org.example.Entities.Team;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PlayerOperations {
    private DatabaseManager databaseManager = new DatabaseManager();
    public PlayerOperations(){
        databaseManager.startConnection();
    }

    public void insertPlayer(Player player){
        String query = "insert into player (Name , Number , Position , Age , Score , `Rank` , team_id) values" +
                "(? ,? ,? ,? ,? ,? ,?)";
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setString(1 , player.getName());
            preparedStatement.setString(2 , player.getNumber());
            preparedStatement.setString(3 , player.getPosition());
            preparedStatement.setInt(4 , player.getAge());
            preparedStatement.setInt(5 , player.getSore());
            preparedStatement.setInt(6 , player.getRank());
            preparedStatement.setInt(7 , player.getTeam().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Player getPlayerByName(String name){
        String query = "select * from player where name  = ?";
        Player player = new Player();
        Team team;
        TeamOperations teamOperations = new TeamOperations();
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                player.setName(resultSet.getString("Name"));
                player.setNumber(resultSet.getString("Number"));
                player.setId(resultSet.getInt("id"));
                player.setAge(resultSet.getInt("Age"));
                player.setRank(resultSet.getInt("Rank"));
                player.setPosition(resultSet.getString("Position"));
                player.setSore(resultSet.getInt("Score"));
                team = teamOperations.getTeamById(resultSet.getInt("team_id"));
                player.setTeam(team);
            }

            return player;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updatePlayer(Player player){
        String query = "update player set Name = ? , Number = ? , Position = ? , Age = ? , Score = ? , Rank =? where id = ?";
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setString(1,player.getName());
            preparedStatement.setString(2 , player.getNumber());
            preparedStatement.setString(3 , player.getPosition());
            preparedStatement.setInt(4 , player.getAge());
            preparedStatement.setInt(5 , player.getSore());
            preparedStatement.setInt(6 , player.getRank());
            preparedStatement.setInt(7 , player.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Player> getAllPlayers(){
        String query = "select * from player";
        List<Player> players = new ArrayList<>();
        try {
            Statement statement = databaseManager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Player player = new Player();
                player.setId(resultSet.getInt("id"));
                player.setName(resultSet.getString("Name"));
                player.setSore(resultSet.getInt("Score"));
                player.setAge(resultSet.getInt("Age"));
                player.setRank(resultSet.getInt("Rank"));
                player.setPosition(resultSet.getString("Position"));
                player.setNumber(resultSet.getString("Number"));
                players.add(player);
            }
            return players;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Player> getPlayersSorted(){
        List<Player>players = getAllPlayers();
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return Integer.compare(o1.getSore() , o2.getSore());
            }
        });
        return players;
    }

    public List<Player> getTopScoredPlayers(){
        List<Player>players = getPlayersSorted();
        List<Player> topPlayers = new ArrayList<>();
        topPlayers.add(players.get(0));
        topPlayers.add(players.get(1));
        topPlayers.add(players.get(2));
        return topPlayers;
    }


}
