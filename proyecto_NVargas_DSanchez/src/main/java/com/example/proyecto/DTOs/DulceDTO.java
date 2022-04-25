package com.example.proyecto.DTOs;

public class DulceDTO {

    private long id;
    private String nombre;
    private String tipo;
    private Number cantidad;
    private Number vendido;
    private Number costo;
    

    public DulceDTO() {}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Number getCantidad() {
        return cantidad;
    }

    public void setCantidad(Number cantidad) {
        this.cantidad = cantidad;
    }

    public Number getVendido() {
        return vendido;
    }

    public void setVendido(Number vendido) {
        this.vendido = vendido;
    }

    public Number getCosto() {
        return costo;
    }

    public void setCosto(Number costo) {
        this.costo = costo;
    }
    

}
