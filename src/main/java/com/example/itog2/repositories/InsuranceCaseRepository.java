package com.example.itog2.repositories;

import com.example.itog2.models.Branch;
import com.example.itog2.models.InsuranceCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceCaseRepository extends JpaRepository<InsuranceCase, Long> {
}
