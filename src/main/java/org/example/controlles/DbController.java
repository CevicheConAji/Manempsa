package org.example.controlles;

import org.example.DB.DbConnect;

import java.sql.*;

public class DbController {
    private final DbConnect dbConnect =  new DbConnect();
    private Connection connection = dbConnect.getConnection();

    public Connection connect() {
        try{
            connection = DriverManager.getConnection(dbConnect.getJdbc(), dbConnect.getUser(), dbConnect.getPassword());
            System.out.println("Connected to the database successfully");
        }catch (SQLException e){
            System.out.println("Error connecting to the database "+e.getMessage());
        }
        return connection;
    }

    public Connection getConnection() {
        try{
            connection = DriverManager.getConnection(dbConnect.getJdbc(), dbConnect.getUser(), dbConnect.getPassword());
        }catch (SQLException e){
            System.out.println("Error connecting to the database "+e.getMessage());
        }
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void disconnect() {
        try{
            connection.close();
            System.out.println("Connection closed");
        }catch (SQLException e){
            System.out.println("Error disconnecting from the database "+e.getMessage());
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
