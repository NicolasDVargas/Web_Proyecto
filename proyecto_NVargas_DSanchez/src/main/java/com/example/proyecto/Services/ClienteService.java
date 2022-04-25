package com.example.proyecto.Services;

import java.util.List;
import java.util.Optional;

import com.example.proyecto.Repository.ClienteRepository;
import com.example.proyecto.model.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService{

    @Autowired
    ClienteRepository repoCli;

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
            repoCli.save(c.get());
            return true ;
        }else{
            return false;
        }
    }
    
    @Override
    public List<Cliente> tomarClientes() {
        return repoCli.findAll();
    }
    
}
