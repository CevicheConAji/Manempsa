package org.example.controlles;

import java.sql.Connection;
import java.sql.SQLException;

public class CreateTablesControllers {
    private DbController dbController = new DbController();
    private Connection connection = dbController.getConnection();

    public CreateTablesControllers(DbController dbController) {
        this.dbController = dbController;
    }

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
}
