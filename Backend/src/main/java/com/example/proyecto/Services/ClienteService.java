package com.example.proyecto.Services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.proyecto.Repository.ClienteRepository;
import com.example.proyecto.Repository.CompraRepository;
import com.example.proyecto.model.Cliente;
import com.example.proyecto.model.Compra;
import com.example.proyecto.model.Dulce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements UserDetailsService,IClienteService{

    @Autowired
    ClienteRepository repoCli;

    @Autowired
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
    public Cliente buscarPorEmail(String email) {
        Optional<Cliente> c = repoCli.findByEmail(email);
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

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Cliente> cliente = repoCli.findByEmail(username);
        if(cliente.isPresent()){
            Cliente cli = cliente.get();
            var authorities = List.of(new SimpleGrantedAuthority("ROLE_"+cli.getRole().getName()));
            return new User(cli.getEmail(), cli.getContrasenna(), authorities);
        }
        return null;
    }
    
}