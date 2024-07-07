package com.example.egyptian_league_management_system.Operations;

import com.example.egyptian_league_management_system.Database.DatabaseManager;
import com.example.egyptian_league_management_system.Entities.Match;
import com.example.egyptian_league_management_system.Entities.Refree;
import com.example.egyptian_league_management_system.Entities.Stadium;
import com.example.egyptian_league_management_system.Entities.Team;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchOperations {
    private final DatabaseManager databaseManager = new DatabaseManager();
    private Connection connection;
    public MatchOperations() {
        databaseManager.startConnection();
    }

    public int insertMatch(Match match) {
    String query = "INSERT INTO `match` (Date, Score, ishelded) VALUES (?, ?, ?)";
    try (PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
        preparedStatement.setString(1, match.getDate());
        preparedStatement.setInt(2, match.getScore());
        preparedStatement.setBoolean(3, match.ishelded());
        preparedStatement.executeUpdate();

        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        } else {
            throw new SQLException("Creating match failed, no ID obtained.");
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}

    public Match getMatchById(int id) {
    String query = "SELECT * FROM `match` WHERE id = ?";
    Match match = null;
    try (PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query)) {
        preparedStatement.setInt(1, id);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                match = new Match();
                match.setId(resultSet.getInt("id"));
                match.setDate(resultSet.getString("Date"));
                match.setScore(resultSet.getInt("Score"));
                match.setishelded(resultSet.getBoolean("ishelded"));
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return match;
}

    public List<Match> getMatchesByDate(String date) {
        String query = "SELECT * FROM `match` WHERE Date = ?";
        List<Match> matches = new ArrayList<>();
        try (PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, date);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Match match = new Match();
                    match.setId(resultSet.getInt("id"));
                    match.setDate(resultSet.getString("Date"));
                    match.setScore(resultSet.getInt("Score"));
                    match.setishelded(resultSet.getBoolean("ishelded"));
                    matches.add(match);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return matches;
    }

    public void updateMatch(Match match) {
        String query = "UPDATE `match` SET Date = ?, Score = ?, ishelded = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, match.getDate());
            preparedStatement.setInt(2, match.getScore());
            preparedStatement.setBoolean(3, match.ishelded());
            preparedStatement.setInt(4, match.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Match getCompleteMatchDetails(Match match) {
        match = getMatchTeams(match);
        match = getMatchReferee(match);
        match = getMatchStadium(match);
        return match;
    }

    private Match getMatchTeams(Match match) {
        String query = "SELECT * FROM team_match WHERE match_id = ?";
        try (PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, match.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Team> teams = new ArrayList<>();
                TeamOperations teamOperations = new TeamOperations();
                while (resultSet.next()) {
                    Team team = teamOperations.getTeamById(resultSet.getInt("team_id"));
                    teams.add(team);
                }
                match.setTeams(teams);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return match;
    }

    private Match getMatchReferee(Match match) {
        String query = "SELECT * FROM refree_match WHERE match_id = ?";
        try (PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, match.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Refree> referees = new ArrayList<>();
                RefreeOperations refereeOperations = new RefreeOperations();
                while (resultSet.next()) {
                    Refree referee = refereeOperations.getRefereeById(resultSet.getInt("refree_id"));
                    referees.add(referee);
                }
                match.setRefrees(referees);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return match;
    }

    private Match getMatchStadium(Match match) {
        String query = "SELECT * FROM stadium INNER JOIN `match` ON stadium.id = `match`.stadium_Id WHERE `match`.id = ?";
        try (PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, match.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    StadiumOperations stadiumOperations = new StadiumOperations();
                    Stadium stadium = stadiumOperations.getStadiumById(resultSet.getInt("stadium.id"));
                    match.setStadium(stadium);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return match;
    }

    public List<Match> getAllMatches() {
        String query = "SELECT * FROM `match`";
        List<Match> matches = new ArrayList<>();
        try (PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Match match = new Match();
                    match.setId(resultSet.getInt("id"));
                    match.setDate(resultSet.getString("Date"));
                    match.setScore(resultSet.getInt("Score"));
                    match.setishelded(resultSet.getBoolean("ishelded"));
                    matches.add(match);
                }
            }

            System.out.println("Number of matches retrieved: " + matches.size());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return matches;
    }

    public Match getMatchByTeams(String teamName1, String teamName2) throws SQLException {
        Match match = null;
        String query = "SELECT m.id, m.date, m.score, m.is_held, s.name AS stadium_name " +
                "FROM matches m " +
                "JOIN stadiums s ON m.stadium_id = s.id " +
                "JOIN team_matches tm1 ON m.id = tm1.match_id " +
                "JOIN teams t1 ON tm1.team_id = t1.id " +
                "JOIN team_matches tm2 ON m.id = tm2.match_id " +
                "JOIN teams t2 ON tm2.team_id = t2.id " +
                "WHERE t1.name = ? AND t2.name = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, teamName1);
            statement.setString(2, teamName2);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    match = new Match();
                    match.setId(resultSet.getInt("id"));
                    match.setDate(resultSet.getString("date"));
                    match.setScore(resultSet.getInt("score"));
                    match.setishelded(resultSet.getBoolean("is_held"));

                    match.setStadium(new Stadium(resultSet.getString("stadium_name")));
                }
            }
        }

        return match;
    }

}
