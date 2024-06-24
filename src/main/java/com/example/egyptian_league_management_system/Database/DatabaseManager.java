package com.example.egyptian_league_management_system.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private String url = "jdbc:mysql://127.0.0.1:3306/egyptianleague2";
    private String userName = "root";
    private String password = "Omar_Alsabahy2000";
    private Connection connection ;

    public  DatabaseManager(){
        startConnection();
    }

    public void startConnection(){
        try {
            connection = DriverManager.getConnection(url , userName , password);
            System.out.println("Connected");

        } catch (SQLException e) {
            System.out.println("Failed");
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
