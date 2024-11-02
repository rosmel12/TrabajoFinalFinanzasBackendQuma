package org.example.trabajofinalfinanzasbackend.repositories;

import org.example.trabajofinalfinanzasbackend.model.Descuento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DescuentoRepository extends JpaRepository<Descuento, Integer> {
    @Query(value = "select d.*\n" +
            "\tfrom descuento d\n" +
            "    join operacionfactoring op on op.id_descuento=d.id\n" +
            "    join factura f on op.id_factura=f.id\n" +
            "    where f.ruc_cliente_proveedor=:ruc;",nativeQuery = true)
    List<Descuento> findByDescuento(@Param("ruc") String ruc);
}
