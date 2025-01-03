package org.example.controlles;

import org.example.config.DbConnect;

import java.sql.*;

/**
 * Clase que controla las operaciones de conexión y consultas a la base de datos.
 * Facilita la conexión, desconexión y ejecución de consultas SQL.
 */
public class DbController {

    /**
     * Objeto de configuración para la conexión a la base de datos.
     */
    private final DbConnect dbConnect =  new DbConnect();

    /**
     * Objeto {@link Connection} que representa la conexión activa con la base de datos.
     */
    private Connection connection = dbConnect.getConnection();

    /**
     * Establece una conexión con la base de datos utilizando los parámetros configurados en {@link DbConnect}.
     *
     * @return Objeto {@link Connection} que representa la conexión activa.
     */
    public Connection connect() {
        try{
            connection = DriverManager.getConnection(dbConnect.getJdbc(), dbConnect.getUser(), dbConnect.getPassword());
            System.out.println("Connected to the database successfully");
        }catch (SQLException e){
            System.out.println("Error connecting to the database "+e.getMessage());
        }
        return connection;
    }

    /**
     * Obtiene la conexión activa con la base de datos. Si no existe, establece una nueva conexión.
     *
     * @return Objeto {@link Connection} que representa la conexión activa.
     */
    public Connection getConnection() {
        try{
            connection = DriverManager.getConnection(dbConnect.getJdbc(), dbConnect.getUser(), dbConnect.getPassword());
        }catch (SQLException e){
            System.out.println("Error connecting to the database "+e.getMessage());
        }
        return connection;
    }

    /**
     * Establece manualmente una conexión específica para este controlador.
     *
     * @param connection Objeto {@link Connection} que se usará como conexión activa.
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Cierra la conexión activa con la base de datos.
     */
    public void disconnect() {
        try{
            connection.close();
            System.out.println("Connection closed");
        }catch (SQLException e){
            System.out.println("Error disconnecting from the database "+e.getMessage());
        }
    }

    /**
     * Ejecuta una consulta SQL de tipo `SELECT` y devuelve los resultados.
     *
     * @param sql Consulta SQL a ejecutar.
     * @return Objeto {@link ResultSet} que contiene los resultados de la consulta.
     */
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

    /**
     * Ejecuta una consulta SQL de tipo `INSERT`, `UPDATE` o `DELETE`.
     *
     * @param sql Consulta SQL a ejecutar.
     * @return Número de filas afectadas por la consulta.
     */
    public int addInfoDataBase(String sql){

        int n = 0;
        try{
            n =  connection.createStatement().executeUpdate(sql);
            System.out.println("Executed " + sql + " statements");
        }catch (SQLException ex){
            System.out.println("Error creating tables " + ex.getMessage()+"\n"+sql);
        }

        return n;
    }

}
