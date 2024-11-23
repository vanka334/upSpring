package com.example.itog2.controllers;



import com.example.itog2.models.InsuranceProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/insuranceProducts")
public class InsuranceProductController {

    private final RestTemplate restTemplate;

    @Autowired
    public InsuranceProductController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static final String API_BASE_URL = "http://localhost:8080/v1/api/insuranceProduct";

    /**
     * Получение всех страховых продуктов через API.
     */
    @GetMapping
    public String getAllInsuranceProducts(Model model) {
        InsuranceProduct[] products = restTemplate.getForObject(API_BASE_URL, InsuranceProduct[].class);

        model.addAttribute("insuranceProducts", products);
        return "InsuranceProduct";
    }

    /**
     * Добавление нового страхового продукта через API.
     */
    @PostMapping("/add")
    public String addInsuranceProduct(
            @RequestParam String name,
            @RequestParam(required = false) String description,
            @RequestParam LocalDateTime validityPeriod,
            @RequestParam BigDecimal cost) {

        InsuranceProduct product = new InsuranceProduct();
        product.setName(name);
        product.setDescription(description);
        product.setValidityPeriod(validityPeriod);
        product.setCost(cost);

        restTemplate.postForObject(API_BASE_URL + "/add", product, InsuranceProduct.class);
        return "redirect:/insuranceProducts";
    }

    /**
     * Обновление страхового продукта через API.
     */
    @PostMapping("/update")
    public String updateInsuranceProduct(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam(required = false) String description,
            @RequestParam LocalDateTime validityPeriod,
            @RequestParam BigDecimal cost) {

        InsuranceProduct product = new InsuranceProduct();
        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setValidityPeriod(validityPeriod);
        product.setCost(cost);

        restTemplate.put(API_BASE_URL + "/update/" + id, product);
        return "redirect:/insuranceProducts";
    }

    /**
     * Удаление страхового продукта через API.
     */
    @PostMapping("/delete")
    public String deleteInsuranceProduct(@RequestParam Long id) {
        restTemplate.delete(API_BASE_URL + "/delete/" + id);
        return "redirect:/insuranceProducts";
    }
}
