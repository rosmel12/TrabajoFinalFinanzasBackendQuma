package org.example.trabajofinalfinanzasbackend.controller;

import org.example.trabajofinalfinanzasbackend.dtos.ClienteProveedorDto;
import org.example.trabajofinalfinanzasbackend.model.ClienteProveedor;
import org.example.trabajofinalfinanzasbackend.servicesinterfaces.ClienteProveedorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quma/clienteproveedor")
@CrossOrigin()
public class ClienteProveedorController {
    @Autowired
    private ClienteProveedorService clienteProveedorService;

    @PostMapping("/usuario/insertar")
    public String createClienteProveeor(@RequestBody ClienteProveedorDto clienteProveedorDto) throws Exception {
        try {
            ModelMapper modelMapper = new ModelMapper();
            ClienteProveedor clienteProveedor  = modelMapper.map(clienteProveedorDto, ClienteProveedor.class);
            return clienteProveedorService.insertarCliente(clienteProveedorDto, clienteProveedor);
        } catch(Exception e) {
            throw new Exception("Error al insertar cliente");
        }

    }

    @GetMapping("/usuario/cliente/{username}")
    public ClienteProveedorDto listarCliente(@PathVariable String username){
        ModelMapper modelMapper = new ModelMapper();
        ClienteProveedor clienteProveedor=clienteProveedorService.clientePorUsuario(username);
        return modelMapper.map(clienteProveedor, ClienteProveedorDto.class);
    }
}
