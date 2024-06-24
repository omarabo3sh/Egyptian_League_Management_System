package com.example.egyptian_league_management_system.Entities;
import javax.persistence.*;
import java.util.List;

@Entity
public class Stadium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int Id;
    String Name;
    @OneToMany(mappedBy = "Stadium", cascade = CascadeType.ALL)
    List<Match>matches;

    public Stadium(String name) {
        Name = name;
    }

    public Stadium() {
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

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
