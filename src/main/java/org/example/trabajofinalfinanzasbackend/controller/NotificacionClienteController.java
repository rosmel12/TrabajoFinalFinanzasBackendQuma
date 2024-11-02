package org.example.trabajofinalfinanzasbackend.controller;

import org.example.trabajofinalfinanzasbackend.dtos.NotificacionClienteDto;
import org.example.trabajofinalfinanzasbackend.model.NotificacionCliente;
import org.example.trabajofinalfinanzasbackend.servicesinterfaces.NotificacionClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/quma/notificacion")
@CrossOrigin()
public class NotificacionClienteController {
    @Autowired
    private NotificacionClienteService notificacionClienteService;

    @GetMapping("/usuario/listar/{ruc}")
    public List<NotificacionClienteDto> listarNotificacionCliente(@PathVariable String ruc) {
        ModelMapper modelMapper = new ModelMapper();
        List<NotificacionCliente> notificacionCliente = notificacionClienteService.listarNotificacionCliente(ruc);
        return Arrays.asList(modelMapper.map(notificacionCliente, NotificacionClienteDto[].class));
    }
    @PutMapping("/usuario/modificar/{id}")
    public String modificarestado(@PathVariable Integer id) {
        return notificacionClienteService.modificarEstadoNotificacionCliente(id);
    }

}
