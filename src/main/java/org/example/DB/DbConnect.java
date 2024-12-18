package org.example.DB;

import java.sql.Connection;

public class ConnectDB {
    private String db;
    private String port;
    private String user;
    private String password;
    private String jdbc;
    private Connection connection;

    public ConnectDB() {
        this.db = "MANEMPSA";
        this.port = "3306";
        this.user = "root";
        this.password = "";
        this.jdbc = "jdbc:mysql://localhost:" + port + "/"+db;
        this.connection = null;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJdbc() {
        return jdbc;
    }

    public void setJdbc(String jdbc) {
        this.jdbc = jdbc;
    }
}
