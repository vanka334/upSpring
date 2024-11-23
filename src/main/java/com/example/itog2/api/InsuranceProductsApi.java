package com.example.itog2.api;

import com.example.itog2.models.InsuranceProduct;
import com.example.itog2.services.InsuranceProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/insuranceProduct")
public class InsuranceProductsApi {
    @Autowired
    private final InsuranceProductService InsuranceProductService;

    public InsuranceProductsApi(InsuranceProductService InsuranceProductService) {
        this.InsuranceProductService = InsuranceProductService;
    }
    @GetMapping
    public List<InsuranceProduct> getAllInsuranceProducts() {
        return InsuranceProductService.getAllInsuranceProducts();
    }
    @GetMapping("/bulk")
    public List<InsuranceProduct> getInsuranceProductsByIds(@RequestParam List<Long> ids) {
        return InsuranceProductService.getInsuranceProductsByIds(ids);
    }

    @GetMapping("/{id}")
    public InsuranceProduct getInsuranceProduct(@PathVariable Long id) {
        return InsuranceProductService.getInsuranceProductById(id);
    }

    @PostMapping("/add")
    public InsuranceProduct addInsuranceProduct(@RequestBody InsuranceProduct InsuranceProduct) {
        return InsuranceProductService.saveInsuranceProduct(InsuranceProduct);
    }
    @PutMapping("/update/{id}")
    public InsuranceProduct updateInsuranceProduct(@RequestBody InsuranceProduct InsuranceProduct) {
        return InsuranceProductService.updateInsuranceProduct(InsuranceProduct);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteInsuranceProduct(@PathVariable Long id) {
        InsuranceProductService.deleteInsuranceProduct(id);
    }
}
