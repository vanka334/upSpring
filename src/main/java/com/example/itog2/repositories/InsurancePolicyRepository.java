package com.example.itog2.repositories;

import com.example.itog2.models.Branch;
import com.example.itog2.models.InsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Long> {
}
