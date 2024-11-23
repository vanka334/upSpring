package com.example.itog2.controllers;


import com.example.itog2.models.InsuranceCase;
import com.example.itog2.models.TypePayment;
import com.example.itog2.models.Payment;
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
@RequestMapping("/payment")
public class PaymentController {

    private final RestTemplate restTemplate;

    @Autowired
    public PaymentController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static final String API_BASE_URL = "http://localhost:8080/v1/api/payment";
    private static final String INSURANCE_CASE_API = "http://localhost:8080/v1/api/insuranceCase";
    private static final String TYPE_PAYMENT_API = "http://localhost:8080/v1/api/typePayment";

    /**
     * Получение всех платежей через API.
     */
    @GetMapping
    public String getAllPayments(Model model) {
        Payment[] payments = restTemplate.getForObject(API_BASE_URL, Payment[].class);
        List<Payment> paymentList = Arrays.asList(payments);

        // Получаем доступные страховые случаи и типы платежей
        InsuranceCase[] insuranceCases = restTemplate.getForObject(INSURANCE_CASE_API, InsuranceCase[].class);
        List<InsuranceCase> insuranceCaseList = Arrays.asList(insuranceCases);

        TypePayment[] typePayments = restTemplate.getForObject(TYPE_PAYMENT_API, TypePayment[].class);
        List<TypePayment> typePaymentList = Arrays.asList(typePayments);

        // Добавляем данные в модель
        model.addAttribute("payments", paymentList);
        model.addAttribute("insuranceCases", insuranceCaseList);
        model.addAttribute("typePayments", typePaymentList);

        return "payment";
    }

    /**
     * Добавление нового платежа через API.
     */
    @PostMapping("/add")
    public String addPayment(
            @RequestParam String paymentDate,
            @RequestParam BigDecimal summary,
            @RequestParam(required = false) String description,
            @RequestParam Long insurancePolicyId,
            @RequestParam Long insuranceCaseId,
            @RequestParam Long typePaymentId) {

        Payment payment = new Payment();
        payment.setPaymentDate(LocalDateTime.parse(paymentDate)); // Преобразование строки в LocalDateTime
        payment.setSummary(summary);
        payment.setDescription(description);

        // Отправка запроса на создание нового платежа
        restTemplate.postForObject(API_BASE_URL + "/add?insurancePolicyId=" + insurancePolicyId +
                "&insuranceCaseId=" + insuranceCaseId + "&typePaymentId=" + typePaymentId, payment, Payment.class);

        return "redirect:/payment";
    }

    /**
     * Обновление платежа через API.
     */
    @PostMapping("/update")
    public String updatePayment(
            @RequestParam Long id,
            @RequestParam String paymentDate,
            @RequestParam BigDecimal summary,
            @RequestParam(required = false) String description,
            @RequestParam Long insurancePolicyId,
            @RequestParam Long insuranceCaseId,
            @RequestParam Long typePaymentId) {

        Payment payment = new Payment();
        payment.setId(id);
        payment.setPaymentDate(LocalDateTime.parse(paymentDate)); // Преобразование строки в LocalDateTime
        payment.setSummary(summary);
        payment.setDescription(description);

        // Отправка запроса на обновление платежа
        restTemplate.put(API_BASE_URL + "/update/" + id + "?insurancePolicyId=" + insurancePolicyId +
                "&insuranceCaseId=" + insuranceCaseId + "&typePaymentId=" + typePaymentId, payment);

        return "redirect:/payment";
    }

    /**
     * Удаление платежа через API.
     */
    @PostMapping("/delete")
    public String deletePayment(@RequestParam Long id) {
        restTemplate.delete(API_BASE_URL + "/delete/" + id);
        return "redirect:/payment";
    }
}

