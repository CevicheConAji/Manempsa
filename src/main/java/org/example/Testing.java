package org.example;

import org.example.controlles.CreateTablesControllers;
import org.example.controlles.DbController;

import java.sql.Connection;

public class Testing {

    public void executeTesting(){
        DbController dbController = new DbController();
        CreateTablesControllers createTablesControllers = new CreateTablesControllers(dbController);

        connection(dbController);

        createTables(createTablesControllers);

        disconnect(dbController);
    }
    public  void connection(DbController dbController){
        dbController.connect();
    }

    public void disconnect(DbController dbController){
        dbController.disconnect();
    }
    public void createTables(CreateTablesControllers createTablesControllers){
        createTablesControllers.createTablesClientes();
    }

}
