package com.example.proyecto.Rest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.example.proyecto.DTOs.CompraDTO;
import com.example.proyecto.Services.ICompraService;
import com.example.proyecto.model.Compra;
import com.example.proyecto.model.Dulce;

import org.modelmapper.ModelMapper;
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
 
    private CompraDTO compraDTOs(Compra compra) {
        if(compra == null) {
            return null;
        }
        CompraDTO result = new CompraDTO();
		ModelMapper mapper = new ModelMapper();
		
        result= mapper.map(compra,CompraDTO.class);

		return result;
	}

    private List<CompraDTO> comprasDTO(List<Compra> compras){
        List<CompraDTO> result = new ArrayList<>();
		ModelMapper mapper = new ModelMapper();
		
		for (Compra compra : compras) {
			result.add(mapper.map(compra, CompraDTO.class));
		}

        return result;
    }
     
    @PutMapping("Ingresar")
    public boolean Agregar(@RequestBody Compra compra, @RequestParam(name = "id") Long id){
        return compraService.agregarCompra(compra, id);
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
    public CompraDTO BuscarId(@RequestParam(name = "id") Long id){
        return compraDTOs(compraService.buscarPorId(id));
    }

    @GetMapping("Buscar/duenno")
    public List<CompraDTO> BuscarDuenno(@RequestParam(name = "id") Long idCliente){
        return comprasDTO(compraService.buscarPorDuenno(idCliente));
    }

    @GetMapping("Buscar/FechaUnica")
    public List<CompraDTO> BuscarFecha(@RequestParam(name = "day") int day,@RequestParam(name = "month") int month,@RequestParam(name = "year") int year){
        Calendar d = Calendar.getInstance();
        d.set(year, month-1, day);
        return comprasDTO(compraService.buscarPorFecha(d.getTime()));
    }

    @GetMapping("Buscar/FechaMultiple")
    public List<CompraDTO> BuscarFechas(@RequestParam(name = "day1") int day1,@RequestParam(name = "month1") int month1,@RequestParam(name = "year1") int year1,@RequestParam(name = "day2") int day2,@RequestParam(name = "month2") int month2,@RequestParam(name = "year2") int year2){
        Calendar d1 = Calendar.getInstance();
        Calendar d2 = Calendar.getInstance();
        d1.set(year1, month1-1, day1);
        d2.set(year2, month2-1, day2);
        return comprasDTO(compraService.buscarEntreFechas(d1.getTime(),d2.getTime()));
    }

    @GetMapping("Buscar/todos")
    public List<CompraDTO> buscarTodos(){
        return comprasDTO(compraService.getCompras());
    }
}
