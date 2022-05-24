package com.example.proyecto.model;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic
    private String nombre;

    @Column(unique=true)
    private String contrasenna;
    
    @Basic
    private String email;

    @Basic
    private boolean admin;

    @OneToOne//ya es eager
    private Compra carrito;

    @ManyToOne(fetch = FetchType.LAZY)
    Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @OneToMany(cascade = CascadeType.ALL)
    private List<Compra> facturas;
    
    public Cliente() {}

    public Cliente( String nombre, String contrasenna, String email, boolean admin, Role role) {;
        this.nombre = nombre;
        this.contrasenna = contrasenna;
        this.email = email;
        this.admin = admin;
        this.role = role;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
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

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Compra getCarrito() {
        return carrito;
    }

    public void setCarrito(Compra carrito) {
        this.carrito = carrito;
    }

    public List<Compra> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Compra> facturas) {
        this.facturas = facturas;
    }

}
