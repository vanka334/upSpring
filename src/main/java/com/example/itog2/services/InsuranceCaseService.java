package com.example.itog2.services;

import com.example.itog2.models.InsuranceCase;
import com.example.itog2.repositories.InsuranceCaseRepository;
import com.example.itog2.repositories.InsurancePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InsuranceCaseService  {
    @Autowired
    private InsuranceCaseRepository insuranceCaseRepository;
    @Autowired
    private InsurancePolicyRepository policyRepository;
    
    public List<InsuranceCase> getAllInsuranceCases() {
        return insuranceCaseRepository.findAll();

    }
    public InsuranceCase getInsuranceCaseById(Long id) {
        return insuranceCaseRepository.findById(id).orElse(null);


    }

    public InsuranceCase saveInsuranceCase(InsuranceCase insuranceCase, Long id) {
        var policy = policyRepository.findById(id).orElse(null);
        insuranceCase.setInsurancePolicy(policy);
        return insuranceCaseRepository.save(insuranceCase);
    }

    public InsuranceCase updateInsuranceCase(InsuranceCase insuranceCase) {
        return insuranceCaseRepository.save(insuranceCase);
    }

    public void deleteInsuranceCase(Long id) {
        insuranceCaseRepository.deleteById(id);
    }
}
