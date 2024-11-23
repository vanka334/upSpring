package com.example.itog2.repositories;

import com.example.itog2.models.Branch;
import com.example.itog2.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
