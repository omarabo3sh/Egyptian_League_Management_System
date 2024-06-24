package com.example.egyptian_league_management_system;


import com.example.egyptian_league_management_system.Database.DatabaseManager ;
import com.example.egyptian_league_management_system.Entities.*;
import com.example.egyptian_league_management_system.Operations.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//      TeamOperations teamOperations = new TeamOperations();
//      List<Team> teams ;
//      teams = teamOperations.getAll();
//      for (Team team : teams){
//          System.out.print(team.getName());
//          System.out.print(team.getId());
//      }

//        List<Team>teams;
//        TeamOperations teamOperations = new TeamOperations();
//        teams = teamOperations.getTeamsSorted();
//        for (Team team : teams){
//            System.out.println(team.getTotalScore());
//        }
        PlayerOperations playerOperations = new PlayerOperations();
        MatchOperations matchOperations = new MatchOperations();
        Player_MatchOperation player_matchOperation = new Player_MatchOperation();
        Player player =  playerOperations.getPlayerByName("Lamin yamal");
        Match match = matchOperations.getMatchById(1);
        Player_Match playerMatch = new Player_Match(player , match);
        player_matchOperation.insert(playerMatch);


    }


}