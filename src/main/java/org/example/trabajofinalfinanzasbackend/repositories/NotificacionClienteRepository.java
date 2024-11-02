package org.example.trabajofinalfinanzasbackend.repositories;

import org.example.trabajofinalfinanzasbackend.model.NotificacionCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionClienteRepository extends JpaRepository<NotificacionCliente, Integer> {
@Query(value = "select nc.*\n" +
        "from factura fc\n" +
        "join operacionfactoring ofc on ofc.id_factura=fc.id\n" +
        "join notificacioncliente nc on nc.id_operacion_factoring=ofc.id\n" +
        "where fc.ruc_cliente_proveedor=:ruc",nativeQuery = true)
List<NotificacionCliente> listarNotificacionCliente(@Param("ruc") String ruc);

}
