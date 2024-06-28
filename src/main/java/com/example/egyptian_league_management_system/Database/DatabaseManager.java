package com.example.egyptian_league_management_system.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//public class DatabaseManager {
//    private static final String URL = "jdbc:mysql://localhost:3306/egyptian_league_management_system";
//    private static final String USER = "root";
//    private static final String PASSWORD = "12345";
//
//    private static Connection connection;
//
//    public void startConnection() {
//        try {
//            connection = DriverManager.getConnection(URL, USER, PASSWORD);
//            System.out.println("Connected to database!");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static Connection getConnection() {
//        return connection;
//    }
//
//    public void closeConnection() {
//        if (connection != null) {
//            try {
//                connection.close();
//                System.out.println("Database connection closed.");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}

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
