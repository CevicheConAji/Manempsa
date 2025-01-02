package org.example.controlles;

import org.example.models.Cliente;
import org.example.models.Coche;
import org.example.models.Servicio;
import org.example.models.Trabajador;

import java.io.BufferedReader;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Testing {

    public void executeTesting(){

        DbController dbController = new DbController();
        TablesCreateControllers tablesCreateControllers = new TablesCreateControllers(dbController);

        connection(dbController);

        createTables(tablesCreateControllers);

        insertsCsvToDatabase(dbController);

        querys(dbController);

        insertarRegistrosCoches(dbController);

        disconnect(dbController);
    }
    private void insertarRegistros(DbController dbController) {
        insertarRegistroTrabajador(dbController);
        insertarRegistrosCliente(dbController);
        insertarRegistrosServicio(dbController);
        insertarRegistrosCoches(dbController);
    }
    private void insertsCsvToDatabase(DbController dbController){
        ArrayList<Coche> coches = csvCoche();
        ArrayList<Trabajador> trabajadores = csvTrabajadores() ;
        ArrayList<Cliente> clientes = csvCliente() ;
        ArrayList<Servicio> servicios = csvServicio() ;

        CocheController controllerCoche = new CocheController(dbController);
        TrabajadorController controllerTrabajador = new TrabajadorController(dbController);
        ClienteController controllerCliente = new ClienteController(dbController);
        ServicioController controllerServicio = new ServicioController(dbController);

        controllerTrabajador.insertTrabajadores(trabajadores);
        controllerCliente.insertTableCliente(clientes);
        controllerServicio.insertTableServicios(servicios);
        controllerCoche.insertTableBaseCoche(coches);

    }
    public void querys(DbController dbController){
        Scanner sc = new Scanner(System.in);
        CocheController controllerCoche = new CocheController(new DbController());
        ClienteController controllerCliente = new ClienteController(new DbController());
        ServicioController controllerServicio = new ServicioController(new DbController());

        controllerCoche.showquery(dbController.createQuery("SELECT * FROM Coches"));
        controllerCliente.showquery(dbController.createQuery("SELECT * FROM Clientes"));

        System.out.println("Introduzca el DNI del conductor del coche");
        String dni = sc.nextLine();

        controllerCoche.showquery(dbController.createQuery("SELECT * FROM Coches WHERE Dni ='" + dni + "'"));
        System.out.println("Introduzca el cif del Cliente para ver los servicios ");
        String cif = sc.nextLine();

        controllerServicio.showQuery(dbController.createQuery("SELECT * FROM Servicios WHERE CIF ='" +  cif + "'"));
    }
    private  void connection(DbController dbController){
        dbController.connect();
    }

    private void disconnect(DbController dbController){
        dbController.disconnect();
    }
    private void createTables(TablesCreateControllers tableControllers){
        tableControllers.createTablesClientes();
        tableControllers.createTableTrabajadores();
        tableControllers.alterTableTrabajadores();
        tableControllers.createTableCoches();
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

    private void insertarRegistrosCliente(DbController dbController){
        System.out.println("Insertando registros clientes");
        ClienteController clienteController = new ClienteController(dbController);
        Cliente clienteEjemplo01 = new Cliente();

        clienteEjemplo01.setCif("CIF12345");
        clienteEjemplo01.setNombre("Empresa Alfa");
        clienteEjemplo01.setDireccion("C/Mayor, 1");
        clienteEjemplo01.setTelefono01("123456789");
        clienteEjemplo01.setTelefono02("987654321");

        Cliente clienteEjemplo02 = new Cliente();

        clienteEjemplo02.setCif("CIF67890");
        clienteEjemplo02.setNombre("Empresa Beta");
        clienteEjemplo02.setDireccion("AV/Central, 42");
        clienteEjemplo02.setTelefono01("654321987");
        clienteEjemplo02.setTelefono02("321987654");

        clienteController.insertTableCliente(clienteEjemplo01);
        clienteController.insertTableCliente(clienteEjemplo02);

        System.out.println("Registros clientes\n"+clienteEjemplo01);
        System.out.println("Registros clientes\n"+clienteEjemplo02);
    }
    public void insertarRegistroTrabajador(DbController dbController){
        System.out.println("Insertando registros trabajadores");
        Trabajador trabajadorEjemplo01 = new Trabajador();

        trabajadorEjemplo01.setDni("12345678A");
        trabajadorEjemplo01.setNombre("Juan");
        trabajadorEjemplo01.setApellidos("Pérez");
        trabajadorEjemplo01.setSueldo(2000.00);
        trabajadorEjemplo01.setFecha("2023/01/01");
        trabajadorEjemplo01.setMatricula("NULL");

        Trabajador trabajadorEjemplo02 = new Trabajador();

        trabajadorEjemplo02.setDni("87654321B");
        trabajadorEjemplo02.setNombre("Ana");
        trabajadorEjemplo02.setApellidos("Garcia");
        trabajadorEjemplo02.setSueldo(2500.00);
        trabajadorEjemplo02.setFecha("2022/06/15");
        trabajadorEjemplo02.setMatricula("NULL");

        TrabajadorController trabajadorController = new TrabajadorController(dbController);
        trabajadorController.insertTrabajadores(trabajadorEjemplo01);
        trabajadorController.insertTrabajadores(trabajadorEjemplo02);

        
        System.out.println("Registro Trabajador\n"+trabajadorEjemplo01);
        System.out.println("Registro Trabajador\n"+trabajadorEjemplo02);


    }
    public void insertarRegistrosServicio(DbController dbController){
        System.out.println("Insertando registros servicios");

        ServicioController servicioController = new ServicioController(dbController);
        Servicio servicioEjemplo01 = new Servicio();
        servicioEjemplo01.setFecha("2024/01/10");
        servicioEjemplo01.setTipo("Reparacion");
        servicioEjemplo01.setCantidad(300.50);
        servicioEjemplo01.setText("Cambio de piezas");
        servicioEjemplo01.setDni("12345678A");
        servicioEjemplo01.setCif("CIF12345");


        Servicio servicioEjemplo02 = new Servicio();

        servicioEjemplo02.setFecha("2024/02/15");
        servicioEjemplo02.setTipo("Mantenimiento");
        servicioEjemplo02.setCantidad(150.75);
        servicioEjemplo02.setText("Revisión general");
        servicioEjemplo02.setDni("87654321B");
        servicioEjemplo02.setCif("CIF67890");

        servicioController.insertTableServicios(servicioEjemplo01);
        servicioController.insertTableServicios(servicioEjemplo02);

        System.out.println("Registro Servicios\n"+servicioEjemplo01);
        System.out.println("Registro Servicios\n"+servicioEjemplo02);

    }
    public void insertarRegistrosCoches(DbController dbController){
        System.out.println("Insertando registros coches");

        CocheController cocheController = new CocheController(dbController);

        Coche cocheEjemplo01 = new Coche();

        cocheEjemplo01.setMatricula("4433KBB");
        cocheEjemplo01.setMarca("Toyota");
        cocheEjemplo01.setModelo("Corolla");
        cocheEjemplo01.setAno(2020);
        cocheEjemplo01.setDni("12345678A");

        Coche cocheEjemplo02 = new Coche();

        cocheEjemplo02.setMatricula("2211ZZZ");
        cocheEjemplo02.setMarca("Honda");
        cocheEjemplo02.setModelo("Civic");
        cocheEjemplo02.setAno(2019);
        cocheEjemplo02.setDni("87654321B");

        cocheController.insertTableBaseCoche(cocheEjemplo01);
        cocheController.insertTableBaseCoche(cocheEjemplo02);

        System.out.println("Registro Coches\n"+cocheEjemplo01);
        System.out.println("Registro Coches\n"+cocheEjemplo02);

    }

}
