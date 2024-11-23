package com.example.itog2.api;

import com.example.itog2.models.InsurancePolicy;
import com.example.itog2.services.InsurancePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/v1/api/insurancePolicy")
public class InsurancePolicyApi {
    @Autowired
    private final InsurancePolicyService insurancePolicyService;

    public InsurancePolicyApi(InsurancePolicyService insurancePolicyService) {
        this.insurancePolicyService = insurancePolicyService;
    }
    @GetMapping
    public List<InsurancePolicy> getAllInsurancePolicies() {
        return insurancePolicyService.getAllInsurancePolicys();
    }


    @GetMapping("/{id}")
    public InsurancePolicy getInsurancePolicy(@PathVariable Long id) {
        return insurancePolicyService.getInsurancePolicyById(id);
    }

    @PostMapping("/add")
    public InsurancePolicy addInsurancePolicy(@RequestBody InsurancePolicy insurancePolicy, List<Long> IdProducts) {
        return insurancePolicyService.saveInsurancePolicy(insurancePolicy, IdProducts);
    }
    @PutMapping("/update/{id}")
    public InsurancePolicy updateInsurancePolicy(@RequestBody InsurancePolicy insurancePolicy, List<Long> IdProducts) {
        return insurancePolicyService.updateInsurancePolicy(insurancePolicy, IdProducts);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteInsurancePolicy(@PathVariable Long id) {
        insurancePolicyService.deleteInsurancePolicy(id);
    }
}
