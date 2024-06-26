package com.example.egyptian_league_management_system.Entities;

import javax.persistence.*;

@Entity
public class Team_Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
     @JoinColumn(name = "team_id" , referencedColumnName = "id")
    Team team;
    @ManyToOne
    @JoinColumn(name = "match_id" , referencedColumnName = "id")
    Match match;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
