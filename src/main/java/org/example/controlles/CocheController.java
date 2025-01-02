package org.example.controlles;

import org.example.models.Coche;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CocheController {
    public final DbController dbController;

    public CocheController(DbController dbController) {
        this.dbController = dbController;
    }

    public void insertTableBaseCoche(ArrayList<Coche> coches){
        for(Coche c : coches){
            String sql = "INSERT INTO Coches(Matricula, Marca, Modelo, Anio, DNI) " +
                    "VALUES('" + c.getMatricula() + "','" + c.getMarca() + "','" + c.getModelo() + "'," +
                    c.getAno() + ",'" + c.getDni() + "')";
            dbController.addInfoDataBase(sql);
        }
    }

    public void insertTableBaseCoche(Coche c){

        String sql = "INSERT INTO Coches(Matricula, Marca, Modelo, Anio, DNI) " +
                    "VALUES('" + c.getMatricula() + "','" + c.getMarca() + "','" + c.getModelo() + "'," +
                    c.getAno() + ",'" + c.getDni() + "')";
        dbController.addInfoDataBase(sql);

    }
    public ArrayList<Coche> showquery(ResultSet rs){
        ArrayList<Coche> coches = new ArrayList<>();

        try {
            System.out.println();
            System.out.printf("%-10s %-10s %-10s %-10s %-10s\n", "Matricula", "Marca", "Modelo", "Anio", "DNI");

            while (rs.next()) {
                Coche c =  new Coche();
                c.setMatricula(rs.getString("Matricula"));
                c.setMarca(rs.getString("Marca"));
                c.setModelo(rs.getString("Modelo"));
                c.setAno(rs.getInt("Anio"));
                c.setDni(rs.getString("DNI"));
                coches.add(c);
                System.out.printf("%-10s %-10s %-10s %-10s %-10s\n",
                        c.getMatricula(),c.getMarca(),c.getModelo(),c.getAno(),c.getDni());
            }
        }catch (SQLException e){
            System.out.println("Error " +e.getMessage());
        }
        return coches;
    }
}
