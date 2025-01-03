package org.example.controlles;

import org.example.models.Cliente;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Clase que controla las operaciones relacionadas con los clientes en la base de datos.
 * Permite insertar registros en la tabla `Clientes` y consultar información desde ella.
 */
public class ClienteController {
    /**
     * Controlador para manejar la conexión y las operaciones con la base de datos.
     */
    public final DbController dbController ;

    /**
     * Constructor de la clase ClienteController.
     *
     * @param dbController Controlador de base de datos utilizado para interactuar con la base de datos.
     */
    public ClienteController(DbController dbController) {
        this.dbController = dbController;
    }

    /**
     * Inserta múltiples registros en la tabla `Clientes`.
     *
     * @param clientes Lista de objetos {@link Cliente} que se insertarán en la base de datos.
     */
    public void insertTableCliente(ArrayList<Cliente> clientes){
        for (Cliente c : clientes){
            String sql = "INSERT INTO Clientes(CIF,nombre,direccion,tfno1,tfno2) " +
                    "VALUES('"+c.getCif()+"','"+c.getNombre()+"','"+c.getDireccion()+"','"+c.getTelefono01()+"','"+c.getTelefono02()+"')";
            dbController.addInfoDataBase(sql);
        }
    }

    /**
     * Inserta un único registro en la tabla `Clientes`.
     *
     * @param c Objeto {@link Cliente} que se insertará en la base de datos.
     */
    public void insertTableCliente(Cliente c){
        String sql = "INSERT INTO Clientes(CIF,nombre,direccion,tfno1,tfno2) " +
                    "VALUES('"+c.getCif()+"','"+c.getNombre()+"','"+c.getDireccion()+"','"+c.getTelefono01()+"','"+c.getTelefono02()+"')";
        dbController.addInfoDataBase(sql);

    }
    /**
     * Procesa un conjunto de resultados (ResultSet) y convierte cada fila en un objeto {@link Cliente}.
     * También imprime la información de los clientes en formato tabular en la consola.
     *
     * @param rs Objeto {@link ResultSet} que contiene los datos obtenidos de una consulta a la base de datos.
     * @return Lista de objetos {@link Cliente} creados a partir de las filas del ResultSet.
     */
    public ArrayList<Cliente> showquery(ResultSet rs){
        ArrayList<Cliente> clientes = new ArrayList<>();

        try {
            System.out.println();
            System.out.printf("%-10s %-20s %-20s %-20s %-20s\n", "CIF", "Nombre", "Direccion", "Telefono 01", "Telefono 02");

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setCif(rs.getString("CIF"));
                c.setNombre(rs.getString("Nombre"));
                c.setDireccion(rs.getString("Direccion"));
                c.setTelefono01(rs.getString("tfno1"));
                c.setTelefono02(rs.getString("tfno2"));
                clientes.add(c);

                // Imprimir los datos del cliente en formato tabular
                System.out.printf("%-10s %-20s %-20s %-20s %-20s\n",c.getCif(),c.getNombre(),c.getDireccion(),c.getTelefono01(),c.getTelefono02());
            }
        }catch (Exception e){
            System.out.println("Error " +e.getMessage());
        }
        return clientes;
    }
}
