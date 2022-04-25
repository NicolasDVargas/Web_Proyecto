package com.example.proyecto.Services;

import com.example.proyecto.model.Cliente;

public interface IClienteService {
    boolean agregarCliente(Cliente cliente);

    boolean editarCliente(Cliente cliente, Long id);

    Cliente buscarPorId(Long id);

    Cliente buscarPorEmailContrasenna(String email,String contrasenna);

}
