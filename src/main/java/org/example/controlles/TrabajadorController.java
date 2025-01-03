package org.example.controlles;

import org.example.models.Trabajador;

import java.util.ArrayList;

/**
 * Clase que controla las operaciones relacionadas con los trabajadores en la base de datos.
 * Permite insertar registros en la tabla `Trabajadores`.
 */
public class TrabajadorController {

    /**
     * Controlador de base de datos utilizado para manejar las operaciones con la base de datos.
     */
    private final DbController dbController;


    /**
     * Constructor de la clase TrabajadorController.
     *
     * @param dbController Controlador de base de datos proporcionado para interactuar con la base de datos.
     */
    public TrabajadorController(DbController dbController) {
        this.dbController = dbController;
    }


    /**
     * Inserta múltiples registros en la tabla `Trabajadores`.
     *
     * @param trabajadores Lista de objetos {@link Trabajador} que se insertarán en la base de datos.
     */
    public void insertTrabajadores(ArrayList<Trabajador> trabajadores){
        for (Trabajador t : trabajadores){
            String sql = "INSERT INTO Trabajadores(DNI,Nombre,Apellidos,Sueldo,Fecha,Matricula) " +
                    "VALUES('"+t.getDni()+"','"+t.getNombre()+"','"+t.getApellidos()+
                    "','"+t.getSueldo()+"','"+t.getFecha()+"','"+t.getMatricula()+"')";
            dbController.addInfoDataBase(sql);
        }

    }

    /**
     * Inserta un único registro en la tabla `Trabajadores`.
     *
     * @param t Objeto {@link Trabajador} que se insertará en la base de datos.
     */
    public void insertTrabajadores(Trabajador t){
        String sql = "INSERT INTO Trabajadores(DNI,Nombre,Apellidos,Sueldo,Fecha,Matricula) " +
                    "VALUES('"+t.getDni()+"','"+t.getNombre()+"','"+t.getApellidos()+
                    "','"+t.getSueldo()+"','"+t.getFecha()+"','"+t.getMatricula()+"')";
        dbController.addInfoDataBase(sql);

    }

}
