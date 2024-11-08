package org.example.trabajofinalfinanzasbackend.controller;

import org.example.trabajofinalfinanzasbackend.dtos.CarteraTceaDto;
import org.example.trabajofinalfinanzasbackend.model.CarteraTcea;
import org.example.trabajofinalfinanzasbackend.servicesinterfaces.CarteraTceaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/quma/carteratcea")
@CrossOrigin()
public class CarteraTceaController {
    @Autowired
    private CarteraTceaService carteraTceaService;

  @PostMapping("/usuario/insertar/{ruc}")
    public String insertar(@PathVariable String ruc) {
      return carteraTceaService.insertarCarteraTcea(ruc);
  }

  @GetMapping("/usuario/listar/{ruc}")
    public List<CarteraTceaDto> listar(@PathVariable String ruc) {
      return carteraTceaService.getCarteraTcea(ruc);
  }

}
