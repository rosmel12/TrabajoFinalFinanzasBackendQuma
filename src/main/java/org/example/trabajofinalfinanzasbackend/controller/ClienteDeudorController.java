package org.example.trabajofinalfinanzasbackend.controller;

import org.example.trabajofinalfinanzasbackend.dtos.ClienteDeudorDto;
import org.example.trabajofinalfinanzasbackend.model.ClienteDeudor;
import org.example.trabajofinalfinanzasbackend.servicesinterfaces.ClienteDeudorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/quma/clientedeudor")
@CrossOrigin()
public class ClienteDeudorController {
    @Autowired
    private ClienteDeudorService clienteDeudorService;

    @PostMapping("/usuario/insertar")
    public String createClienteDeudor(@RequestBody ClienteDeudorDto clienteDeudorDto) throws Exception {
        try {
            ModelMapper modelMapper = new ModelMapper();
            ClienteDeudor cliente = modelMapper.map(clienteDeudorDto, ClienteDeudor.class);
            return clienteDeudorService.insertarClienteDeudor(cliente);
        } catch(Exception e) {
            throw new Exception("Error al insertar cliente");
        }
    }

    @GetMapping("/usuario/listar")
    public List<ClienteDeudorDto> listarClienteDeudor(){
        ModelMapper modelMapper = new ModelMapper();
        List<ClienteDeudor> clienteDeudors=clienteDeudorService.listarClienteDeudor();
        return Arrays.asList(modelMapper.map(clienteDeudors, ClienteDeudorDto[].class));
    }

}
