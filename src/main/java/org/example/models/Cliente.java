package org.example.models;

public class Cliente {
    private String cif;
    private String nombre;
    private String direccion;
    private String telefono01;
    private String telefono02;

    public Cliente(String cif, String nombre, String direccion, String telefono01, String telefono02) {
        this.cif = cif;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono01 = telefono01;
        this.telefono02 = telefono02;
    }

    public Cliente() {
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono01() {
        return telefono01;
    }

    public void setTelefono01(String telefono01) {
        this.telefono01 = telefono01;
    }

    public String getTelefono02() {
        return telefono02;
    }

    public void setTelefono02(String telefono02) {
        this.telefono02 = telefono02;
    }
}
