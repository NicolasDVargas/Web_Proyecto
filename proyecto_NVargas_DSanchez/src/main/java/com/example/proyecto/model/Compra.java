package com.example.proyecto.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


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

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCompra;

    public Compra(){}


    public Compra(Cliente propietario, List<Dulce> pedido) {
        Propietario = propietario;
        this.dulces = pedido;
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


    public Date getFechaCompra() {
        return fechaCompra;
    }


    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    
}
