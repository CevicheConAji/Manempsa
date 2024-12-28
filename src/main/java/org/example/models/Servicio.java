package org.example.models;

import java.sql.Date;

public class Servicio {
    private int numero;
    private Date fecha;
    private double cantidad;
    private String text;
    private String dni;
    private String cif;

    public Servicio(int numero, Date fecha, double cantidad, String text, String dni, String cif) {
        this.numero = numero;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.text = text;
        this.dni = dni;
        this.cif = cif;
    }

    public Servicio() {
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }
}
