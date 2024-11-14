package org.example.trabajofinalfinanzasbackend.servicesinterfaces;

import org.example.trabajofinalfinanzasbackend.dtos.ComisionDto;
import org.example.trabajofinalfinanzasbackend.model.Comision;
import org.example.trabajofinalfinanzasbackend.repositories.ComisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComisionService {
@Autowired
private ComisionRepository comisionRepository;

public String insertComision(Comision comision) {
     comisionRepository.save(comision);
    return " se agrego la comision";
}
public Integer comisionId(String moneda){
return comisionRepository.findComisionMoneda(moneda).getId();
}
public ComisionDto comisionMoneda(String moneda){
    Comision comision = comisionRepository.findComisionMoneda(moneda);
    ComisionDto comisionDto = new ComisionDto();
    comisionDto.setId(comision.getId());
    comisionDto.setEstudioRiesgo(comision.getEstudioRiesgo());
    comisionDto.setSeguroDesgravamen(comision.getSeguroDesgravamen());
    comisionDto.setFotoCopias(comision.getFotoCopias());
    comisionDto.setGastoAdministracion(comision.getGastoAdministracion());
    comisionDto.setPorte(comision.getPorte());
    comisionDto.setMoneda(comision.getMoneda());
    return comisionDto ;
}

}
