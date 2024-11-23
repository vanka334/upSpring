package com.example.itog2.repositories;

import com.example.itog2.models.Branch;
import com.example.itog2.models.InsuranceProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceProductRepository extends JpaRepository<InsuranceProduct, Long> {
}
