package com.example.proyecto.Services;

import java.util.List;

import com.example.proyecto.model.Dulce;

public interface IDulceService {

    boolean agregarDulce(Dulce dulce);

    boolean eliminarDulce(Long id);

    boolean editarDulce(Dulce dulce, Long id);

    Dulce buscarPorNombre(String nombre);

    Dulce buscarPorId(Long id);

    List<Dulce> buscarPorTipo(String tipo);

    List<Dulce> getDulces();

}
