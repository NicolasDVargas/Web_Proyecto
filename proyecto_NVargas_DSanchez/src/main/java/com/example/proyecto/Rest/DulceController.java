package com.example.proyecto.Rest;

import java.util.List;

import com.example.proyecto.Services.IDulceService;
import com.example.proyecto.model.Dulce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Dulce")
 class DulceController {
    
    @Autowired
    private IDulceService dulceService;

    @PutMapping("Ingresar")
    public boolean Agregar(@RequestBody Dulce dulce){
        return dulceService.agregarDulce(dulce);
    }

    @DeleteMapping("eliminar/id")
    public boolean eliminarDulce(@RequestParam Long id){
        return dulceService.eliminarDulce(id);
    }

    @PutMapping("editar")
    public boolean editarDulce(@RequestBody Dulce dulce, @RequestParam(name = "id") Long id){
        return dulceService.editarDulce(dulce, id);
    }

    @GetMapping("Nombre")
    public Dulce getByNombre(@RequestParam(name = "nombre") String nombre){
        return dulceService.buscarPorNombre(nombre);
    }

    @GetMapping("id")
    public Dulce getById(@RequestParam(name = "id") Long id){
        return dulceService.buscarPorId(id);
    }

    @GetMapping("tipo")
    public List<Dulce> getById(@RequestParam(name = "tipo") String tipo){
        return dulceService.buscarPorTipo(tipo);
    }


    @GetMapping()
    public List<Dulce> getAll(){
        return dulceService.getDulces();
    }

   
}
