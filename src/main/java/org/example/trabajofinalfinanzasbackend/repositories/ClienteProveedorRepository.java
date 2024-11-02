package org.example.trabajofinalfinanzasbackend.repositories;

import org.example.trabajofinalfinanzasbackend.model.ClienteProveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteProveedorRepository extends JpaRepository<ClienteProveedor, String> {
   ClienteProveedor findClienteProveedorByUserCliente_Username(String username);
}
