package com.example.egyptian_league_management_system.Entities;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int Id;
    String Name;
    String CaptainName;
    int TotalScore;
    @OneToMany(mappedBy = "team" , cascade = CascadeType.ALL)
    List<Player> players;

    @OneToMany(mappedBy = "team" , cascade = CascadeType.ALL)
    List<Match>matches;
    public Team( String name, String captainName, int totalScore) {

        Name = name;
        CaptainName = captainName;
        TotalScore = totalScore;
    }
    public Team(){

    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCaptainName() {
        return CaptainName;
    }

    public void setCaptainName(String captainName) {
        CaptainName = captainName;
    }

    public int getTotalScore() {
        return TotalScore;
    }

    public void setTotalScore(int totalScore) {
        TotalScore = totalScore;
    }


}
