package com.example.itog2.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class InsuranceProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String name;

    @Lob
    private String description;

    @NotNull
    private LocalDateTime validityPeriod;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal cost;

    @ManyToMany(mappedBy = "insuranceProducts")
    private List<InsurancePolicy> insurancePolicies = new ArrayList<>();
    // Getters and Setters

    public List<InsurancePolicy> getInsurancePolicies() {
        return insurancePolicies;
    }

    public void setInsurancePolicies(List<InsurancePolicy> insurancePolicies) {
        this.insurancePolicies = insurancePolicies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank @Size(max = 20) String getName() {
        return name;
    }

    public void setName(@NotBlank @Size(max = 20) String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public @NotNull LocalDateTime getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(@NotNull LocalDateTime validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public @NotNull @DecimalMin("0.00") BigDecimal getCost() {
        return cost;
    }

    public void setCost(@NotNull @DecimalMin("0.00") BigDecimal cost) {
        this.cost = cost;
    }
}
