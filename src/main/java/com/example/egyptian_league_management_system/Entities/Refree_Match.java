package com.example.egyptian_league_management_system.Entities;

import javax.persistence.*;

@Entity
public class Refree_Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;
    @ManyToOne
     @JoinColumn(name = "refree_id" , referencedColumnName = "id")
    Refree refree;
    @ManyToOne
    @JoinColumn(name = "match_iddd" , referencedColumnName = "id")
    Match match;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Refree getRefree() {
        return refree;
    }

    public void setRefree(Refree refree) {
        this.refree = refree;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
