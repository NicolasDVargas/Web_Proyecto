package com.example.proyecto.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Cliente Propietario;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Dulce> dulces;

    @Basic
    private Long total;

    @Basic
    private String fechaCompra;

    public Compra(){}


    public Compra(Cliente propietario, List<Dulce> pedido) {
        Propietario = propietario;
        this.dulces = pedido;
        setfecha();
    }

    public void setfecha(){
        LocalDate fecha = java.time.LocalDate.now();
        setFechaCompra(fecha.toString());
    }


    public void setFechaCompra(String string) {
        this.fechaCompra = string;
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public Cliente getPropietario() {
        return Propietario;
    }


    public void setPropietario(Cliente propietario) {
        Propietario = propietario;
    }


    public List<Dulce> getPedido() {
        return dulces;
    }


    public void setPedido(List<Dulce> pedido) {
        this.dulces = pedido;
    }


    public Long getTotal() {
        return total;
    }


    public void setTotal(Long total) {
        this.total = total;
    }


    public String getFechaCompra() {
        return fechaCompra;
    }


    public List<Dulce> getDulces() {
        return dulces;
    }


    public void setDulces(List<Dulce> dulces) {
        this.dulces = dulces;
    }


}
