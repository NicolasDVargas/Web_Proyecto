package com.example.proyecto.Rest;

import java.util.List;

import com.example.proyecto.DTOs.ClienteDTO;
import com.example.proyecto.Services.IClienteService;
import com.example.proyecto.model.Cliente;
import com.example.proyecto.model.Compra;

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
        ClienteDTO result = new ClienteDTO();
		ModelMapper mapper = new ModelMapper();
		
        result= mapper.map(cliente,ClienteDTO.class);

		return result;
	}

    @PutMapping("Ingresar")
    public boolean Agregar(@RequestBody Cliente cliente){
        return clienteService.agregarCliente(cliente);
    }

    @GetMapping("Buscar/id")
    public ClienteDTO buscar(@RequestParam(name = "id") Long id){
        return convertDTOs(clienteService.buscarPorId(id));
    }

    @GetMapping("Buscar/nombre&contrasenna")
    public ClienteDTO iniciarSesion(@RequestBody String email, String contrasenna){
        return  convertDTOs(clienteService.buscarPorEmailContrasenna(email, contrasenna));
    }

    @PutMapping("Editar")
    public boolean editar(@RequestBody Cliente cliente , @RequestParam(name = "id") Long id ){
    
        return clienteService.editarCliente(cliente, id);
    }

    @GetMapping("Buscar/Carrito")
    public Compra getCarrito( @RequestParam(name = "id") Long id){
        Cliente c = clienteService.buscarPorId(id);
        return c.getCarrito();  
    }

    @GetMapping("Buscar/facturas")
    public List<Compra> getFacturaas( @RequestParam(name = "id") Long id){
        Cliente c = clienteService.buscarPorId(id);
        return c.getFacturas();  
    }


}
