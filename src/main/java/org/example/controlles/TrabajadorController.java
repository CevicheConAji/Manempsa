package org.example.controlles;

import org.example.models.Trabajador;

import java.util.ArrayList;

public class TrabajadorController {
    private final DbController dbController;

    public TrabajadorController(DbController dbController) {
        this.dbController = dbController;
    }

    public void insertTrabajadores(ArrayList<Trabajador> trabajadores){
        for (Trabajador t : trabajadores){
            String sql = "INSERT INTO Trabajadores(DNI,Nombre,Apellidos,Sueldo,Fecha,Matricula) " +
                    "VALUES('"+t.getDni()+"','"+t.getNombre()+"','"+t.getApellidos()+
                    "','"+t.getSueldo()+"','"+t.getFecha()+"','"+t.getMatricula()+"')";
            dbController.addInfoDataBase(sql);
        }

    }
    public void insertTrabajadores(Trabajador t){
        String sql = "INSERT INTO Trabajadores(DNI,Nombre,Apellidos,Sueldo,Fecha,Matricula) " +
                    "VALUES('"+t.getDni()+"','"+t.getNombre()+"','"+t.getApellidos()+
                    "','"+t.getSueldo()+"','"+t.getFecha()+"','"+t.getMatricula()+"')";
        dbController.addInfoDataBase(sql);

    }

}
