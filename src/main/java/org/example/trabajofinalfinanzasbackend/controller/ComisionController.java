package org.example.trabajofinalfinanzasbackend.controller;

import org.example.trabajofinalfinanzasbackend.dtos.ComisionDto;
import org.example.trabajofinalfinanzasbackend.model.Comision;
import org.example.trabajofinalfinanzasbackend.servicesinterfaces.ComisionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quma/comision")
@CrossOrigin()
public class ComisionController {

    @Autowired
    private ComisionService comisionService;

    @PostMapping("/insertar")
    public String createComision(@RequestBody ComisionDto comisionDto) throws Exception {
        try {
            ModelMapper modelMapper = new ModelMapper();
            Comision comision = modelMapper.map(comisionDto, Comision.class);
            return comisionService.insertComision(comision);
        } catch(Exception e) {
            throw new Exception("Error al insertar comision");
        }

    }

    @GetMapping("/usuario/id/{moneda}")
    public Integer comisionId(@PathVariable String moneda){
        return comisionService.comisionId(moneda);
    }
}
