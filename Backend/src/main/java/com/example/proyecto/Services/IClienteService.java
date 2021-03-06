package com.example.proyecto.Services;

import java.util.List;

import com.example.proyecto.model.Cliente;
import com.example.proyecto.model.Dulce;

public interface IClienteService {
    boolean agregarCliente(Cliente cliente);

    boolean editarCliente(Cliente cliente, Long id);

    Cliente buscarPorId(Long id);

    Cliente buscarPorEmailContrasenna(String email,String contrasenna);

    List<Cliente> tomarClientes();
    
    boolean editarCompra(List<Dulce> carrito, Long id);

    Cliente buscarPorEmail(String email);

}
