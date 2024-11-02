package org.example.trabajofinalfinanzasbackend.servicesinterfaces;

import org.example.trabajofinalfinanzasbackend.dtos.ClienteProveedorDto;
import org.example.trabajofinalfinanzasbackend.model.ClienteProveedor;
import org.example.trabajofinalfinanzasbackend.model.Usuario;
import org.example.trabajofinalfinanzasbackend.repositories.ClienteProveedorRepository;
import org.example.trabajofinalfinanzasbackend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteProveedorService {
    @Autowired
    private ClienteProveedorRepository clienteProveedorRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public String insertarCliente(ClienteProveedorDto clienteProveedorDto, ClienteProveedor clienteProveedor) {
        Usuario usuario = usuarioRepository.findById(clienteProveedorDto.getIdUsuario()).orElse(null);
        if (usuario != null) {
             clienteProveedor.setUserCliente(usuario);
             clienteProveedorRepository.save(clienteProveedor);
            return "se agrego con exito";
        }
        return "existe un cliente con ese usuario";
    }

    public ClienteProveedor clientePorUsuario(String username) {
        return clienteProveedorRepository.findClienteProveedorByUserCliente_Username(username);
    }



}
