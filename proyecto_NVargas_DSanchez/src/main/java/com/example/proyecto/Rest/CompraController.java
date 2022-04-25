package com.example.proyecto.Rest;

import java.util.Calendar;
import java.util.List;

import com.example.proyecto.Services.ICompraService;
import com.example.proyecto.model.Compra;
import com.example.proyecto.model.Dulce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Compra")
public class CompraController  {

    @Autowired
    private ICompraService compraService;
 
     
    @PutMapping("Ingresar")
    public boolean Agregar(@RequestBody Compra compra){
        return compraService.agregarCompra(compra);
    }
 
    @PutMapping("Editar")
    public boolean Editar(@RequestBody Compra compra,  @RequestParam(name = "id") Long id){
        return compraService.editarCompra(compra, id);
    }

    @PutMapping("Editar/Carrito")
    public boolean EditarCarrito(@RequestBody List<Dulce> carrito,  @RequestParam(name = "id") Long id){
        return compraService.editarCompra(carrito, id);
    }

    @GetMapping("Buscar/id")
    public Compra BuscarId(@RequestParam(name = "id") Long id){
        return compraService.buscarPorId(id);
    }

    @GetMapping("Buscar/duenno")
    public List<Compra> BuscarDuenno(@RequestParam(name = "id") Long idCliente){
        return compraService.buscarPorDuenno(idCliente);
    }

    @GetMapping("Buscar/FechaUnica")
    public List<Compra> BuscarFecha(@RequestParam(name = "day") int day,@RequestParam(name = "month") int month,@RequestParam(name = "year") int year){
        Calendar d = Calendar.getInstance();
        d.set(year, month-1, day);
        return compraService.buscarPorFecha(d.getTime());
    }

    @GetMapping("Buscar/FechaMultiple")
    public List<Compra> BuscarFechas(@RequestParam(name = "day1") int day1,@RequestParam(name = "month1") int month1,@RequestParam(name = "year1") int year1,@RequestParam(name = "day2") int day2,@RequestParam(name = "month2") int month2,@RequestParam(name = "year2") int year2){
        Calendar d1 = Calendar.getInstance();
        Calendar d2 = Calendar.getInstance();
        d1.set(year1, month1-1, day1);
        d2.set(year2, month2-1, day2);
        return compraService.buscarEntreFechas(d1.getTime(),d2.getTime());
    }

    @GetMapping("Buscar/todos")
    public List<Compra> buscarTodos(){
        return compraService.getCompras();
    }
}
