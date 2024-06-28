package com.example.egyptian_league_management_system.Operations;


import com.example.egyptian_league_management_system.Database.DatabaseManager ;

import com.example.egyptian_league_management_system.Entities.Match;
import com.example.egyptian_league_management_system.Entities.Team;
import com.example.egyptian_league_management_system.Entities.Team_Match;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Team_MatchOperation {
    private DatabaseManager databaseManager = new DatabaseManager();

    public Team_MatchOperation() {
        databaseManager.startConnection();
    }

    public void insert(Team_Match teamMatch){
        String query = "insert into team_match (team_idd , match_idd) values (? ,?)";
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(query);
            preparedStatement.setInt(1 , teamMatch.getTeam().getId());
            preparedStatement.setInt(2 , teamMatch.getMatch().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Team_Match> getAll(){
        String query = "select * from team_match";
        TeamOperations teamOperations = new TeamOperations();
        MatchOperations matchOperations = new MatchOperations();
        List<Team_Match> teamMatches = new ArrayList<>();
        try {
            Statement statement = databaseManager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Team_Match teamMatch = new Team_Match();
                Team team= teamOperations.getTeamById(resultSet.getInt("team_idd"));
                Match match = matchOperations.getMatchById(resultSet.getInt("match_idd"));
                teamMatch.setTeam(team);
                teamMatch.setMatch(match);
                teamMatches.add(teamMatch);
            }
            return teamMatches;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
