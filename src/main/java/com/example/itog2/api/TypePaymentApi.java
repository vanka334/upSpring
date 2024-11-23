package com.example.itog2.api;

import com.example.itog2.models.TypePayment;
import com.example.itog2.services.TypePaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/typePayment")
public class TypePaymentApi {
    @Autowired
    private final TypePaymentService TypePaymentService;

    public TypePaymentApi(TypePaymentService TypePaymentService) {
        this.TypePaymentService = TypePaymentService;
    }
    @GetMapping
    public List<TypePayment> getAllPayments() {
        return TypePaymentService.getAllTypePaymentes();
    }


    @GetMapping("/{id}")
    public TypePayment getPayment(@PathVariable Long id) {
        return TypePaymentService.getTypePaymentById(id);
    }

    @PostMapping("/add")
    public TypePayment addPayment(@RequestBody TypePayment typePayment) {
        return TypePaymentService.saveTypePayment(typePayment);
    }
    @PutMapping("/update/{id}")
    public TypePayment updatePayment(@RequestBody TypePayment TypePayment) {
        return TypePaymentService.updateTypePayment(TypePayment);
    }
    @DeleteMapping("/delete/{id}")
    public void deletePayment(@PathVariable Long id) {
        TypePaymentService.deleteTypePayment(id);
    }
}
