package org.example.controlles;

import org.example.models.Cliente;
import org.example.models.Coche;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ClienteController {
    public final DbController dbController ;

    public ClienteController(DbController dbController) {
        this.dbController = dbController;
    }

    public void insertTableCliente(ArrayList<Cliente> clientes){
        for (Cliente c : clientes){
            String sql = "INSERT INTO Clientes(CIF,nombre,direccion,tfno1,tfno2) " +
                    "VALUES('"+c.getCif()+"','"+c.getNombre()+"','"+c.getDireccion()+"','"+c.getTelefono01()+"','"+c.getTelefono02()+"')";
            dbController.addInfoDataBase(sql);
        }
    }
    public void insertTableCliente(Cliente c){
        String sql = "INSERT INTO Clientes(CIF,nombre,direccion,tfno1,tfno2) " +
                    "VALUES('"+c.getCif()+"','"+c.getNombre()+"','"+c.getDireccion()+"','"+c.getTelefono01()+"','"+c.getTelefono02()+"')";
        dbController.addInfoDataBase(sql);

    }
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
                System.out.printf("%-10s %-20s %-20s %-20s %-20s\n",c.getCif(),c.getNombre(),c.getDireccion(),c.getTelefono01(),c.getTelefono02());
            }
        }catch (Exception e){
            System.out.println("Error " +e.getMessage());
        }
        return clientes;
    }
}
