package com.example.proyecto.Services;

import java.util.List;
import java.util.Optional;

import com.example.proyecto.Repository.DulceRepository;
import com.example.proyecto.model.Dulce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DulceService implements IDulceService {

    @Autowired
    private DulceRepository repoD;

    @Override
    public boolean agregarDulce(Dulce dulce) {
        repoD.save(dulce);
        return true;
    }

    @Override
    public Dulce buscarPorId(Long id) {
        Optional<Dulce> dulce = repoD.findById(id);
        if(dulce.isPresent()){
            return dulce.get();
        }
        return null;
    }

    @Override
    public Dulce buscarPorNombre(String nombre) {
        return repoD.getByNombre(nombre);
    }

    @Override
    public List<Dulce> buscarPorTipo(String tipo) {
        return repoD.getByTipo(tipo);
    }

    @Override
    public boolean editarDulce(Dulce nuevoDulce, Long id) {

        Optional<Dulce> d = repoD.findById(id);
        if(d.isPresent()){
            Dulce nuevo = d.get();
            nuevo.setNombre(nuevoDulce.getNombre());
            nuevo.setCantidad(nuevoDulce.getCantidad());
            nuevo.setCosto(nuevoDulce.getCosto());
            nuevo.setTipo(nuevoDulce.getTipo());
            nuevo.setVendido(nuevoDulce.getVendido());
            repoD.save(nuevo);
            return true;
        }else{
            return false;
        } 
        
    }

    @Override
    public boolean eliminarDulce(Long id) {

        Optional<Dulce> d = repoD.findById(id);
        if(d.isPresent()){
            repoD.delete(d.get());
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Dulce> getDulces() {
        return repoD.findAll();
    }

}
