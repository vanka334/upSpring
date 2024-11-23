package com.example.itog2.repositories;

import com.example.itog2.models.Payment;
import com.example.itog2.models.TypePayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypePaymentRepository extends JpaRepository<TypePayment, Long> {
}
