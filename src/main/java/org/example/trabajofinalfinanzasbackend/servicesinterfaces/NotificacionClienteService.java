package org.example.trabajofinalfinanzasbackend.servicesinterfaces;

import org.example.trabajofinalfinanzasbackend.model.NotificacionCliente;
import org.example.trabajofinalfinanzasbackend.model.OperacionFactoring;
import org.example.trabajofinalfinanzasbackend.repositories.NotificacionClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacionClienteService {
@Autowired
private NotificacionClienteRepository notificacionClienteRepository;

public void enviarNotificacionCliente(OperacionFactoring operacionFactoring) {
 NotificacionCliente notificacionCliente = new NotificacionCliente();
 notificacionCliente.setMensaje("Estimado cliente su operacion " +
         "fue realizada correspondiente a la factura con Numero " +operacionFactoring.getFacturaOperacion().getNumero());
 notificacionCliente.setLeido(false);
 notificacionCliente.setNotificacionOperacionFactoring(operacionFactoring);
 notificacionClienteRepository.save(notificacionCliente);
    }
public String modificarEstadoNotificacionCliente( Integer id) {
  NotificacionCliente notificacionCliente = notificacionClienteRepository.findById(id).orElse(null);
  if(notificacionCliente != null) {
      notificacionCliente.setLeido(true);
      notificacionClienteRepository.save(notificacionCliente);
      return "mensaje modificado";
  }
  return "mensaje no encontrado";
}

public List<NotificacionCliente> listarNotificacionCliente(String ruc) {
    return notificacionClienteRepository.listarNotificacionCliente(ruc);
}

}
