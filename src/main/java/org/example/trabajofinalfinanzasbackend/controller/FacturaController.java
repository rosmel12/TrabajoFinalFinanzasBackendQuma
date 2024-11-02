package org.example.trabajofinalfinanzasbackend.controller;
import org.example.trabajofinalfinanzasbackend.dtos.FacturaDto;
import org.example.trabajofinalfinanzasbackend.model.Factura;
import org.example.trabajofinalfinanzasbackend.servicesinterfaces.FacturaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/quma/factura")
@CrossOrigin()
public class FacturaController {
    @Autowired
    private FacturaService facturaService;

    @PostMapping("/usuario/insertar")
    public Integer createFactura(@RequestBody FacturaDto facturaDto) throws Exception {
        try {
            ModelMapper modelMapper = new ModelMapper();
            Factura factura = modelMapper.map(facturaDto, Factura.class);
            return facturaService.insertarFactura(facturaDto, factura);
        } catch(Exception e) {
            throw new Exception("Error al insertar factura");
        }
    }
    @GetMapping("/usuario/listar/{ruc}")
    public List<FacturaDto> listarFacturasProveedor(@PathVariable String ruc){
    return facturaService.listarFacturasCliente(ruc);}

    @GetMapping("/usuario/facturaid/{id}")
    public FacturaDto facturabyid(@PathVariable Integer id)  {
        return facturaService.facturabyid(id);
    }
    @GetMapping("/usuario/facturarecientes/{ruc}")
    public List<FacturaDto> facturaRecientes(@PathVariable String ruc)  {
        return facturaService.listarFacturasRecientes(ruc);
    }
}
