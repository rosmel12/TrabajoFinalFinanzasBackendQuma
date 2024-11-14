package org.example.trabajofinalfinanzasbackend.servicesinterfaces;

import org.example.trabajofinalfinanzasbackend.model.ClienteDeudor;
import org.example.trabajofinalfinanzasbackend.repositories.ClienteDeudorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteDeudorService {

    private final ClienteDeudorRepository clienteDeudorRepository;

    private ClienteDeudorService(ClienteDeudorRepository clienteDeudorRepository) {
        this.clienteDeudorRepository = clienteDeudorRepository;
    }

    public String insertarClienteDeudor(ClienteDeudor clienteDeudor) {
        ClienteDeudor cliente =clienteDeudorRepository.findById(clienteDeudor.getRuc()).orElse(null);

        if(cliente==null) {
            clienteDeudorRepository.save(clienteDeudor);
            return "se crea el cliente  deudor";
        }
        return "no se crea el cliente deudor";
    }

    public List<ClienteDeudor> listarClienteDeudor() {
        return clienteDeudorRepository.findAll();
    }
}
