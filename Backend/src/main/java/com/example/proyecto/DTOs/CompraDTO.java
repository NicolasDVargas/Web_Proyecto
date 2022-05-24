package com.example.proyecto.DTOs;

import java.util.Date;
import java.util.List;

public class CompraDTO {
    private long id;
    private List<DulceDTO> pedido;
    private Number total;
    private Date fechaCompra;

    public CompraDTO(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<DulceDTO> getPedido() {
        return pedido;
    }

    public void setPedido(List<DulceDTO> pedido) {
        this.pedido = pedido;
    }

    public Number getTotal() {
        return total;
    }

    public void setTotal(Number total) {
        this.total = total;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    
}
