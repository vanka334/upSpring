package com.example.itog2.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class InsurancePolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "clientId", nullable = false)
    private Client client;

    @NotNull
    private LocalDateTime issueDate;

    @NotNull
    private LocalDateTime endDate;
    @ManyToMany
    @JoinTable(
            name = "insurance_policy_product",
            joinColumns = @JoinColumn(name = "insurance_policy_id"),
            inverseJoinColumns = @JoinColumn(name = "insurance_product_id")
    )
    private List<InsuranceProduct> insuranceProducts = new ArrayList<>();

    public List<InsuranceProduct> getInsuranceProducts() {
        return insuranceProducts;
    }

    public void setInsuranceProducts(List<InsuranceProduct> insuranceProducts) {
        this.insuranceProducts = insuranceProducts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public @NotNull LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(@NotNull LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public @NotNull LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(@NotNull LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public @NotNull @DecimalMin("0.00") BigDecimal getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(@NotNull @DecimalMin("0.00") BigDecimal sumInsured) {
        this.sumInsured = sumInsured;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public @NotBlank @Size(max = 20) String getStatus() {
        return status;
    }

    public void setStatus(@NotBlank @Size(max = 20) String status) {
        this.status = status;
    }

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal sumInsured;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;

    @NotBlank
    @Size(max = 20)
    private String status;

    // Getters and Setters
}