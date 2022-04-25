package com.example.proyecto.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.proyecto.Repository.CompraRepository;
import com.example.proyecto.model.Compra;
import com.example.proyecto.model.Dulce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraService implements ICompraService {

    @Autowired
    private CompraRepository repoC;

    @Override
    public boolean agregarCompra(Compra compra) {
      
        repoC.save(compra);
        return  true;
    } 

    @Override
    public List<Compra> buscarEntreFechas(Date inicio, Date fin) {
        return  repoC.findByFechaCompraBetween(inicio, fin);
    }

    @Override
    public List<Compra> buscarPorDuenno(Long clienteID) {
        return  repoC.getByIdPropietario(clienteID);
    }

    @Override
    public List<Compra> buscarPorFecha(Date date) {
        
        return repoC.findByFechaCompra(date);
    }

    @Override
    public Compra buscarPorId(Long id) {
        Optional<Compra> c =  repoC.findById(id);
        if(c.isPresent()){
            return c.get();
        }
        return null;
    }

    @Override
    public boolean editarCompra(Compra compra, Long id) {
        Optional<Compra> c =  repoC.findById(id);
        if(c.isPresent()){
            repoC.save(compra);
            return true;
        }
        return false;
    }

    @Override
    public List<Compra> getCompras() {
        return repoC.findAll();
    }

    @Override
    public boolean editarCompra(List<Dulce> carrito, Long id) {
        Optional<Compra> c =  repoC.findById(id);
        if(c.isPresent()){
            Compra compra = c.get();
            compra.setPedido(carrito);
            repoC.save(compra);
            return true;
        }
        return false;
    }

}
