package org.example.controlles;

import org.example.models.Servicio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase que controla las operaciones relacionadas con los servicios en la base de datos.
 * Permite insertar registros en la tabla `Servicios` y consultar información desde ella.
 */
public class ServicioController {

    /**
     * Controlador para manejar la conexión y las operaciones con la base de datos.
     */
    public final DbController dbController;

    /**
     * Constructor de la clase ServicioController.
     *
     * @param dbController Controlador de base de datos utilizado para interactuar con la base de datos.
     */
    public ServicioController(DbController dbController) {
        this.dbController = dbController;
    }

    /**
     * Inserta múltiples registros en la tabla `Servicios`.
     *
     * @param servicios Lista de objetos {@link Servicio} que se insertarán en la base de datos.
     */
    public void insertTableServicios(ArrayList<Servicio> servicios){
        for (Servicio s : servicios) {
            String sql = "INSERT INTO Servicios(Fecha,Tipo,Cantidad,Comentario,DNI,CIF) " +
                    "VALUE('"+s.getFecha()+"','"+s.getTipo()+"','"
                    +s.getCantidad()+"','"+s.getText()+"','"+s.getDni()+"','"+s.getCif()+"')";
            dbController.addInfoDataBase(sql);
        }


    }

    /**
     * Inserta un único registro en la tabla `Servicios`.
     *
     * @param s Objeto {@link Servicio} que se insertará en la base de datos.
     */
    public void insertTableServicios(Servicio s){
            String sql = "INSERT INTO Servicios(Fecha,Tipo,Cantidad,Comentario,DNI,CIF) " +
                    "VALUE('"+s.getFecha()+"','"+s.getTipo()+"','"
                    +s.getCantidad()+"','"+s.getText()+"','"+s.getDni()+"','"+s.getCif()+"')";
            dbController.addInfoDataBase(sql);
        }

    /**
     * Procesa un conjunto de resultados (ResultSet) y convierte cada fila en un objeto {@link Servicio}.
     * También imprime la información de los servicios en formato tabular en la consola.
     *
     * @param rs Objeto {@link ResultSet} que contiene los datos obtenidos de una consulta a la base de datos.
     * @return Lista de objetos {@link Servicio} creados a partir de las filas del ResultSet.
     */
    public ArrayList<Servicio> showQuery(ResultSet rs){
        ArrayList<Servicio> servicios = new ArrayList<>();
        System.out.printf("%-10s %-20s %-20s %-20s %-20s %-10s\n",
                "Numero","Fecha","Tipo","Cantidad","Comentario","DNI");
        try{
            while (rs.next()) {
                // Crear un objeto Servicio a partir de cada fila del ResultSet
                Servicio servicio = new Servicio();

                servicio.setNumero(rs.getInt("Numero"));
                servicio.setFecha(rs.getString("Fecha"));
                servicio.setTipo(rs.getString("Tipo"));
                servicio.setCantidad(rs.getInt("Cantidad"));
                servicio.setText(rs.getString("Comentario"));
                servicio.setDni(rs.getString("DNI"));
                servicio.setCif(rs.getString("CIF"));

                servicios.add(servicio);

                // Imprimir los datos del servicio en formato tabular
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
