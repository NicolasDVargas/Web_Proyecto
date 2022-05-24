package com.example.proyecto.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dulce {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String tipo;
    private Long cantidad;
    private Long vendido;
    private Long costo;
    
    public Dulce() {}

    public Dulce(String nombre, String tipo, Long cantidad, Long costo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.costo = costo;
        this.vendido=0L;
    }

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

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Long getVendido() {
        return vendido;
    }

    public void setVendido(Long vendido) {
        this.vendido = vendido;
    }

    public Long getCosto() {
        return costo;
    }

    public void setCosto(Long costo) {
        this.costo = costo;
    }

    

    

}
