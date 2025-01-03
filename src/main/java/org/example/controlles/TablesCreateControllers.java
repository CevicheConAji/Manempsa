package org.example.controlles;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Clase que controla la creación de tablas en la base de datos.
 * Proporciona métodos para crear tablas específicas y realizar alteraciones.
 */
public class TablesCreateControllers {

    /**
     * Controlador de base de datos utilizado para manejar la conexión.
     */
    private DbController dbController = new DbController();

    /**
     * Conexión activa a la base de datos.
     */
    private Connection connection = dbController.getConnection();

    /**
     * Constructor de la clase TablesCreateControllers.
     *
     * @param dbController Controlador de base de datos proporcionado para interactuar con la base de datos.
     */
    public TablesCreateControllers(DbController dbController) {
        this.dbController = dbController;
    }

    /**
     * Crea la tabla `Clientes` en la base de datos si no existe.
     *
     * @return Número de filas afectadas por la operación.
     */
    public int createTablesClientes() {
        String sql = "CREATE TABLE if not exists clientes\n" +
                "(CIF varchar(15) primary key,\n" +
                "nombre varchar(100),\n" +
                "direccion varchar(100),\n" +
                "tfno1 varchar(15),\n" +
                "tfno2 varchar(15)\n" +
                ")\n";
        int n = 0;
        try{
            n =  connection.createStatement().executeUpdate(sql);
            System.out.println("Executed " + sql + " statements");
            return n;

        }catch (SQLException e){
            System.out.println("Error creating tables clientes " + e.getMessage());
        }
        return n;
    }

    /**
     * Crea la tabla `Trabajadores` en la base de datos si no existe.
     *
     * @return Número de filas afectadas por la operación.
     */
    public int createTableTrabajadores(){

        String sql = "CREATE TABLE IF NOT EXISTS Trabajadores (\n" +
                "    DNI VARCHAR(15) PRIMARY KEY,\n" +
                "    Nombre VARCHAR(50) NOT NULL,\n" +
                "    Apellidos VARCHAR(50) NOT NULL,\n" +
                "    Sueldo DOUBLE NOT NULL,\n" +
                "    Fecha DATE NOT NULL,\n" +
                "    Matricula VARCHAR(10)\n" +
                ")\n";
        int n = 0;
        try{
            n =  connection.createStatement().executeUpdate(sql);
            System.out.println("Executed " + sql + " statements");
        }catch (SQLException ex){
            System.out.println("Error creating tables trabajadores " + ex.getMessage());
        }
        return n;
    }

    /**
     * Crea la tabla `Coches` en la base de datos si no existe.
     *
     * @return Número de filas afectadas por la operación.
     */
    public int createTableCoches(){
        String sql = "CREATE TABLE IF NOT EXISTS Coches (\n" +
                "    Matricula VARCHAR(10) PRIMARY KEY,\n" +
                "    Marca VARCHAR(50) NOT NULL,\n" +
                "    Modelo VARCHAR(50) NOT NULL,\n" +
                "    Anio INT NOT NULL,\n" +
                "    DNI VARCHAR(15),\n" +
                "    FOREIGN KEY (DNI) REFERENCES Trabajadores(DNI) ON DELETE SET NULL ON UPDATE CASCADE\n" +
                ")\n";
        int n = 0;

        try{
            n =  connection.createStatement().executeUpdate(sql);
            System.out.println("Executed " + sql + " statements");
        }catch (SQLException ex){
            System.out.println("Error creating tables coches " + ex.getMessage());
        }

        return n;
    }

    /**
     * Agrega una clave foránea entre las tablas `Coches` y `Trabajadores`.
     *
     * @return Número de filas afectadas por la operación.
     */
    public int alterTableTrabajadores(){
        String sql = "ALTER TABLE Coches\n" +
                "ADD CONSTRAINT fk_coches_trabajadores\n" +
                "FOREIGN KEY (DNI) REFERENCES Trabajadores(DNI) ON DELETE SET NULL ON UPDATE CASCADE;\n";
        int n = 0;

        try {
            n =  connection.createStatement().executeUpdate(sql);
            System.out.println("Executed " + sql + " statements");
        }catch (SQLException ex){
            System.out.println("Error alter table trabajadores " + ex.getMessage());
        }
        return n;
    }

    /**
     * Crea la tabla `Servicios` en la base de datos si no existe.
     *
     * @return Número de filas afectadas por la operación.
     */
    public int createTableServicios(){
        String sql = "CREATE TABLE IF NOT EXISTS Servicios (\n" +
                "    Numero INT AUTO_INCREMENT PRIMARY KEY,\n" +
                "    Fecha DATE NOT NULL,\n" +
                "    Tipo VARCHAR(50) NOT NULL,\n" +
                "    Cantidad DOUBLE NOT NULL,\n" +
                "    Comentario TEXT,\n" +
                "    DNI VARCHAR(15),\n" +
                "    CIF VARCHAR(15),\n" +
                "    FOREIGN KEY (DNI) REFERENCES Trabajadores(DNI) ON DELETE SET NULL ON UPDATE CASCADE,\n" +
                "    FOREIGN KEY (CIF) REFERENCES Clientes(CIF) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                ")\n";
        int n = 0;
        try{
            n =  connection.createStatement().executeUpdate(sql);
            System.out.println("Executed " + sql + " statements");
        }catch (SQLException ex){
            System.out.println("Error creating tables servicios " + ex.getMessage());
        }

        return n;

    }


}
