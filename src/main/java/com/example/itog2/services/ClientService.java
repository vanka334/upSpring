package com.example.itog2.services;

import com.example.itog2.models.Client;
import com.example.itog2.models.Client;
import com.example.itog2.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    public List<Client> getAllClientes() {
        return clientRepository.findAll();

    }
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);


    }
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }
    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
