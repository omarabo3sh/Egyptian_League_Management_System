package com.example.egyptian_league_management_system.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




public class DatabaseManager {



    private static final String url = "jdbc:mysql://127.0.0.1:3306/egyptian_league_management_system"; //omar aboesh db
    private static final String userName = "root";  //omar aboesh db
    private static final String password = "12345";//omar aboesh db


//    private String url = "jdbc:mysql://127.0.0.1:3306/egyptianleague2";
//    private String userName = "root";
//    private String password = "Omar_Alsabahy2000";



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
