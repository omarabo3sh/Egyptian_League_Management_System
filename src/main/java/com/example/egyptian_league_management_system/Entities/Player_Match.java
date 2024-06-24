package com.example.egyptian_league_management_system.Entities;

import javax.persistence.*;

@Entity
public class Player_Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    Player player;

    @ManyToOne
    @JoinColumn(name = "match_id")
    Match match;

    // No-argument constructor
    public Player_Match() {
        // This constructor is intentionally empty.
    }

    // Parameterized constructor
    public Player_Match(Player player, Match match) {
        this.player = player;
        this.match = match;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
