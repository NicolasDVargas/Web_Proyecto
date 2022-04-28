package com.example.proyecto.Services;

import java.util.List;
import java.util.Optional;

import com.example.proyecto.Repository.ClienteRepository;
import com.example.proyecto.Repository.CompraRepository;
import com.example.proyecto.model.Cliente;
import com.example.proyecto.model.Compra;
import com.example.proyecto.model.Dulce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService{

    @Autowired
    ClienteRepository repoCli;
    CompraRepository repoC;

    @Override
    public boolean agregarCliente(Cliente cliente) {
        if(repoCli.save(cliente)!=null){
            return true;
        }
        return false;
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> c = repoCli.findById(id);
        if(c.isPresent()){
            return c.get();
        }else{
            return null;
        }
    }

    @Override
    public Cliente buscarPorEmailContrasenna(String email, String contrasenna) {
        Optional<Cliente> c =  repoCli.findByEmailAndContrasenna(email, contrasenna);
        if(c.isPresent()){
            return c.get();
        }else{
            return null;
        }
    }

    @Override
    public boolean editarCliente(Cliente cliente, Long id) {
        Optional<Cliente> c = repoCli.findById(id);
        if(c.isPresent()){
            Cliente nuevo = c.get();
            nuevo.setNombre(cliente.getNombre());
            nuevo.setEmail(cliente.getEmail());
            nuevo.setContrasenna(cliente.getContrasenna());
            repoCli.save(nuevo);
            return true ;
        }else{
            return false;
        }
    }
    
    @Override
    public List<Cliente> tomarClientes() {
        return repoCli.findAll();
    }

    @Override
    public boolean editarCompra(List<Dulce> carrito, Long id) {
        Optional<Cliente> c =  repoCli.findById(id);
        if(c.isPresent()){
            Cliente cliente = c.get();
            Compra compraNueva = cliente.getCarrito();
            compraNueva.setDulces(carrito);
            repoC.save(compraNueva);
            cliente.setCarrito(compraNueva);
            repoCli.save(cliente);
            return true;
        }
        return false;
    }
    
}
