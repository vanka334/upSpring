package com.example.itog2.api;

import com.example.itog2.models.Payment;
import com.example.itog2.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/payment")
public class PaymentApi {
    @Autowired
    private final PaymentService PaymentService;

    public PaymentApi(PaymentService PaymentService) {
        this.PaymentService = PaymentService;
    }
    @GetMapping
    public List<Payment> getAllPayments() {
        return PaymentService.getAllPayments();
    }


    @GetMapping("/{id}")
    public Payment getPayment(@PathVariable Long id) {
        return PaymentService.getPaymentById(id);
    }

    @PostMapping("/add")
    public Payment addPayment(@RequestBody Payment payment, Long idtype) {
        return PaymentService.savePayment(payment, idtype);
    }
    @PutMapping("/update/{id}")
    public Payment updatePayment(@RequestBody Payment Payment) {
        return PaymentService.updatePayment(Payment);
    }
    @DeleteMapping("/delete/{id}")
    public void deletePayment(@PathVariable Long id) {
        PaymentService.deletePayment(id);
    }
}
