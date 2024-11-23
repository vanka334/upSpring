package com.example.itog2.controllers;



import com.example.itog2.models.TypePayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/typePayments")
public class TypePaymentController {

    private final RestTemplate restTemplate;

    @Autowired
    public TypePaymentController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static final String API_BASE_URL = "http://localhost:8080/v1/api/typePayment";

    /**
     * Получение всех типов платежей через API.
     */
    @GetMapping
    public String getAllTypePayments(Model model) {
        TypePayment[] payments = restTemplate.getForObject(API_BASE_URL, TypePayment[].class);
        List<TypePayment> typePayments = Arrays.asList(payments);
        model.addAttribute("typePayments", typePayments);
        return "typePayment";
    }

    /**
     * Добавление нового типа платежа через API.
     */
    @PostMapping("/add")
    public String addTypePayment(
            @RequestParam String name,
            @RequestParam(required = false) String description) {

        TypePayment typePayment = new TypePayment();
        typePayment.setName(name);
        typePayment.setDescription(description);

        restTemplate.postForObject(API_BASE_URL + "/add", typePayment, TypePayment.class);
        return "redirect:/typePayments";
    }

    /**
     * Обновление типа платежа через API.
     */
    @PostMapping("/update")
    public String updateTypePayment(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam(required = false) String description) {

        TypePayment typePayment = new TypePayment();
        typePayment.setId(id);
        typePayment.setName(name);
        typePayment.setDescription(description);

        restTemplate.put(API_BASE_URL + "/update/" + id, typePayment);
        return "redirect:/typePayments";
    }

    /**
     * Удаление типа платежа через API.
     */
    @PostMapping("/delete")
    public String deleteTypePayment(@RequestParam Long id) {
        restTemplate.delete(API_BASE_URL + "/delete/" + id);
        return "redirect:/typePayments";
    }
}

