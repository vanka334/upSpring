package com.example.itog2.controllers;

import com.example.itog2.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
@Controller
@RequestMapping("/clients")
public class ClientController {

    private final RestTemplate restTemplate;
    private final String apiUrl = "http://localhost:8080/v1/api/client";

    @Autowired
    public ClientController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String getClientsPage(Model model) {
        // Получение списка клиентов через API
        Client[] clientsArray = restTemplate.getForObject(apiUrl, Client[].class);
        List<Client> clients = Arrays.asList(clientsArray);
        model.addAttribute("clients", clients);
        return "clients";
    }

    @PostMapping("/add")
    public String addClient(
            @RequestParam String firstName,
            @RequestParam String surname,
            @RequestParam(required = false) String secondName,
            @RequestParam(required = false) String passportNumber,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String birthday,
            @RequestParam String email,
            @RequestParam String phoneNumber,
            @RequestParam(required = false) String address
    ) {
        // Создание нового клиента через API
        Client newClient = new Client();
        newClient.setFirstName(firstName);
        newClient.setSurname(surname);
        newClient.setSecondName(secondName);
        newClient.setPassportNumber(passportNumber);
        newClient.setCountry(country);
        if (birthday != null && !birthday.isEmpty()) {
            newClient.setBirthday(LocalDateTime.parse(birthday));
        }
        newClient.setEmail(email);
        newClient.setPhoneNumber(phoneNumber);
        newClient.setAddress(address);
        restTemplate.postForObject(apiUrl + "/add", newClient, Client.class);
        return "redirect:/clients";
    }

    @PostMapping("/update")
    public String updateClient(
            @RequestParam Long id,
            @RequestParam String firstName,
            @RequestParam String surname,
            @RequestParam(required = false) String secondName,
            @RequestParam(required = false) String passportNumber,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String birthday,
            @RequestParam String email,
            @RequestParam String phoneNumber,
            @RequestParam(required = false) String address
    ) {
        // Обновление клиента через API
        Client updatedClient = new Client();
        updatedClient.setId(id);
        updatedClient.setFirstName(firstName);
        updatedClient.setSurname(surname);
        updatedClient.setSecondName(secondName);
        updatedClient.setPassportNumber(passportNumber);
        updatedClient.setCountry(country);
        if (birthday != null && !birthday.isEmpty()) {
            updatedClient.setBirthday(LocalDateTime.parse(birthday));
        }
        updatedClient.setEmail(email);
        updatedClient.setPhoneNumber(phoneNumber);
        updatedClient.setAddress(address);
        restTemplate.put(apiUrl + "/update/" + id, updatedClient);
        return "redirect:/clients";
    }

    @PostMapping("/delete")
    public String deleteClient(@RequestParam Long id) {
        // Удаление клиента через API
        restTemplate.delete(apiUrl + "/delete/" + id);
        return "redirect:/clients";
    }
}

