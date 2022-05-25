package com.example.proyecto.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.example.proyecto.Repository.CompraRepository;
import com.example.proyecto.model.Cliente;
import com.example.proyecto.model.Compra;
import com.example.proyecto.model.Dulce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraService implements ICompraService {

    @Autowired
    private CompraRepository repoC;

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IDulceService dulceService;

    @Override
    public boolean agregarCompra(Compra dulces, Long id) {
        Cliente cliente = clienteService.buscarPorId(id);
        if(cliente!=null) {
            clienteService.editarCliente(cliente, id);  
            Compra compra1 = new Compra(cliente, dulces.getDulces());
            repoC.save(compra1);
            if(cliente.getFacturas().size()==0){
                List<Compra> facturas = new ArrayList<>();
                facturas.add(compra1);
                cliente.setFacturas(facturas);
            }
            cliente.getFacturas().add(compra1);
            clienteService.editarCliente(cliente, id);  
            return  true;
        }
        return false;
    } 

    @Override
    public List<Compra> buscarPorDuenno(Long clienteID) {
        return  repoC.getByIdPropietario(clienteID);
    }

    @Override
    public List<Compra> buscarPorFecha(String date) {
        
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
            Compra nuevaCompra = c.get();
            nuevaCompra.setPedido(compra.getPedido());
            nuevaCompra.setPropietario(compra.getPropietario());
            nuevaCompra.setFechaCompra(compra.getFechaCompra());
            nuevaCompra.setTotal(compra.getTotal());
            nuevaCompra.setfecha();
            repoC.save(nuevaCompra);
            return true;
        }
        return false;
    }

    @Override
    public List<Compra> getCompras() {
        return repoC.findAll();
    }

    

}
