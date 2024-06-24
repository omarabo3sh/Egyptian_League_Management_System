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

public class TeamOperations {
    private DatabaseManager databaseManager = new DatabaseManager();
    public TeamOperations(){
        databaseManager.startConnection();
    }

    public void insertTeam(Team team){
        String query = "insert into team (Name , Captain , TotalScore) values (? , ? , ?)";
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setString(1 , team.getName());
            preparedStatement.setString(2,team.getCaptainName());
            preparedStatement.setInt(3 , team.getTotalScore());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Team getTeamByName(String name){
        String query = "select * from team where Name = ?";
        Team team = new Team();
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setString(1 , name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                team.setName(resultSet.getString("Name"));
                team.setCaptainName(resultSet.getString("Captain"));
                team.setTotalScore(resultSet.getInt("TotalScore"));
                team.setId(resultSet.getInt("id"));
            }
            return team;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Team getTeamById(int id){
        String query = "Select * from Team where id = ?";
        Team team = new Team();
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setInt(1 , id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                team.setId(resultSet.getInt("id"));
                team.setName(resultSet.getString("Name"));
                team.setCaptainName(resultSet.getString("Captain"));
                team.setTotalScore(resultSet.getInt("TotalScore"));
            }
            return team;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTeam(Team team){
        String query = "update team set Name =? , Captain =? , TotalScore = ? where id = ?";
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setString(1 , team.getName());
            preparedStatement.setString(2 , team.getCaptainName());
            preparedStatement.setInt( 3, team.getTotalScore());
            preparedStatement.setInt(4 , team.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Team getTeamPlayers(Team team){
        String query = "select *  from player inner join team on player.team_id = team.id where team.id = ?";
        List<Player> players = new ArrayList<>();
        Player player = new Player();
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setInt(1 , team.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            for (int i=0 ; resultSet.next() ; i++){
                player.setId(resultSet.getInt("id"));
                player.setNumber(resultSet.getString("Number"));
                player.setName(resultSet.getString("Name"));
                player.setPosition(resultSet.getString("Position"));
                player.setAge(resultSet.getInt("Age"));
                player.setRank(resultSet.getInt("Rank"));
                player.setSore(resultSet.getInt("Score"));
                players.add(player);
            }
            team.setPlayers(players);
            return team;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Team> getAll(){
        String query = "select * from team";
        List<Team>teams = new ArrayList<>();

        try {
            Statement statement = databaseManager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Team team = new Team();
                team.setId(resultSet.getInt("id"));
                team.setName(resultSet.getString("Name"));
                team.setCaptainName(resultSet.getString("Captain"));
                team.setTotalScore(resultSet.getInt("TotalScore"));
                team.setPlayers(getTeamPlayers(team).getPlayers());
                teams.add(team);
            }
            return teams;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Team> getTeamsSorted(){
        List<Team> teams = getAll();
        Collections.sort(teams, new Comparator<Team>() {
            @Override
            public int compare(Team o1, Team o2) {
                return Integer.compare(o2.getTotalScore() , o1.getTotalScore());
            }
        });
        return teams;
    }

}