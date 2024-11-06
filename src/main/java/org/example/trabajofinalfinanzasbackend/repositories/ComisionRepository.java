package org.example.trabajofinalfinanzasbackend.repositories;

import org.example.trabajofinalfinanzasbackend.model.Comision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ComisionRepository extends JpaRepository<Comision, Integer> {
    @Query(value = "select *\n" +
            "from comision c\n" +
            "where c.moneda=:moneda",nativeQuery = true)
    public Comision findComisionMoneda(@Param("moneda") String moneda);
}
