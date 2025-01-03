package org.example.controlles;

import org.example.models.Coche;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase que controla las operaciones relacionadas con los coches en la base de datos.
 * Permite insertar registros en la tabla `Coches` y consultar información desde ella.
 */
public class CocheController {

    /**
     * Controlador para manejar la conexión y las operaciones con la base de datos.
     */
    public final DbController dbController;

    /**
     * Constructor de la clase CocheController.
     *
     * @param dbController Controlador de base de datos utilizado para interactuar con la base de datos.
     */
    public CocheController(DbController dbController) {
        this.dbController = dbController;
    }

    /**
     * Inserta múltiples registros en la tabla `Coches`.
     *
     * @param coches Lista de objetos {@link Coche} que se insertarán en la base de datos.
     */
    public void insertTableBaseCoche(ArrayList<Coche> coches){
        for(Coche c : coches){
            String sql = "INSERT INTO Coches(Matricula, Marca, Modelo, Anio, DNI) " +
                    "VALUES('" + c.getMatricula() + "','" + c.getMarca() + "','" + c.getModelo() + "'," +
                    c.getAno() + ",'" + c.getDni() + "')";
            dbController.addInfoDataBase(sql);
        }
    }

    /**
     * Inserta un único registro en la tabla `Coches`.
     *
     * @param c Objeto {@link Coche} que se insertará en la base de datos.
     */
    public void insertTableBaseCoche(Coche c){

        String sql = "INSERT INTO Coches(Matricula, Marca, Modelo, Anio, DNI) " +
                    "VALUES('" + c.getMatricula() + "','" + c.getMarca() + "','" + c.getModelo() + "'," +
                    c.getAno() + ",'" + c.getDni() + "')";
        dbController.addInfoDataBase(sql);

    }

    /**
     * Procesa un conjunto de resultados (ResultSet) y convierte cada fila en un objeto {@link Coche}.
     * También imprime la información de los coches en formato tabular en la consola.
     *
     * @param rs Objeto {@link ResultSet} que contiene los datos obtenidos de una consulta a la base de datos.
     * @return Lista de objetos {@link Coche} creados a partir de las filas del ResultSet.
     */
    public ArrayList<Coche> showquery(ResultSet rs){
        ArrayList<Coche> coches = new ArrayList<>();

        try {
            System.out.println();
            System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", "Matricula", "Marca", "Modelo", "Anio", "DNI");

            while (rs.next()) {
                // Crear un objeto Coche a partir de cada fila del ResultSet
                Coche c =  new Coche();
                c.setMatricula(rs.getString("Matricula"));
                c.setMarca(rs.getString("Marca"));
                c.setModelo(rs.getString("Modelo"));
                c.setAno(rs.getInt("Anio"));
                c.setDni(rs.getString("DNI"));
                coches.add(c);

                // Imprimir los datos del coche en formato tabular
                System.out.printf("%-10s %-10s %-10s %-10s %-10s\n",
                        c.getMatricula(),c.getMarca(),c.getModelo(),c.getAno(),c.getDni());
            }
        }catch (SQLException e){
            System.out.println("Error " +e.getMessage());
        }
        return coches;
    }
}
