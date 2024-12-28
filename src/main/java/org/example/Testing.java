package org.example;

import org.example.controlles.TablesControllers;
import org.example.controlles.DbController;
import org.example.models.Coche;
import org.example.models.Trabajador;

import java.io.BufferedReader;
import java.io.FileReader;

import java.util.ArrayList;

public class Testing {

    public void executeTesting(){
        ArrayList<Coche> coches;
        ArrayList<Trabajador> trabajadores ;

        DbController dbController = new DbController();
        TablesControllers tablesControllers = new TablesControllers(dbController);

        connection(dbController);

        createTables(tablesControllers);
        trabajadores = csvTrabajadores();
        insertTableTrabajadores(trabajadores, tablesControllers);

        coches = csvCoche();
        insertTableBaseCoche(coches, tablesControllers);

        disconnect(dbController);
    }
    public  void connection(DbController dbController){
        dbController.connect();
    }

    public void disconnect(DbController dbController){
        dbController.disconnect();
    }
    public void createTables(TablesControllers tableControllers){
        tableControllers.createTableTrabajadores();
        tableControllers.createTableCoches();
        tableControllers.alterTableTrabajadores();
        tableControllers.createTablesClientes();
        tableControllers.createTableServicios();
    }
    public ArrayList<Coche> csvCoche(){
        ArrayList<Coche> coches = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/example/CSV/coches.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Coche c = new Coche();
                String[] split = line.split(";");
                c.setMatricula(split[0]);
                c.setMarca(split[1]);
                c.setModelo(split[2]);
                c.setAno(Integer.parseInt(split[3]));
                c.setDni(split[4]);
                coches.add(c);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return coches;
    }

    public ArrayList<Trabajador> csvTrabajadores(){
        ArrayList<Trabajador> trabajadores = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/example/CSV/trabajadores.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Trabajador t = new Trabajador();
                String[] split = line.split(";");
                t.setDni(split[0]);
                t.setNombre(split[1]);
                t.setApellidos(split[2]);
                t.setSueldo(Double.parseDouble(split[3]));
                t.setFecha(split[4]);
                t.setMatricula(split[5]);
                trabajadores.add(t);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return trabajadores;
    }
    public void insertTableTrabajadores(ArrayList<Trabajador> trabajadores,TablesControllers tablesControllers){
        for(Trabajador t : trabajadores){
            System.out.println(t);
            addDataBaseTrabajadores(t,tablesControllers);
        }
    }
    public void addDataBaseTrabajadores(Trabajador t,TablesControllers tablesControllers){
        String sql = "INSERT INTO Trabajadores(DNI,Nombre,Apellidos,Sueldo,Fecha,Matricula) " +
                "VALUES('"+t.getDni()+"','"+t.getNombre()+"','"+t.getApellidos()+
                "','"+t.getSueldo()+"','"+t.getFecha()+"','"+t.getMatricula()+"')";
        tablesControllers.addDataBase(sql);

    }
    public void insertTableBaseCoche(ArrayList<Coche> coches, TablesControllers tablesControllers){
        for(Coche c : coches){
            addDataBaseCoches(c,tablesControllers);
        }
    }
    public void addDataBaseCoches(Coche c,TablesControllers tablesControllers){
        String sql = "INSERT INTO Coches(Matricula, Marca, Modelo, Anio, DNI) " +
                "VALUES('" + c.getMatricula() + "','" + c.getMarca() + "','" + c.getModelo() + "'," +
                c.getAno() + ",'" + c.getDni() + "')";
        tablesControllers.addDataBase(sql);
    }

}
