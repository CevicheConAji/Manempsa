package org.example.controlles;

import org.example.models.Servicio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServicioController {
    public final DbController dbController;

    public ServicioController(DbController dbController) {
        this.dbController = dbController;
    }

    public void insertTableServicios(ArrayList<Servicio> servicios){
        for (Servicio s : servicios) {
            String sql = "INSERT INTO Servicios(Fecha,Tipo,Cantidad,Comentario,DNI,CIF) " +
                    "VALUE('"+s.getFecha()+"','"+s.getTipo()+"','"
                    +s.getCantidad()+"','"+s.getText()+"','"+s.getDni()+"','"+s.getCif()+"')";
            dbController.addInfoDataBase(sql);
        }


    }
    public void insertTableServicios(Servicio s){
            String sql = "INSERT INTO Servicios(Fecha,Tipo,Cantidad,Comentario,DNI,CIF) " +
                    "VALUE('"+s.getFecha()+"','"+s.getTipo()+"','"
                    +s.getCantidad()+"','"+s.getText()+"','"+s.getDni()+"','"+s.getCif()+"')";
            dbController.addInfoDataBase(sql);
        }
    public ArrayList<Servicio> showQuery(ResultSet rs){
        ArrayList<Servicio> servicios = new ArrayList<>();
        System.out.printf("%-10s %-20s %-20s %-20s %-20s %-10s\n",
                "Numero","Fecha","Tipo","Cantidad","Comentario","DNI");
        try{
            while (rs.next()) {
                Servicio servicio = new Servicio();

                servicio.setNumero(rs.getInt("Numero"));
                servicio.setFecha(rs.getString("Fecha"));
                servicio.setTipo(rs.getString("Tipo"));
                servicio.setCantidad(rs.getInt("Cantidad"));
                servicio.setText(rs.getString("Comentario"));
                servicio.setDni(rs.getString("DNI"));
                servicio.setCif(rs.getString("CIF"));

                servicios.add(servicio);
                System.out.printf("%-10s %-20s %-20s %-10s %-20s %-20s %-10s\n",
                        servicio.getNumero(),servicio.getFecha(),servicio.getTipo(),
                        servicio.getCantidad(),servicio.getText(),servicio.getDni(),servicio.getCif());

            }
        }catch (SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
        return servicios;
    }
}
