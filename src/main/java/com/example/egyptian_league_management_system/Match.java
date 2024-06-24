package com.example.egyptian_league_management_system;

import java.time.LocalDate;

public class Match {
    private int id;
    private LocalDate date;
    private Team team1;
    private Team team2;
    private String footballReferee;
    private String score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public String getFootballReferee() {
        return footballReferee;
    }

    public void setFootballReferee(String footballReferee) {
        this.footballReferee = footballReferee;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    private String stadiumName;
}
