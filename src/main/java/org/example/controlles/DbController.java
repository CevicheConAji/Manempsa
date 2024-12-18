package org.example.controlles;

import org.example.DB.ConnectDB;

import java.sql.*;

public class ControllerDB {
    private final ConnectDB connectDB =  new ConnectDB();
    private Connection connection = connectDB.getConnection();

    public void connect() {
        try{
            connection = DriverManager.getConnection(connectDB.getJdbc(),connectDB.getUser(),connectDB.getPassword());
            System.out.println("Connected to the database successfully");
        }catch (SQLException e){
            System.out.println("Error connecting to the database "+e.getMessage());
        }
    }
    public void disconnect() {
        try{
            connection.close();
            System.out.println("Connection closed");
        }catch (SQLException e){
            System.out.println("Error disconnecting from the database "+e.getMessage());
        }
    }
    public void createUpdate(String sql){
        int rowsAffected = 0;
        try{
            rowsAffected = connection.createStatement().executeUpdate(sql);
            System.out.println(sql);
        }catch (SQLException e){
            System.out.println("Error creating the database "+e.getMessage());
        }

    }
    public ResultSet createQuery(String sql){
        Statement statement;
        ResultSet resultSet = null;
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            System.out.println("Query created successfully " + sql);
        }catch (SQLException e){
            System.out.println("Error creating the database "+e.getMessage());
        }
        return resultSet;
    }
}
