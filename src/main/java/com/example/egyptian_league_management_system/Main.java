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
        TeamOperations teamOperations = new TeamOperations();
        Team team =teamOperations.getTeamById(2);
       team = teamOperations.getTeamPlayers(team);

       for (Player player : team.getPlayers()){
           System.out.println(player.getName());
       }


    }

}