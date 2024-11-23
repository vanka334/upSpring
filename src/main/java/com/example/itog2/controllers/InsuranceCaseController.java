package com.example.itog2.controllers;


import com.example.itog2.models.InsuranceCase;
import com.example.itog2.models.InsurancePolicy;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/insuranceCase")
public class InsuranceCaseController {

    private final RestTemplate restTemplate;
    private final String apiUrl = "http://localhost:8080/v1/api/insuranceCase";

    @Autowired
    public InsuranceCaseController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Получение списка страховых случаев
    @GetMapping
    public String getInsuranceCasesPage(Model model) {
        InsuranceCase[] insuranceCasesArray = restTemplate.getForObject(apiUrl, InsuranceCase[].class);
        model.addAttribute("insuranceCases", insuranceCasesArray);
        return "InsuranceCase";
    }

    // Добавление нового страхового случая
    @PostMapping("/add")
    public String addInsuranceCase(
            @RequestParam @NotNull LocalDateTime issueDate,
            @RequestParam String description,
            @RequestParam @NotBlank @Size(max = 15) String status,
            @RequestParam Long insurancePolicyId // ID страхового полиса
    ) {
        InsurancePolicy insurancePolicy = new InsurancePolicy();
        insurancePolicy.setId(insurancePolicyId); // Присваиваем ID страхового полиса

        // Создание нового страхового случая
        InsuranceCase newInsuranceCase = new InsuranceCase();
        newInsuranceCase.setIssueDate(issueDate);
        newInsuranceCase.setDescription(description);
        newInsuranceCase.setStatus(status);
        newInsuranceCase.setInsurancePolicy(insurancePolicy); // Присоединение страхового полиса

        // Отправляем запрос на добавление страхового случая
        restTemplate.postForObject(apiUrl + "/add", newInsuranceCase, InsuranceCase.class);

        return "redirect:/InsuranceCase"; // Перенаправляем после успешного добавления
    }

    // Обновление страхового случая
    @PostMapping("/update")
    public String updateInsuranceCase(
            @RequestParam Long id,
            @RequestParam @NotNull LocalDateTime issueDate,
            @RequestParam String description,
            @RequestParam @NotBlank @Size(max = 15) String status,
            @RequestParam Long insurancePolicyId // ID страхового полиса
    ) {
        InsurancePolicy insurancePolicy = new InsurancePolicy();
        insurancePolicy.setId(insurancePolicyId); // Присваиваем ID страхового полиса

        // Создание объекта для обновления страхового случая
        InsuranceCase updatedInsuranceCase = new InsuranceCase();
        updatedInsuranceCase.setId(id);
        updatedInsuranceCase.setIssueDate(issueDate);
        updatedInsuranceCase.setDescription(description);
        updatedInsuranceCase.setStatus(status);
        updatedInsuranceCase.setInsurancePolicy(insurancePolicy); // Присоединение страхового полиса

        // Отправляем запрос на обновление страхового случая
        restTemplate.put(apiUrl + "/update/" + id, updatedInsuranceCase);

        return "redirect:/InsuranceCase"; // Перенаправляем после успешного обновления
    }

    // Удаление страхового случая
    @PostMapping("/delete")
    public String deleteInsuranceCase(@RequestParam Long id) {
        restTemplate.delete(apiUrl + "/delete/" + id);
        return "redirect:/InsuranceCase"; // Перенаправляем после успешного удаления
    }
}

