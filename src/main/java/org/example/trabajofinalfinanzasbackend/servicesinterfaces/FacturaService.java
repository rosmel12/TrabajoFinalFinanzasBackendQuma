package org.example.trabajofinalfinanzasbackend.servicesinterfaces;

import org.example.trabajofinalfinanzasbackend.dtos.FacturaDto;
import org.example.trabajofinalfinanzasbackend.model.ClienteDeudor;
import org.example.trabajofinalfinanzasbackend.model.ClienteProveedor;
import org.example.trabajofinalfinanzasbackend.model.Factura;
import org.example.trabajofinalfinanzasbackend.repositories.ClienteDeudorRepository;
import org.example.trabajofinalfinanzasbackend.repositories.ClienteProveedorRepository;
import org.example.trabajofinalfinanzasbackend.repositories.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacturaService {
    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private ClienteDeudorRepository deudorRepository;
    @Autowired
    private ClienteProveedorRepository proveedorRepository;

    public Integer insertarFactura(FacturaDto facturaDto, Factura factura) {
        ClienteProveedor clienteProveedor = proveedorRepository.findById(facturaDto.getRucClienteProveedor()).orElse(null);
        ClienteDeudor clienteDeudor = deudorRepository.findById(facturaDto.getRucClienteDeudor()).orElse(null);
        if (clienteDeudor != null && clienteProveedor != null) {
            factura.setDeudorFactura(clienteDeudor);
            factura.setProveedorFactura(clienteProveedor);
            factura.setMontoTotal(0.82*factura.getMontoTotalIgv());
            factura=facturaRepository.save(factura);
            return factura.getId();
        }
        return null;
    }

    public List<FacturaDto> listarFacturasCliente(String ruc) {
        List<Factura> facturasAux=facturaRepository.findFacturasByRuc(ruc);
        List<FacturaDto> facturas=new ArrayList<>();
        if(facturasAux!=null){
            for(Factura facturaAux:facturasAux){
                FacturaDto facturaDtoAux=new FacturaDto(facturaAux.getId(),facturaAux.getNumero(),
                        facturaAux.getMontoTotal(),facturaAux.getMontoTotalIgv(),facturaAux.getMoneda(),
                        facturaAux.getFechaEmision(),facturaAux.getFechaVencimiento(),facturaAux.getProveedorFactura().getRuc(),
                        facturaAux.getDeudorFactura().getRuc());
                facturas.add(facturaDtoAux);
            }
            return facturas;
        }
        return null;
    }

    public FacturaDto facturabyid(Integer id) {
        Factura factura = facturaRepository.findById(id).orElse(null);
        if (factura != null) {
            return new FacturaDto(factura.getId(),factura.getNumero(),factura.getMontoTotal(),factura.getMontoTotalIgv(),factura.getMoneda(),factura.getFechaEmision(),
                    factura.getFechaVencimiento(),factura.getProveedorFactura().getRuc(),factura.getDeudorFactura().getRuc());
        }
        return null;
    }

    public List<FacturaDto> listarFacturasRecientes(String ruc) {
        List<Factura> facturasAux =facturaRepository.findAllFacturasRecientes(ruc);
        List<FacturaDto> facturas=new ArrayList<>();
        if(facturasAux!=null){
            for(Factura facturaAux:facturasAux){
                FacturaDto facturaDtoAux=new FacturaDto(facturaAux.getId(),facturaAux.getNumero(),
                        facturaAux.getMontoTotal(),facturaAux.getMontoTotalIgv(),facturaAux.getMoneda(),
                        facturaAux.getFechaEmision(),facturaAux.getFechaVencimiento(),facturaAux.getProveedorFactura().getRuc(),
                        facturaAux.getDeudorFactura().getRuc());
                facturas.add(facturaDtoAux);
            }
            return facturas;
        }
        return null;
    }


}
