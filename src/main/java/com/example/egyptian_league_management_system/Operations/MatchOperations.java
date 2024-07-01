//package com.example.egyptian_league_management_system.Operations;
//
//
//import com.example.egyptian_league_management_system.Database.DatabaseManager ;
//import com.example.egyptian_league_management_system.Entities.Match;
//import com.example.egyptian_league_management_system.Entities.Refree;
//import com.example.egyptian_league_management_system.Entities.Stadium;
//import com.example.egyptian_league_management_system.Entities.Team;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MatchOperations {
//    private DatabaseManager databaseManager = new DatabaseManager();
//    public MatchOperations(){
//        databaseManager.startConnection();
//    }

package com.example.egyptian_league_management_system.Operations;

import com.example.egyptian_league_management_system.Database.DatabaseManager;
import com.example.egyptian_league_management_system.Entities.Match;
import com.example.egyptian_league_management_system.Entities.Refree;
import com.example.egyptian_league_management_system.Entities.Stadium;
import com.example.egyptian_league_management_system.Entities.Team;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatchOperations {
    private final DatabaseManager databaseManager = new DatabaseManager();

    public MatchOperations() {
        databaseManager.startConnection();
    }

    public void insertMatch(Match match) {
        String query = "INSERT INTO `match` (Date, Score, helded) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, match.getDate());
            preparedStatement.setInt(2, match.getScore());
            preparedStatement.setBoolean(3, match.isIshelded());
            preparedStatement.executeUpdate();
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
                    match.setIshelded(resultSet.getBoolean("helded"));
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
                    match.setIshelded(resultSet.getBoolean("helded"));
                    matches.add(match);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return matches;
    }

    public void updateMatch(Match match) {
        String query = "UPDATE `match` SET Date = ?, Score = ?, helded = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, match.getDate());
            preparedStatement.setInt(2, match.getScore());
            preparedStatement.setBoolean(3, match.isIshelded());
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
        String query = "SELECT * FROM stadium INNER JOIN `match` ON stadium.id = `match`.stadiumId WHERE `match`.id = ?";
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
                    match.setIshelded(resultSet.getBoolean("helded"));
                    matches.add(match);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return matches;
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
    public Match getMatchRefree(Match match){
        String query = "select * from refree_match where refree_match.match_id = ?";
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

}
