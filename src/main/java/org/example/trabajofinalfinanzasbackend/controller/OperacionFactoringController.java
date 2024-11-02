package org.example.trabajofinalfinanzasbackend.controller;
import org.example.trabajofinalfinanzasbackend.dtos.OperacionFactoringDto;
import org.example.trabajofinalfinanzasbackend.dtos.OperacionFactoringInsertarDto;
import org.example.trabajofinalfinanzasbackend.model.OperacionFactoring;
import org.example.trabajofinalfinanzasbackend.servicesinterfaces.OperacionFactoringService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/quma/operacionfactoring")
@CrossOrigin()
public class OperacionFactoringController {
    @Autowired
    private OperacionFactoringService operacionFactoringService;

    @PostMapping("/usuario/insertar")
    public Integer insertarOperacion(@RequestBody OperacionFactoringInsertarDto operacionFactoringInsertarDto) {
        return  operacionFactoringService.insertarOperacion(operacionFactoringInsertarDto);
    }

    @GetMapping("/usuario/facturaId/{id}")
    public OperacionFactoringDto listarOperacionFactura(@PathVariable Integer id) {
     ModelMapper modelMapper = new ModelMapper();
     OperacionFactoring operacionFactoring=operacionFactoringService.listaroperacionPorFactura(id);
     return modelMapper.map(operacionFactoring, OperacionFactoringDto.class);
    }

    @GetMapping("/usuario/operacionesusuario/{ruc}")
    public List<OperacionFactoringDto> listarOperacionesUsuario(@PathVariable String ruc){
        ModelMapper modelMapper = new ModelMapper();
        List<OperacionFactoring> operacionFactorings=operacionFactoringService.listaroperacionPorCliente(ruc);
        return Arrays.asList(modelMapper.map(operacionFactorings, OperacionFactoringDto[].class));
    }

}
