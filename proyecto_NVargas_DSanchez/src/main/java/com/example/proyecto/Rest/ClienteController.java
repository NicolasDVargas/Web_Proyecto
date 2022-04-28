package com.example.proyecto.Rest;

import java.util.ArrayList;
import java.util.List;

import com.example.proyecto.DTOs.ClienteDTO;
import com.example.proyecto.DTOs.CompraDTO;
import com.example.proyecto.Services.IClienteService;
import com.example.proyecto.model.Cliente;
import com.example.proyecto.model.Compra;
import com.example.proyecto.model.Dulce;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Cliente")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    private ClienteDTO convertDTOs(Cliente cliente) {
        if(cliente == null) {
            return null;
        }
        ClienteDTO result = new ClienteDTO();
		ModelMapper mapper = new ModelMapper();
		
        result= mapper.map(cliente,ClienteDTO.class);

		return result;
	}

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
    public boolean Agregar(@RequestBody Cliente cliente){
        return clienteService.agregarCliente(cliente);
    }

    @PutMapping("Editar/Carrito")
    public boolean EditarCarrito(@RequestBody List<Dulce> carrito,  @RequestParam(name = "id") Long id){
        return clienteService.editarCompra(carrito, id);
    }

    @GetMapping("Buscar/id")
    public ClienteDTO buscar(@RequestParam(name = "id") Long id){
        return convertDTOs(clienteService.buscarPorId(id));
    }

    @GetMapping("Buscar/nombre&contrasenna")
    public ClienteDTO iniciarSesion(@RequestBody ObjectNode aaa){
        return  convertDTOs(clienteService.buscarPorEmailContrasenna(aaa.get("email").asText(), aaa.get("contrasenna").asText()));
    }

    @PutMapping("Editar")
    public boolean editar(@RequestBody Cliente cliente , @RequestParam(name = "id") Long id ){
    
        return clienteService.editarCliente(cliente, id);
    }

    @GetMapping("Buscar/Carrito")
    public CompraDTO getCarrito( @RequestParam(name = "id") Long id){
        Cliente c = clienteService.buscarPorId(id);
        return compraDTOs(c.getCarrito());  
    }

    @GetMapping("Buscar/facturas")
    public List<CompraDTO> getFacturaas( @RequestParam(name = "id") Long id){
        Cliente c = clienteService.buscarPorId(id);
        return comprasDTO(c.getFacturas());  
    }

@GetMapping()
public List<Cliente> getClientes(){
    return clienteService.tomarClientes();
}
}
