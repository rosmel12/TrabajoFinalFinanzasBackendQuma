package org.example.trabajofinalfinanzasbackend.repositories;

import org.example.trabajofinalfinanzasbackend.model.CarteraTcea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarteraTceaRepository extends JpaRepository<CarteraTcea, Integer> {

    @Query(value = "select ct.*\n" +
            "from carteratcea ct\n" +
            "where ct.ruc_cliente=:ruc\n" +
            "and ct.moneda=:moneda",nativeQuery = true)
     List<CarteraTcea> carteraTceaDia(@Param("ruc")String ruc, @Param("moneda") String moneda);

    @Query(value = "select ct.*\n" +
            "from carteratcea ct\n" +
            "where ct.ruc_cliente=:ruc\n"+
            "order by ct.id desc",nativeQuery = true)
    List<CarteraTcea> findByRuc(@Param("ruc")String ruc);

}
