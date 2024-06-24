package com.example.egyptian_league_management_system.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Refree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    String Name;

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    @OneToMany(mappedBy = "Refree" , cascade = CascadeType.ALL)
    List<Match>matches;

    public Refree(String name) {
        Name = name;
    }

    public Refree() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
