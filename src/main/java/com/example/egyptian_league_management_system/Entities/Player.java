package com.example.egyptian_league_management_system.Entities;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.List;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int Id;
    String Name;
    String Number ;
    String Position;
    int Age;
    int Sore;
    int Rank;
    @ManyToOne
    @JoinColumn(name = "team_id" , referencedColumnName = "id")

    Team team;

    @OneToMany(mappedBy = "Player" , cascade = CascadeType.ALL)
    List<Match> matches;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }


    public Player(String name, String number, String position, int age, int sore, int rank , Team team) {

        Name = name;
        Number = number;
        Position = position;
        Age = age;
        Sore = sore;
        Rank = rank;
        this.team = team;


    }

    public Player() {
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

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public int getSore() {
        return Sore;
    }

    public void setSore(int sore) {
        Sore = sore;
    }

    public int getRank() {
        return Rank;
    }

    public void setRank(int rank) {
        Rank = rank;
    }
}
