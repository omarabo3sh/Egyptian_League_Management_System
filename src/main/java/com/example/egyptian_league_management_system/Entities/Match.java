package com.example.egyptian_league_management_system.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int Id;
    String Date;
    int Score;
    boolean ishelded ;
    @OneToMany(mappedBy = "Match" , cascade = CascadeType.ALL)
    List<Player>players;
    @OneToMany(mappedBy = "Match" ,cascade = CascadeType.ALL)
    List<Team>teams;

    @ManyToOne
     @JoinColumn(name = "stadiumId" , referencedColumnName = "id")
    Stadium stadium;
    @OneToMany(mappedBy = "Match" , cascade = CascadeType.ALL)
    List<Refree>refrees;
    public Match(String date, int score) {
        Date = date;
        Score = score;
    }

    public Match() {
    }




    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public boolean ishelded() {
        return ishelded;
    }

    public void setishelded(boolean ishelded) {
        this.ishelded = ishelded;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public List<Refree> getRefrees() {
        return refrees;
    }

    public void setRefrees(List<Refree> refrees) {
        this.refrees = refrees;
    }
}
