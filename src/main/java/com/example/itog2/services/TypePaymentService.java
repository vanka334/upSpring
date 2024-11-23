package com.example.itog2.services;

import com.example.itog2.models.TypePayment;
import com.example.itog2.repositories.TypePaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypePaymentService {

    @Autowired
    private TypePaymentRepository typePaymentRepository;
    public List<TypePayment> getAllTypePaymentes() {
        return typePaymentRepository.findAll();

    }
    public TypePayment getTypePaymentById(Long id) {
        return typePaymentRepository.findById(id).orElse(null);


    }
    public TypePayment saveTypePayment(TypePayment typePayment) {
        return typePaymentRepository.save(typePayment);
    }
    public TypePayment updateTypePayment(TypePayment typePayment) {
        return typePaymentRepository.save(typePayment);
    }
    public void deleteTypePayment(Long id) {
        typePaymentRepository.deleteById(id);
    }
}
