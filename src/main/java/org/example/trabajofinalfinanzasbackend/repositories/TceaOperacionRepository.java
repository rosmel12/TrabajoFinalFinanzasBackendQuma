package org.example.trabajofinalfinanzasbackend.repositories;

import org.example.trabajofinalfinanzasbackend.model.TceaOperacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TceaOperacionRepository extends JpaRepository<TceaOperacion, Integer> {
    @Query(value = "select tcea.*\n" +
            "from factura fc\n" +
            "join operacionfactoring ofc on ofc.id_factura=fc.id\n" +
            "join tceaoperacion tcea on tcea.id_operacion_factoring=ofc.id\n" +
            "where fc.ruc_cliente_proveedor=:ruc", nativeQuery = true)
    List<TceaOperacion> findAllByRucClienteProveedor(@Param("ruc") String ruc);

    @Query(value="select tc.*\n" +
            "from tceaoperacion tc\n" +
            "where tc.id_operacion_factoring=:id",nativeQuery = true)
    TceaOperacion findbyIdOperacionFactoring(@Param("id") Integer id);
}
