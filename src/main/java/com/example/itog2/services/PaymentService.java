package com.example.itog2.services;

import com.example.itog2.models.Payment;
import com.example.itog2.models.Payment;
import com.example.itog2.models.TypePayment;
import com.example.itog2.repositories.PaymentRepository;
import com.example.itog2.repositories.TypePaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PaymentService  {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private TypePaymentRepository typePaymentRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();

    }
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);


    }
    public Payment savePayment(Payment payment, Long id) {

        payment.setTypePayment(typePaymentRepository.findById(id).orElse(null));
        return paymentRepository.save(payment);
    }
    public Payment updatePayment(Payment payment) {
        return paymentRepository.save(payment);
    }
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}
