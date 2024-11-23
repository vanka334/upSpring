package com.example.itog2.services;

import com.example.itog2.models.InsuranceProduct;
import com.example.itog2.repositories.InsuranceProductRepository;
import com.example.itog2.repositories.InsurancePolicyRepository;
import com.example.itog2.repositories.InsuranceProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceProductService {
    @Autowired
    private InsuranceProductRepository insuranceProductRepository;
    

    public List<InsuranceProduct> getAllInsuranceProducts() {
        return insuranceProductRepository.findAll();

    }
    public InsuranceProduct getInsuranceProductById(Long id) {
        return insuranceProductRepository.findById(id).orElse(null);


    }
    public InsuranceProduct saveInsuranceProduct(InsuranceProduct insuranceProduct) {
        return insuranceProductRepository.save(insuranceProduct);
    }
    public InsuranceProduct updateInsuranceProduct(InsuranceProduct insuranceProduct) {
        return insuranceProductRepository.save(insuranceProduct);
    }
    public void deleteInsuranceProduct(Long id) {
        insuranceProductRepository.deleteById(id);
    }

    public List<InsuranceProduct> getInsuranceProductsByIds(List<Long> ids) {
        return insuranceProductRepository.findAllById(ids);
    }
}
