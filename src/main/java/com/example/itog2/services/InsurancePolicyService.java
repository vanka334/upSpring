package com.example.itog2.services;

import com.example.itog2.models.InsurancePolicy;
import com.example.itog2.models.InsuranceProduct;
import com.example.itog2.repositories.InsurancePolicyRepository;
import com.example.itog2.repositories.InsuranceProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InsurancePolicyService {
    @Autowired
    private InsurancePolicyRepository insurancePolicyRepository;
    @Autowired
    private InsuranceProductRepository policyRepository;

    public List<InsurancePolicy> getAllInsurancePolicys() {
        return insurancePolicyRepository.findAll();

    }
    public InsurancePolicy getInsurancePolicyById(Long id) {
        return insurancePolicyRepository.findById(id).orElse(null);


    }
    public InsurancePolicy saveInsurancePolicy(InsurancePolicy insurancePolicy, List<Long> ids) {
        List<InsuranceProduct> products = policyRepository.findAllById(ids);
        insurancePolicy.setInsuranceProducts(products);

        return insurancePolicyRepository.save(insurancePolicy);
    }
    public InsurancePolicy updateInsurancePolicy(InsurancePolicy insurancePolicy, List<Long> ids) {
        List<InsuranceProduct> products = policyRepository.findAllById(ids);
        insurancePolicy.getInsuranceProducts().clear();
        insurancePolicy.getInsuranceProducts().addAll(products);
        return insurancePolicyRepository.save(insurancePolicy);
    }
    public void deleteInsurancePolicy(Long id) {
        insurancePolicyRepository.deleteById(id);
    }
}
