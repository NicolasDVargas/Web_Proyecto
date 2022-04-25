package com.example.proyecto.Services;

import java.util.Date;
import java.util.List;

import com.example.proyecto.model.Compra;
import com.example.proyecto.model.Dulce;

public interface ICompraService {
    
    boolean agregarCompra(Compra compra);

    boolean editarCompra(Compra compra, Long id);

    Compra buscarPorId(Long id);

    List<Compra> buscarPorDuenno(Long clienteID);

    boolean editarCompra(List<Dulce> carrito, Long id);

    List<Compra> buscarPorFecha(Date date);

    List<Compra> buscarEntreFechas(Date inicio,Date fin);

    List<Compra> getCompras();


}
