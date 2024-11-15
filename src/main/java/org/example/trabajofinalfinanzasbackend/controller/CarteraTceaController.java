package org.example.trabajofinalfinanzasbackend.controller;

import org.example.trabajofinalfinanzasbackend.dtos.CarteraTceaDto;
import org.example.trabajofinalfinanzasbackend.servicesinterfaces.CarteraTceaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/quma/carteratcea")
@CrossOrigin()
public class CarteraTceaController {
    @Autowired
    private CarteraTceaService carteraTceaService;
  @PostMapping("/usuario/insertar/{ruc}/{moneda}")
  public String Registar(@PathVariable String ruc, @PathVariable String moneda) {

  return carteraTceaService.insertarCarteraTcea(ruc,moneda);
  }

  @GetMapping("/usuario/listar/{ruc}")
    public List<CarteraTceaDto> listar(@PathVariable String ruc) {
      return carteraTceaService.getCarteraTcea(ruc);
  }

}
