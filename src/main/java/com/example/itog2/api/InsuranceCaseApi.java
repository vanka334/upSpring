package com.example.itog2.api;

import com.example.itog2.models.InsuranceCase;
import com.example.itog2.services.InsuranceCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/v1/api/insuranceCase")
public class InsuranceCaseApi {
    @Autowired
    private final InsuranceCaseService insuranceCaseService;

    public InsuranceCaseApi(InsuranceCaseService insuranceCaseService) {
        this.insuranceCaseService = insuranceCaseService;
    }
    @GetMapping
    public List<InsuranceCase> getAllInsuranceCasees() {
        return insuranceCaseService.getAllInsuranceCases();
    }


    @GetMapping("/{id}")
    public InsuranceCase getInsuranceCase(@PathVariable Long id) {
        return insuranceCaseService.getInsuranceCaseById(id);
    }

    @PostMapping("/add")
    public InsuranceCase addInsuranceCase(@RequestBody InsuranceCase insuranceCase, Long IdPolicy) {
        return insuranceCaseService.saveInsuranceCase(insuranceCase, IdPolicy);
    }
    @PutMapping("/update/{id}")
    public InsuranceCase updateInsuranceCase(@RequestBody InsuranceCase insuranceCase) {
        return insuranceCaseService.updateInsuranceCase(insuranceCase);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteInsuranceCase(@PathVariable Long id) {
        insuranceCaseService.deleteInsuranceCase(id);
    }
}
