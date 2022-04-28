package com.example.proyecto.Services;

import java.util.List;

import com.example.proyecto.model.Compra;
import com.example.proyecto.model.Dulce;

public interface ICompraService {
    
    boolean agregarCompra(Compra dulces, Long id);

    boolean editarCompra(Compra compra, Long id);

    Compra buscarPorId(Long id);

    List<Compra> buscarPorDuenno(Long clienteID);

    List<Compra> buscarPorFecha(String date);

    List<Compra> getCompras();


}
