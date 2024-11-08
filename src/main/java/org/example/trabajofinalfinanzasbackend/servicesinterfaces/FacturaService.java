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

import java.math.BigDecimal;
import java.math.RoundingMode;
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
            double valorNominal=factura.getMontoTotalIgv()/1.18;
            BigDecimal valorNominalRedondeada = BigDecimal.valueOf(valorNominal).setScale(2, RoundingMode.HALF_UP);
            factura.setMontoTotal(valorNominalRedondeada.doubleValue());
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
                FacturaDto facturaDtoAux=new FacturaDto();
                facturaDtoAux.setId(facturaAux.getId());
                facturaDtoAux.setNumero(facturaAux.getNumero());
                facturaDtoAux.setMontoTotal(facturaAux.getMontoTotal());
                facturaDtoAux.setMontoTotalIgv(facturaAux.getMontoTotalIgv());
                facturaDtoAux.setMoneda(facturaAux.getMoneda());
                facturaDtoAux.setFechaEmision(facturaAux.getFechaEmision());
                facturaDtoAux.setFechaVencimiento(facturaAux.getFechaVencimiento());
                facturaDtoAux.setRucClienteProveedor(facturaAux.getProveedorFactura().getRuc());
                facturaDtoAux.setRucClienteDeudor(facturaAux.getDeudorFactura().getRuc());
                facturas.add(facturaDtoAux);
            }
            return facturas;
        }
        return null;
    }

    public FacturaDto facturabyid(Integer id) {
        Factura facturaAux = facturaRepository.findById(id).orElse(null);
        if (facturaAux != null) {
            FacturaDto facturaDtoAux =new FacturaDto();
            facturaDtoAux.setId(facturaAux.getId());
            facturaDtoAux.setNumero(facturaAux.getNumero());
            facturaDtoAux.setMontoTotal(facturaAux.getMontoTotal());
            facturaDtoAux.setMontoTotalIgv(facturaAux.getMontoTotalIgv());
            facturaDtoAux.setMoneda(facturaAux.getMoneda());
            facturaDtoAux.setFechaEmision(facturaAux.getFechaEmision());
            facturaDtoAux.setFechaVencimiento(facturaAux.getFechaVencimiento());
            facturaDtoAux.setRucClienteProveedor(facturaAux.getProveedorFactura().getRuc());
            facturaDtoAux.setRucClienteDeudor(facturaAux.getDeudorFactura().getRuc());
            return facturaDtoAux;
        }
        return null;
    }

    public List<FacturaDto> listarFacturasRecientes(String ruc) {
        List<Factura> facturasAux =facturaRepository.findAllFacturasRecientes(ruc);
        List<FacturaDto> facturas=new ArrayList<>();
        if(facturasAux!=null){
            for(Factura facturaAux:facturasAux){
                FacturaDto facturaDtoAux=new FacturaDto();
                facturaDtoAux.setId(facturaAux.getId());
                facturaDtoAux.setNumero(facturaAux.getNumero());
                facturaDtoAux.setMontoTotal(facturaAux.getMontoTotal());
                facturaDtoAux.setMontoTotalIgv(facturaAux.getMontoTotalIgv());
                facturaDtoAux.setMoneda(facturaAux.getMoneda());
                facturaDtoAux.setFechaEmision(facturaAux.getFechaEmision());
                facturaDtoAux.setFechaVencimiento(facturaAux.getFechaVencimiento());
                facturaDtoAux.setRucClienteProveedor(facturaAux.getProveedorFactura().getRuc());
                facturaDtoAux.setRucClienteDeudor(facturaAux.getDeudorFactura().getRuc());
                facturas.add(facturaDtoAux);
            }
            return facturas;
        }
        return null;
    }


}
