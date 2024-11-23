package com.example.itog2.api;

import com.example.itog2.models.Client;
import com.example.itog2.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/v1/api/client")
public class ClientApi {
    @Autowired
    private final ClientService clientService;

    public ClientApi(ClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping
    public List<Client> getAllClientes() {
        return clientService.getAllClientes();
    }


    @GetMapping("/{id}")
    public Client getClient(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PostMapping("/add")
    public Client addClient(@RequestBody Client client) {
        return clientService.saveClient(client);
    }
    @PutMapping("/update/{id}")
    public Client updateClient(@RequestBody Client client) {
        return clientService.updateClient(client);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
