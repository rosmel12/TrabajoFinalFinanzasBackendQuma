package org.example.trabajofinalfinanzasbackend.repositories;

import org.example.trabajofinalfinanzasbackend.model.CarteraTcea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarteraTceaRepository extends JpaRepository<CarteraTcea, Integer> {

    @Query(value = "select fc.fecha_vencimiento,fc.monto_total,ofc.fecha_operacion,ofc.monto_pago\n" +
            "from factura fc\n" +
            "join operacionfactoring ofc on ofc.id_factura=fc.id\n" +
            "where fc.ruc_cliente_proveedor=:ruc AND DATE(ofc.fecha_operacion) = CURDATE()", nativeQuery = true)
    List<Object[]> flujos(@Param("ruc")String ruc);

    @Query(value = "select ct.*\n" +
            "from carteratcea ct\n" +
            "where ct.ruc_cliente=:ruc and date(ct.fecha)=CURDATE()",nativeQuery = true)
     CarteraTcea carteraTceaDia(@Param("ruc")String ruc);

    @Query(value = "select *\n" +
            "from carteratcea ct\n" +
            "where ct.ruc_cliente=:ruc\n"+
            "order by ct.id desc",nativeQuery = true)
    List<CarteraTcea> findByRuc(@Param("ruc")String ruc);

}
