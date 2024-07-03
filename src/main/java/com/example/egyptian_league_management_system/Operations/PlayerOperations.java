package com.example.egyptian_league_management_system.Operations;

import com.example.egyptian_league_management_system.Database.DatabaseManager;
import com.example.egyptian_league_management_system.Entities.Player;
import com.example.egyptian_league_management_system.Entities.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PlayerOperations {
    private static DatabaseManager databaseManager = new DatabaseManager();

    public PlayerOperations() {
        databaseManager.startConnection();
    }

    public void insertPlayer(Player player) {
        String query = "INSERT INTO player (Name, Number, Position, Age, Score, `Rank`, team_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, player.getName());
            preparedStatement.setString(2, player.getNumber());
            preparedStatement.setString(3, player.getPosition());
            preparedStatement.setInt(4, player.getAge());
            preparedStatement.setInt(5, player.getScore());
            preparedStatement.setInt(6, player.getRank());
            preparedStatement.setInt(7, player.getTeam().getId());
            preparedStatement.executeUpdate();
            System.out.println("Player inserted successfully: " + player.getName());
        } catch (SQLException e) {
            System.err.println("Error inserting player: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Player getPlayerByName(String name) {
        String query = "SELECT * FROM player WHERE name = ?";
        Player player = new Player();
        Team team;
        TeamOperations teamOperations = new TeamOperations();
        try (PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    player.setName(resultSet.getString("Name"));
                    player.setNumber(resultSet.getString("Number"));
                    player.setId(resultSet.getInt("id"));
                    player.setAge(resultSet.getInt("Age"));
                    player.setRank(resultSet.getInt("Rank"));
                    player.setPosition(resultSet.getString("Position"));
                    player.setScore(resultSet.getInt("Score"));
                    team = teamOperations.getTeamById(resultSet.getInt("team_id"));
                    player.setTeam(team);
                } else {
                    System.err.println("Player not found: " + name);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving player: " + e.getMessage());
            e.printStackTrace();
        }
        return player;
    }

    public void updatePlayer(Player player) {
        String query = "UPDATE player SET Name = ?, Number = ?, Position = ?, Age = ?, Score = ?, `Rank` = ?, team_id = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, player.getName());
            preparedStatement.setString(2, player.getNumber());
            preparedStatement.setString(3, player.getPosition());
            preparedStatement.setInt(4, player.getAge());
            preparedStatement.setInt(5, player.getScore());
            preparedStatement.setInt(6, player.getRank());
            preparedStatement.setInt(7, player.getTeam().getId());
            preparedStatement.setInt(8, player.getId());
            preparedStatement.executeUpdate();
            System.out.println("Player updated successfully: " + player.getName());
        } catch (SQLException e) {
            System.err.println("Error updating player: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<Player> getAllPlayers() {
        String query = "SELECT * FROM player";
        List<Player> players = new ArrayList<>();
        try (Statement statement = databaseManager.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Player player = new Player();
                player.setId(resultSet.getInt("id"));
                player.setName(resultSet.getString("Name"));
                player.setScore(resultSet.getInt("Score"));
                player.setAge(resultSet.getInt("Age"));
                player.setRank(resultSet.getInt("Rank"));
                player.setPosition(resultSet.getString("Position"));
                player.setNumber(resultSet.getString("Number"));
                players.add(player);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all players: " + e.getMessage());
            e.printStackTrace();
        }
        return players;
    }

    public List<Player> getPlayersSorted() {
        List<Player> players = getAllPlayers();
        players.sort(Comparator.comparingInt(Player::getScore));
        return players;
    }

    public List<Player> getTopScoredPlayers() {
        List<Player> players = getPlayersSorted();
        Collections.reverse(players);
        return players.subList(0, Math.min(players.size(), 3));
    }

    public List<Player> getTopRankedPlayers() {
        List<Player> players = getAllPlayers();
        players.sort(Comparator.comparingInt(Player::getRank));
        Collections.reverse(players);
        return players.subList(0, Math.min(players.size(), 3));
    }

    public List<Player> getGKsByCSH() {
        List<Player> players = getAllPlayers();
        List<Player> gks = new ArrayList<>();
        for (Player player : players) {
            if ("Goalkeeper".equalsIgnoreCase(player.getPosition())) {
                gks.add(player);
            }
        }
        gks.sort(Comparator.comparingInt(Player::getScore));
        return gks;
    }

    public List<Player> getFewestScoredGKs() {
        List<Player> gks = getGKsByCSH();

        return gks.subList(0, Math.min(gks.size(), 3));
    }
}
