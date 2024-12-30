package org.example;

import org.example.controlles.TablesControllers;
import org.example.controlles.DbController;
import org.example.models.Cliente;
import org.example.models.Coche;
import org.example.models.Servicio;
import org.example.models.Trabajador;

import java.io.BufferedReader;
import java.io.FileReader;

import java.util.ArrayList;

public class Testing {

    public void executeTesting(){

        DbController dbController = new DbController();
        TablesControllers tablesControllers = new TablesControllers(dbController);

        connection(dbController);

        createTables(tablesControllers);
        insertsCsvToDatabase(tablesControllers);

        disconnect(dbController);
    }
    private void insertsCsvToDatabase(TablesControllers tablesControllers){
        ArrayList<Coche> coches;
        ArrayList<Trabajador> trabajadores ;
        ArrayList<Cliente> clientes ;
        ArrayList<Servicio> servicios ;


        trabajadores = csvTrabajadores();
        insertTableTrabajadores(trabajadores, tablesControllers);

        coches = csvCoche();
        insertTableBaseCoche(coches, tablesControllers);

        clientes = csvCliente();
        insertTableClientes(clientes, tablesControllers);

        servicios = csvServicio();
        insertTableServicios(servicios, tablesControllers);
    }
    private  void connection(DbController dbController){
        dbController.connect();
    }

    private void disconnect(DbController dbController){
        dbController.disconnect();
    }
    private void createTables(TablesControllers tableControllers){
        tableControllers.createTableTrabajadores();
        tableControllers.createTableCoches();
        tableControllers.alterTableTrabajadores();
        tableControllers.createTablesClientes();
        tableControllers.createTableServicios();
    }
    private ArrayList<Coche> csvCoche(){
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

    private ArrayList<Trabajador> csvTrabajadores(){
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
    private void insertTableTrabajadores(ArrayList<Trabajador> trabajadores,TablesControllers tablesControllers){
        for(Trabajador t : trabajadores){
            addDataBaseTrabajadores(t,tablesControllers);
        }
    }
    private void addDataBaseTrabajadores(Trabajador t,TablesControllers tablesControllers){
        String sql = "INSERT INTO Trabajadores(DNI,Nombre,Apellidos,Sueldo,Fecha,Matricula) " +
                "VALUES('"+t.getDni()+"','"+t.getNombre()+"','"+t.getApellidos()+
                "','"+t.getSueldo()+"','"+t.getFecha()+"','"+t.getMatricula()+"')";
        tablesControllers.addInfoDataBase(sql);

    }
    private void insertTableBaseCoche(ArrayList<Coche> coches, TablesControllers tablesControllers){
        for(Coche c : coches){
            addDataBaseCoches(c,tablesControllers);
        }
    }
    private void addDataBaseCoches(Coche c,TablesControllers tablesControllers){
        String sql = "INSERT INTO Coches(Matricula, Marca, Modelo, Anio, DNI) " +
                "VALUES('" + c.getMatricula() + "','" + c.getMarca() + "','" + c.getModelo() + "'," +
                c.getAno() + ",'" + c.getDni() + "')";
        tablesControllers.addInfoDataBase(sql);
    }
    private ArrayList<Cliente> csvCliente(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/example/CSV/clientes.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Cliente c = new Cliente();
                String[] split = line.split(";");
                c.setCif(split[0]);
                c.setNombre(split[1]);
                c.setDireccion(split[2]);
                c.setTelefono01(split[3]);
                c.setTelefono02(split[4]);
                clientes.add(c);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return clientes;
    }
    private void insertTableClientes(ArrayList<Cliente> clientes,TablesControllers tablesControllers){
        for(Cliente c : clientes){
            addDataBaseClientes(c,tablesControllers);
        }
    }
    private void addDataBaseClientes(Cliente c,TablesControllers tablesControllers){
        String sql = "INSERT INTO Clientes(CIF,nombre,direccion,tfno1,tfno2) " +
                "VALUES('"+c.getCif()+"','"+c.getNombre()+"','"+c.getDireccion()+"','"+c.getTelefono01()+"','"+c.getTelefono02()+"')";
        tablesControllers.addInfoDataBase(sql);

    }
    private ArrayList<Servicio> csvServicio(){
        ArrayList<Servicio> servicios = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/example/CSV/servicios.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Servicio s = new Servicio();
                String[] split = line.split(";");
                s.setNumero(Integer.parseInt(split[0]));
                s.setFecha(split[1]);
                s.setTipo(split[2]);
                s.setCantidad(Double.parseDouble(split[3]));
                s.setText(split[4]);
                s.setDni(split[5]);
                s.setCif(split[6]);

                servicios.add(s);

            }
        }catch (Exception e){
            System.out.println("Error "+e.getMessage());
        }

        return servicios;
    }
    private void insertTableServicios(ArrayList<Servicio> servicios,TablesControllers tablesControllers){
        for(Servicio s : servicios){
            addDataBaseServicios(s,tablesControllers);
        }
    }
    private void addDataBaseServicios(Servicio s,TablesControllers tablesControllers){
        String sql = "INSERT INTO Servicios(Numero,Fecha,Tipo,Cantidad,Comentario,DNI,CIF) " +
                "VALUE('"+s.getNumero()+"','"+s.getFecha()+"','"+s.getTipo()+"','"
                +s.getCantidad()+"','"+s.getText()+"','"+s.getDni()+"','"+s.getCif()+"')";
        tablesControllers.addInfoDataBase(sql);

    }

}
